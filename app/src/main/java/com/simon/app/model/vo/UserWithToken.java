package com.simon.app.model.vo;

import com.simon.dal.model.User;

/**
 * @author fengtianying
 * @date 2018/11/9 16:12
 */
public class UserWithToken extends User {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
