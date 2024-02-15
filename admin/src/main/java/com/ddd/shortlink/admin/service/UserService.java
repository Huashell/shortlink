package com.ddd.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddd.shortlink.admin.dao.entity.UserDO;
import com.ddd.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /**
    * @Description:根据用户名查询用户信息
    * @Param:
    * @return
    **/
    UserRespDTO getUserByUsername(String username);

}
