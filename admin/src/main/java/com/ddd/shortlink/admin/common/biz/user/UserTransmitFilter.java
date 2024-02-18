package com.ddd.shortlink.admin.common.biz.user;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.ddd.shortlink.admin.common.Convention.exception.ClientException;
import com.ddd.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.google.common.collect.Lists;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Objects;

/**
 * 用户信息传输过滤器
 */
@RequiredArgsConstructor
public class UserTransmitFilter implements Filter {


    private final StringRedisTemplate stringRedisTemplate;

    private final List<String> IGNORE_URI = Lists.newArrayList(
            "/api/short-link/admin/v1/user/login",
            "/api/short-link/admin/v1/user/has-username",
            "/api/short-link/admin/title"
    );

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if(!IGNORE_URI.contains(requestURI)){
            String method = httpServletRequest.getMethod();
            if (!(Objects.equals(requestURI, "/api/short-link/admin/v1/user") && Objects.equals(method, "POST"))) {
                String username = httpServletRequest.getHeader("username");
                String token = httpServletRequest.getHeader("token");
                if(!StrUtil.isAllNotBlank(username, token)){
                    throw new ClientException(UserErrorCodeEnum.USER_TOKEN_FAIL);
                }
                Object userInfoJsonStr = null;

                try{
                    userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
                    if(userInfoJsonStr == null){
                        throw new ClientException(UserErrorCodeEnum.USER_TOKEN_FAIL);
                    }
                }catch (Exception e){
                    throw new ClientException(UserErrorCodeEnum.USER_TOKEN_FAIL);
                }
                UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
                UserContext.setUser(userInfoDTO);
            }
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.removeUser();
        }
    }
//    @SneakyThrows
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String requestURI = httpServletRequest.getRequestURI();
//        if(!IGNORE_URI.contains(requestURI)){
//            String method = httpServletRequest.getMethod();
//            if(!(Objects.equals(requestURI, "api/short-link/admin/v1/user"))){
//                String username = httpServletRequest.getHeader("username");
//                String token = httpServletRequest.getHeader("token");
//                Object userInfoJsonStr = stringRedisTemplate.opsForHash().get("login_" + username, token);
//                if(userInfoJsonStr != null){
//                    UserInfoDTO userInfoDTO = JSON.parseObject(userInfoJsonStr.toString(), UserInfoDTO.class);
//                    UserContext.setUser(userInfoDTO);
//                }
//                try {
//                    filterChain.doFilter(servletRequest, servletResponse);
//                } finally {
//                    UserContext.removeUser();
//                }
//            }
//        }
//    }
}