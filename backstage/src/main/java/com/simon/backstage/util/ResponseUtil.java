package com.simon.backstage.util;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author fengtianying
 * @date 2018/11/9 14:38
 */
public class ResponseUtil {

    private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
    public static void responseWrite(String outStr, ServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            printWriter.write(outStr);
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
        }finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }

}
