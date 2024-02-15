package com.ddd.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ddd.shortlink.admin.common.Convention.result.Result;
import com.ddd.shortlink.admin.common.Convention.result.Results;
import com.ddd.shortlink.admin.dto.resp.UserActualRespDTO;
import com.ddd.shortlink.admin.dto.resp.UserRespDTO;
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
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("/api/shortlink/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

}
