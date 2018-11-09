package com.simon.backstage.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * springboot下的jackon 处理json
 * @author fengtianying
 * @date 2018/9/4 14:57
 */
public class JSONUtil {

    protected static Logger logger  = LoggerFactory.getLogger(JSONUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    public static <T> T jsonToObject(String jsonString,Class<T> c){

        try {
            return mapper.readValue(jsonString,c);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(jsonString+"is not a json string",e);
            return null;
        }
    }

    public static String objectToJson(Object object){
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("system error",e);
            return null;
        }
    }

    public static <T> List<T> jsonToList(String jsonString) {
        try {
            return mapper.readValue(jsonString,new TypeReference<List<T>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(jsonString+"is not a json array",e);
            return null;

        }
    }

    public static Map<String, Object> readValue(String val) {
        try {
            return mapper.readValue(val, Map.class);
        } catch (IOException e) {
            throw new MalformedJwtException("Unable to read JSON value: " + val, e);
        }
    }
    public static String listToJson(List list) {
        try {
            return  mapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("system error",e);
            return null;

        }
    }
}
