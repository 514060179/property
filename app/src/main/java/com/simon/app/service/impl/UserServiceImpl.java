package com.simon.app.service.impl;

import com.simon.app.service.UserService;
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
    public User findOne(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    
    @Override
    public User findUser(String username, String password) {
		return userMapper.selectUser(username, password);
    }
    
}
