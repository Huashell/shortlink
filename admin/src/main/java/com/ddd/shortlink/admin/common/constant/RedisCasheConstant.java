package com.ddd.shortlink.admin.common.constant;

/**
 * 短链接后管Redis缓存常量
 **/
public class RedisCasheConstant {
    public static final String LOCK_USER_REGISTER_KEY = "short-link:user-register:";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";
}
