package com.ddd.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddd.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 *
 **/

@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;


}