package com.simon.backstage.service.impl;

import com.simon.backstage.service.UserService;
import com.simon.dal.dao.UserMapper;
import com.simon.dal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findOne(Long userId) {
        return userMapper.findByUserName("simon");
    }
}
