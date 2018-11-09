package com.simon.backstage.component;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author fengtianying
 * @date 2018/9/4 15:28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public String paramExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return "";
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public String authenticationExceptionHandler(AuthenticationException e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return e.getMessage();
    }
//    @ResponseStatus(value= HttpStatus.UNAUTHORIZED,reason="没有权限")
//    public ReturnParam unauthorizedHandler(Exception e) {
//        logger.error(">>>> system error： ", e);
//        return ReturnParam.systemError("系统异常！");
//    }


}
