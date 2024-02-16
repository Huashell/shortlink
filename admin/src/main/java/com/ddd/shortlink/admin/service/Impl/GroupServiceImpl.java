package com.ddd.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.shortlink.admin.dao.entity.GroupDO;
import com.ddd.shortlink.admin.dao.mapper.GroupMapper;
import com.ddd.shortlink.admin.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 **/


@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
}
