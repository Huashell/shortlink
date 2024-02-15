package com.ddd.shortlink.admin.dto;

import lombok.Data;

import java.util.Date;

/**
 * 用户返回实体
 **/


@Data
public class UserRespDTO {

    private Long id;

    /**
     * 用户名
     */
    private String username;
    

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注销时间戳
     */
    private Long deletionTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除标识 0：未删除 1：已删除
     */
    private Integer delFlag;
}
