package com.ddd.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddd.shortlink.project.dao.entity.ShortLinkDO;
import com.ddd.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
