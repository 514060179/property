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
    //是否多个组合使用逗号隔开的字符串
    public static boolean isMutilString(String str) {
        try {
            String[] split = str.split(",");
            if (split.length > 1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //触摸板使用
    public static String getBuildingId(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("perms",String.class);
    }

    public static String getUsername(HttpServletRequest request){
        Claims claims = (Claims)request.getAttribute("claims");
        if (Objects.isNull(claims)){
            return null;
        }
        return claims.get("iss",String.class);
    }
}
