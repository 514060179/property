package com.simon.dal.dao;

import com.simon.dal.model.Community;

import java.util.List;

public interface CommunityMapper {
    int deleteByPrimaryKey(String communityId);

    int insert(Community record);

    int insertSelective(Community record);

    Community selectByPrimaryKey(String communityId);

    int updateByPrimaryKeySelective(Community record);

    int updateByPrimaryKey(Community record);

    List<Community> list();
}