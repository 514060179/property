package com.simon.backstage.domain.msg;

/**
 * @author fengtianying
 * @date 2018/12/10 14:02
 */
public interface Code {
    //成功
    int success=200;
    
    //未登录
    int nologin=100;

    //数据库key重复
    int duplicate=201;

    //参数溢出(数据长度过长，非外键数据)
    int truncation=202;

    //系统异常
    int error=999;

    //参数异常
    int paramiolationException=300;

    //未授权
    int unauthorized = 401;

}
