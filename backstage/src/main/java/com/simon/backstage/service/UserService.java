package com.simon.backstage.service;

import com.github.pagehelper.PageInfo;
import com.simon.dal.model.User;
import com.simon.dal.model.UserWithCommunity;
import com.simon.dal.vo.BaseClaims;

/**
 * 用户管理
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
public interface UserService {

    /**
     * 新增
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 新增
     * @param userId
     * @return
     */
    User detail(String userId);

    /**
     * 删除绑定的社区
     * @param userId
     * @param communityId
     * @return
     */
    int delUserCommunity(String userId,String communityId);

    /**
     * 添加绑定社区
     * @param userWithCommunity
     * @return
     */
    int addUserCommunity(UserWithCommunity userWithCommunity);

    /**
     * 修改
     * @param user
     * @return
     */
    int upd(User user);

    /**
     * 删除
     * @param userId
     * @return
     */
    int del(String userId);

    /**
     * 列表
     * @param baseClaims
     * @return
     */
    PageInfo<User> list(BaseClaims baseClaims);
}
