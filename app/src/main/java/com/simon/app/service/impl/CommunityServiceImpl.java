package com.simon.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.simon.app.dao.BuildingMapper;
import com.simon.app.model.vo.Building;
import com.simon.app.service.CommunityService;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengtianying
 * @date 2018/12/8 16:43
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private BuildingMapper buildingMapper;

    @Override
    public List<UserWithCommunity> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return communityMapper.selfList(baseQueryParam);
    }

    @Override
    public List<Building> buildingList(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return buildingMapper.selectByCondition(baseQueryParam);
    }
}
