package com.simon.backstage.util;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author fengtianying
 * @date 2018/12/8 16:01
 */
public class ClaimsUtil {

    public static String getManagerId(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("sub",String.class);
    }

    public static String getCommunityId(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("community",String.class);
    }
    //触摸板使用
    public static String getBuildingId(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("roles",String.class);
    }

    public static String getUsername(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("iss",String.class);
    }
}
