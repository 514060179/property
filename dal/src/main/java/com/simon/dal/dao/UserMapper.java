package com.simon.dal.dao;

import com.simon.dal.vo.BaseQueryParam;
import org.apache.ibatis.annotations.Param;

import com.simon.dal.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectByCondition(BaseQueryParam baseQueryParam);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectUser(@Param("username") String username, @Param("password") String password);
}