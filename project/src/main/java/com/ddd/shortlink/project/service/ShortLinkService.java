package com.ddd.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ddd.shortlink.project.dao.entity.ShortLinkDO;
import com.ddd.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.ddd.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

public interface ShortLinkService extends IService<ShortLinkDO> {
    /**
     * 创建短链接
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    void updateShortLink(ShortLinkUpdateReqDTO requestParam);
}
