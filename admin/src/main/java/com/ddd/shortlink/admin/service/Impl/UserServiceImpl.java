package com.ddd.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.shortlink.admin.dao.entity.UserDO;
import com.ddd.shortlink.admin.dao.mapper.UserMapper;
import com.ddd.shortlink.admin.dto.UserRespDTO;
import com.ddd.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *用户接口实现
 **/

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null) {
            return null;
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
