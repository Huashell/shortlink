package com.ddd.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ddd.shortlink.admin.dao.entity.GroupDO;
import com.ddd.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.ddd.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.ddd.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;


/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {

    void saveGroup(String groupname);

    
    /**
     * 新增短链接分组
     */
    void saveGroup(String username, String groupname);

    List<ShortLinkGroupRespDTO> listGroup();


    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);

    void deleteGroup(String gid);

    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
}
