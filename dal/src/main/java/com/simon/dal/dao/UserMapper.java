package com.simon.dal.dao;

import org.apache.ibatis.annotations.Param;

import com.simon.dal.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectUser(@Param("username") String username, @Param("password") String password);
}