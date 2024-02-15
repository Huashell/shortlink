package com.ddd.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ddd.shortlink.admin.common.Convention.result.Result;
import com.ddd.shortlink.admin.common.Convention.result.Results;
import com.ddd.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.ddd.shortlink.admin.dto.resp.UserActualRespDTO;
import com.ddd.shortlink.admin.dto.resp.UserRespDTO;
import com.ddd.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("/api/short-link/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
    * 查询用户名是否可用
    **/
    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username){
        return Results.success(!userService.hasUsername(username));
    }

    @PostMapping("/api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO registerReqDTO){
        userService.register(registerReqDTO);
        return Results.success();
    }
}
