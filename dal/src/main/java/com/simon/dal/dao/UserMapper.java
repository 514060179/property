package com.simon.dal.dao;


import com.simon.dal.model.User;

import java.util.List;

public interface UserMapper {

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    User findByUserName(String userName);

    List<User> findListByCondition(User user);
}