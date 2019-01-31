package com.simon.app.component;

import com.simon.app.model.vo.Code;
import com.simon.app.model.vo.ReturnMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * @author fengtianying
 * @date 2018/9/4 15:28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ReturnMsg paramExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.missparam,"缺少参数："+e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ReturnMsg exceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.systemError,"系统异常："+e.getMessage());
    }


}
