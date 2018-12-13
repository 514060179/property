package com.simon.dal.util;

import java.util.UUID;

/**
 * @author fengtianying
 * @date 2018/12/8 15:38
 */
public class UUIDUtil {

    public static String uidString(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
