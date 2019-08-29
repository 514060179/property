package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.backstage.domain.model.BuildingChild;
import com.simon.backstage.domain.model.BuildingPart;
import com.simon.dal.model.Community;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

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

    /**
     * 社区下的建筑子部分
     * @param communityId
     * @return
     */
    List<BuildingPart> buildPartOfCommunity(String communityId);
}
