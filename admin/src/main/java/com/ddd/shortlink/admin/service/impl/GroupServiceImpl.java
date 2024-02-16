package com.ddd.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.shortlink.admin.common.biz.user.UserContext;
import com.ddd.shortlink.admin.dao.entity.GroupDO;
import com.ddd.shortlink.admin.dao.mapper.GroupMapper;
import com.ddd.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.ddd.shortlink.admin.service.GroupService;
import com.ddd.shortlink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 **/


@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupname) {
        String gid;
        while(true){
            gid = RandomGenerator.generateRandom();
            if (hasGid(gid)){
                break;
            }
        }

        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .username(UserContext.getUsername())
                .name(groupname)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        //TODO 获取用户名
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);

        return BeanUtil.copyToList(groupDOList, ShortLinkGroupRespDTO.class);
    }

    private boolean hasGid(String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                //TODO 设置用户名
                .eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO hasGruopFlag = baseMapper.selectOne(queryWrapper);
        return hasGruopFlag == null;
    }
}
