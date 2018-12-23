package com.simon.app.model.vo;

import com.simon.dal.model.User;

/**
 * @author fengtianying
 * @date 2018/11/9 16:12
 */
public class UserWithToken {

	private User user;
	
    private String token;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
