package com.ddd.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ddd.shortlink.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 短链接分组实体
 */

@Data
@TableName("t_group_0")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;



}
