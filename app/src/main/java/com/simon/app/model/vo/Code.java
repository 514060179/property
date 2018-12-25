package com.simon.app.model.vo;

/**
 * @author fengtianying
 * @date 2018/11/9 16:04
 */
public interface Code {
    //操作成功
    String success = "200";
    //未登录
    String nologin = "100";
    //登录失败
    String loginfail = "105";
    //密码错误
    String wrongPassword = "106";
    //订场失败
    String orderFail = "401";
    
    String systemError = "999";
}
