package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.service.UserService;
import com.simon.dal.dao.UserMapper;
import com.simon.dal.model.User;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseQueryParam;
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
    public User add(User user) {
        user.setUserId(UUIDUtil.uidString());
        if(userMapper.insertSelective(user)>0){
            return user;
        }
        return null;
    }

    @Override
    public int upd(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int del(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public PageInfo<User> list(BaseQueryParam baseQueryParam) {
        PageHelper.startPage(baseQueryParam.getPageNo(),baseQueryParam.getPageSize());
        return new PageInfo<>(userMapper.selectByCondition(baseQueryParam));
    }
}
