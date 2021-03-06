package com.simon.app.model.vo;

/**
 * @author fengtianying
 * @date 2018/11/9 16:04
 */
public interface Code {
    //操作成功
    String success = "200";
    //缺少参数
    String missparam = "201";
    //未登录
    String nologin = "100";
    //登录失败
    String loginfail = "105";
    //密码错误
    String wrongPassword = "106";
    //账户不存在
    String accountNoExit = "107";

    String noSetEmail = "108";

    //非法文件
    String illegalFile = "301";
    
    //订场失败
    String orderFail = "401";
    
    String systemError = "999";
    
}
