package com.ddd.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddd.shortlink.admin.common.Convention.result.Result;
import com.ddd.shortlink.admin.common.Convention.result.Results;
import com.ddd.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.ddd.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.ddd.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.ddd.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 **/

@RestController
public class ShortLinkController {

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };


    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkRemoteService.createShortLink(requestParam));
    }


    /**
     * 短链接分页查询
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
