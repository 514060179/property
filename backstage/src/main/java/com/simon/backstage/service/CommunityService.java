package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.dal.model.Community;
import com.simon.dal.vo.BaseQueryParam;

public interface CommunityService {
    /**
     * 新增社区
     * @param community
     * @return
     */
    Community add(Community community);


    /**
     * 新增
     * @param communityId
     * @return
     */
    Community detail(String communityId);

    /**
     * 修改
     * @param community
     * @return
     */
    int upd(Community community);

    /**
     * 删除
     * @param communityId
     * @return
     */
    int del(String communityId);

    /**
     * 列表
     * @param baseQueryParam
     * @return
     */
    PageInfo<Community> list(BaseQueryParam baseQueryParam);
}
