package com.ddd.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ddd.shortlink.admin.common.Convention.result.Result;
import com.ddd.shortlink.admin.common.Convention.result.Results;
import com.ddd.shortlink.admin.dto.req.UserLoginReqDTO;
import com.ddd.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.ddd.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.ddd.shortlink.admin.dto.resp.UserActualRespDTO;
import com.ddd.shortlink.admin.dto.resp.UserLoginRespDTO;
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
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
    * 查询用户名是否可用
    **/
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username){
        return Results.success(!userService.hasUsername(username));
    }

    /**
     * 用户注册
     */

    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO registerReqDTO){
        userService.register(registerReqDTO);
        return Results.success();
    }
    /**
    * 用户更新数据
    **/
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO reqDTO){
        userService.update(reqDTO);
        return Results.success();
    }
    /**
     *
     */

    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO){
        return Results.success(userService.login(userLoginReqDTO));

    }

    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token){
        return Results.success(userService.checkLogin(username, token));
    }

    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token){
        userService.logout(username, token);
        return Results.success();
    }

}
