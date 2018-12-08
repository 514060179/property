package com.simon.app.service.impl;

import com.github.pagehelper.PageHelper;
import com.simon.app.model.vo.BaseQueryParam;
import com.simon.app.service.CommunityService;
import com.simon.dal.dao.CommunityMapper;
import com.simon.dal.model.Community;
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

    @Override
    public List<Community> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return communityMapper.list();
    }
}
