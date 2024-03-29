package com.ddd.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ddd.shortlink.project.dao.entity.ShortLinkDO;
import com.ddd.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.ddd.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import com.ddd.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.ddd.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.ddd.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * 回收站管理接口层
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 保存回收站
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询短链接
     *
     * @param requestParam 分页查询短链接请求参数
     * @return 短链接分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    /**
     * 从回收站恢复短链接
     *
     * @param requestParam 恢复短链接请求参数
     */
    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);

    /**
     * 从回收站移除短链接
     *
     * @param requestParam 移除短链接请求参数
     */
    void removeRecycleBin(RecycleBinRemoveReqDTO requestParam);
}