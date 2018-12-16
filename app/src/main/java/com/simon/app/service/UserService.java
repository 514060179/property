package com.simon.app.service;

import com.simon.dal.model.User;

/**
 * @author fengtianying
 * @date 2018/11/7 13:32
 */
public interface UserService {

    User findOne(String userId);

	User findUser(String username, String password);

	int updateByPrimaryKeySelective(User user);
}
