package com.simon.app.service;

import com.simon.dal.model.User;

/**
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
public interface UserService {

    User findOne(String userId);

	User findUser(User user);

	int updateByPrimaryKeySelective(User user);
}
