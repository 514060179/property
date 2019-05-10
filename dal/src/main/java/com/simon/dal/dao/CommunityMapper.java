package com.simon.dal.dao;

import com.simon.dal.model.Community;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseQueryParam;

import java.util.List;

public interface CommunityMapper {
    int deleteByPrimaryKey(String communityId);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(String communityId);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<Community> list(BaseQueryParam baseQueryParam);

    List<UserWithCommunity> selfList(BaseQueryParam baseQueryParam);

	String findName(String communityId);
	
	String findId();
}