package com.ddd.shortlink.admin.controller;

import com.ddd.shortlink.admin.common.Convention.enums.UserErrorCodeEnum;
import com.ddd.shortlink.admin.common.Convention.result.Result;
import com.ddd.shortlink.admin.dto.UserRespDTO;
import com.ddd.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @return
 **/

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
    * @Description:根据用户名查询用户信息
    **/
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        UserRespDTO result = userService.getUserByUsername(username);
        if(result == null){
            return new Result<UserRespDTO>().setCode(UserErrorCodeEnum.USER_NULL.code()).setMessage(UserErrorCodeEnum.USER_NULL.message());
        }
        return new Result<UserRespDTO>().setCode("0").setData(result);
    }

}
