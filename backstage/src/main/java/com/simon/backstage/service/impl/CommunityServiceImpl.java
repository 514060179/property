package com.simon.backstage.service.impl;

import com.simon.backstage.service.CommunityService;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.model.Community;
import com.simon.dal.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {


    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public Community add(Community community) {
        community.setCommunityId(UUIDUtil.uidString());
        if (communityMapper.insertSelective(community)>0){
            return community;
        }
        return null;
    }
}
