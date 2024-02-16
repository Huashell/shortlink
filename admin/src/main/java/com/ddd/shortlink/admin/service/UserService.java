package com.ddd.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddd.shortlink.admin.dao.entity.UserDO;
import com.ddd.shortlink.admin.dto.req.UserLoginReqDTO;
import com.ddd.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.ddd.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.ddd.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.ddd.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {

    /**
    * @Description:根据用户名查询用户信息
    * @Param:
    * @return
    **/
    UserRespDTO getUserByUsername(String username);

    /**
    * @Description:查询是否存在某个用户名
    **/
    Boolean hasUsername(String username);

    /**
    * @Description:注册用户
    * @Param:
    * @return
    **/
    void register(UserRegisterReqDTO requestParam);

    void update(UserUpdateReqDTO reqDTO);

    UserLoginRespDTO login(UserLoginReqDTO userLoginReqDTO);

    Boolean checkLogin(String username, String token);

    void logout(String username, String token);
}
