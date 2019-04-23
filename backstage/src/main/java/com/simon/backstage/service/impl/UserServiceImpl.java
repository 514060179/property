package com.simon.backstage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simon.backstage.service.UserService;
import com.simon.dal.dao.UserMapper;
import com.simon.dal.model.User;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.util.EncryUtil;
import com.simon.dal.util.UUIDUtil;
import com.simon.dal.vo.BaseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public User add(User user) {
        user.setUserId(UUIDUtil.uidString());
        user.setPassword(EncryUtil.getMD5(user.getPassword()));
        if(userMapper.insertSelective(user)>0){
            userMapper.insertUserCommunity(user);
            return user;
        }
        return null;
    }

    @Override
    public User detail(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setUserWithCommunities(userMapper.selectUserCommunitys(userId));
        return user;
    }

    @Override
    public int delUserCommunity(String userId, String communityId) {
        return userMapper.deleteUserCommunity(userId,communityId);
    }

    @Override
    public int addUserCommunity(UserWithCommunity userWithCommunity) {
        User user = new User();
        user.setUserId(userWithCommunity.getUserId());
        user.setCommunityId(userWithCommunity.getCommunityId());
        return userMapper.insertUserCommunity(user);
    }

    @Override
    public int upd(User user) {
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(EncryUtil.getMD5(user.getPassword()));
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int del(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public PageInfo<User> list(BaseClaims baseClaims) {
        PageHelper.startPage(baseClaims.getPageNo(),baseClaims.getPageSize());
        return new PageInfo<>(userMapper.selectByCondition(baseClaims));
    }
}
