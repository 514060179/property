package com.simon.backstage.service;

import com.simon.dal.model.User;

/**
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
public interface UserService {

    User findOne(Long userId);
}