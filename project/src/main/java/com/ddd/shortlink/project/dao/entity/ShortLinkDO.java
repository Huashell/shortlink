package com.ddd.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ddd.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 **/
@TableName("t_link")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkDO extends BaseDO {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;

    /**
     * 域名
     */
    private String domain;

    /**
     * 短链接
     */
    private String shortUri;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 点击量
     */
    private Integer clickNum = 0;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 网站图标
     */
    private String favicon;

    /**
     * 启用标识 0：启用 1：未启用
     */
    private int enableStatus;

    /**
     * 创建类型 0：接口创建 1：控制台创建
     */
    private int createdType = 1;

    /**
     * 有效期类型 0：永久有效 1：自定义
     */
    private int validDateType = 1;

    /**
     * 有效期
     */
    private Date validDate;

    /**
     * 描述
     */
    private String description;

    /**
     * 历史pv
     */
    private Integer totalPv = 0;

    /**
     * 历史uv
     */
    private Integer totalUv = 0;

    /**
     * 历史uip
     */
    private Integer totalUip = 0;


    /**
     * 删除时间戳
     */
    private Long delTime = 0L;


}
