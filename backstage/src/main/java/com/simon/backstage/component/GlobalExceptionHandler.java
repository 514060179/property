package com.simon.backstage.component;

import com.simon.backstage.domain.msg.Code;
import com.simon.backstage.domain.msg.ReturnMsg;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ReturnMsg paramExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.missingParameter,"缺少参数:"+e.getMessage());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ReturnMsg authenticationExceptionHandler(AuthenticationException e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.loginfail,"登录验证不通过");
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ReturnMsg dataIntegrityViolationExceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.truncation,"数据异常："+e.getCause().getMessage());
    }
    @ExceptionHandler(value = DuplicateKeyException.class)
    public ReturnMsg duplicateKeyExceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.duplicate,"重复key："+e.getCause().getMessage());
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ReturnMsg methodArgumentNotValidExceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.paramiolationException,"参数格式异常："+e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public ReturnMsg exceptionHandler(Exception e) {
        e.printStackTrace();
        logger.error(">>>> system error： ", e);
        return ReturnMsg.fail(Code.error,"系统异常："+e.getMessage());
    }
//    @ResponseStatus(value= HttpStatus.UNAUTHORIZED,reason="没有权限")
//    public ReturnParam unauthorizedHandler(Exception e) {
//        logger.error(">>>> system error： ", e);
//        return ReturnParam.systemError("系统异常！");
//    }


}
