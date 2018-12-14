package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.service.CommunityService;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.model.Community;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
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

    @Override
    public Community detail(String communityId) {
        return communityMapper.selectByPrimaryKey(communityId);
    }

    @Override
    public int upd(Community community) {
        return communityMapper.updateByPrimaryKeySelective(community);
    }

    @Override
    public int del(String communityId) {
        return communityMapper.deleteByPrimaryKey(communityId);
    }

    @Override
    public PageInfo<Community> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(communityMapper.list(baseQueryParam));
    }
}
