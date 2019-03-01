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
    
    int loginfail = 101;

    //注销失败
    int logoutfail = 102;
    
    //密码错误
    int wrongPassword = 103;

    //缺少参数
    int missingParameter = 104;

    //数据库key重复
    int duplicate=201;

    //参数溢出(数据长度过长，非外键数据)
    int truncation=202;

    //未知文件
    int unknownFile = 203;

    //业主存在
    int ownerExist = 204;

    //未找到资源
    int notfound = 404;

    //系统异常
    int error=999;

    //参数异常
    int paramiolationException=300;

    //未授权
    int unauthorized = 401;


}
