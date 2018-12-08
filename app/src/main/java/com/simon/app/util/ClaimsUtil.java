package com.simon.app.util;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/8 16:01
 */
public class ClaimsUtil {

    public static String getUserId(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("user_id",String.class);
    }

    public static String getCommunityId(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("community_id",String.class);
    }

    public static String getUsername(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("username",String.class);
    }
}
