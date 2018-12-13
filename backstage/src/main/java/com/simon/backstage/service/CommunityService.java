package com.simon.backstage.service;

import com.simon.dal.model.Community;

public interface CommunityService {
    /**
     * 新增社区
     * @param community
     * @return
     */
    Community add(Community community);
}
