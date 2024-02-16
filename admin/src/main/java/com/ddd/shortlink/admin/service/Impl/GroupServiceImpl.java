package com.ddd.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.shortlink.admin.dao.entity.GroupDO;
import com.ddd.shortlink.admin.dao.mapper.GroupMapper;
import com.ddd.shortlink.admin.service.GroupService;
import com.ddd.shortlink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                .name(groupname)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                //TODO 设置用户名
                .eq(GroupDO::getUsername, null);
        GroupDO hasGruopFlag = baseMapper.selectOne(queryWrapper);
        return hasGruopFlag == null;
    }
}
