package com.ddd.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ddd.shortlink.project.common.Convention.result.Result;
import com.ddd.shortlink.project.common.Convention.result.Results;
import com.ddd.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.ddd.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.ddd.shortlink.project.service.ShortLinkService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 **/

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    
    /**
     * 短链接跳转
     */
    @GetMapping("/{short-uri}")
    public void restorUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response){
        shortLinkService.restoreUrl(shortUri, request, response);
        return;
    }

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 修改短链接
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam){
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 短链接分页查询
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    /**
     * 查询短链接分组内数量
     */
    @GetMapping("/api/short-link/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        List<ShortLinkGroupCountQueryRespDTO> shortLinkGroupCountQueryRespDTOS = shortLinkService.listGroupShortLinkCount(requestParam);
        return Results.success(shortLinkGroupCountQueryRespDTOS);
    }
}