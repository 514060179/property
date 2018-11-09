package com.simon.backstage.shiro.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author fengtianying
 * @date 2018/11/6 15:43
 */
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {

    private String base64Secret;

    private int expiresSecond;

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }
}
