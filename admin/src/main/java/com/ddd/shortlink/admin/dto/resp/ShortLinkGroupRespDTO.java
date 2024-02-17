package com.ddd.shortlink.admin.dto.resp;

import lombok.Data;

/**
 *
 **/
@Data
public class ShortLinkGroupRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    private Integer ShortLinkCount;

    /**
     * 分组排序
     */
    private Integer sortOrder;

}
