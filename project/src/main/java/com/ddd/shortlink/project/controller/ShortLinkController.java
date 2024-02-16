package com.ddd.shortlink.project.controller;

import com.ddd.shortlink.project.common.Convention.result.Result;
import com.ddd.shortlink.project.common.Convention.result.Results;
import com.ddd.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.ddd.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 **/

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;
    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }
}
