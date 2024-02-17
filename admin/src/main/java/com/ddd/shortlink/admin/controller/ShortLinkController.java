package com.ddd.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddd.shortlink.admin.common.Convention.result.Result;
import com.ddd.shortlink.admin.common.Convention.result.Results;
import com.ddd.shortlink.admin.remote.ShortLinkRemoteService;
import com.ddd.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.ddd.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.ddd.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.ddd.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.*;

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
     * 修改短链接
     */
    @PutMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        ShortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }


    /**
     * 短链接分页查询
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
