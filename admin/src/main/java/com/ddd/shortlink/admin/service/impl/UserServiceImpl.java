package com.ddd.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.shortlink.admin.common.Convention.enums.UserErrorCodeEnum;
import com.ddd.shortlink.admin.common.Convention.exception.ClientException;
import com.ddd.shortlink.admin.dao.entity.UserDO;
import com.ddd.shortlink.admin.dao.mapper.UserMapper;
import com.ddd.shortlink.admin.dto.req.UserLoginReqDTO;
import com.ddd.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.ddd.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.ddd.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.ddd.shortlink.admin.dto.resp.UserRespDTO;
import com.ddd.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.ddd.shortlink.admin.common.constant.RedisCasheConstant.LOCK_USER_REGISTER_KEY;

/**
 *用户接口实现
 **/

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {


   private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
   private final RedissonClient redissonClient;
   private final StringRedisTemplate stringRedisTemplate;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if(hasUsername(requestParam.getUsername())){
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try {
            if (lock.tryLock()){
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if(inserted < 1){
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void update(UserUpdateReqDTO reqDTO) {
        //todo验证当前用户是否为登陆用户
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, reqDTO.getUsername());
        baseMapper.update(BeanUtil.toBean(reqDTO, UserDO.class), updateWrapper);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userLoginReqDTO.getUsername())
                .eq(UserDO::getPassword, userLoginReqDTO.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + userLoginReqDTO.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + userLoginReqDTO.getUsername(), 24L, TimeUnit.HOURS);
        return new UserLoginRespDTO(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().hasKey("login_"+username, token);
    }

    @Override
    public void logout(String username, String token) {
        if(checkLogin(username, token)){
            stringRedisTemplate.delete("login_"+username);
            return;
        }
        throw new ClientException("用户不存在或用户未登录");
    }
}
