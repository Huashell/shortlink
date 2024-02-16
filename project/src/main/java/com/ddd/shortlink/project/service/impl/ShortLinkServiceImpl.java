package com.ddd.shortlink.project.service.impl;

import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ddd.shortlink.project.common.Convention.exception.ServiceException;
import com.ddd.shortlink.project.dao.entity.ShortLinkDO;
import com.ddd.shortlink.project.dao.mapper.ShortLinkMapper;
import com.ddd.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.ddd.shortlink.project.service.ShortLinkService;
import com.ddd.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * 短链接接口实现层
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    private final RBloomFilter<String> shortUriRegisterCachePenetrationBloomFilter;
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String shortLinkSuffix = generateSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .description(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .totalPv(0)
                .totalUv(0)
                .totalUip(0)
                .delTime(0L)
                .fullShortUrl(fullShortUrl)
                .build();
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException e) {
            log.warn("短链接: {} 重复入库", fullShortUrl);
            throw new ServiceException("短链接生成重复");
        }
        shortUriRegisterCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkDO.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam){
        String shortUri;
        int customGenerateCount = 0;
        while(true){
            if(customGenerateCount > 10){
                throw new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
            originUrl += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originUrl);
            if(!shortUriRegisterCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shortUri)){
                break;
            }
            customGenerateCount++;
        }
        return shortUri;
    }
}
