package com.simon.app.model.vo;

/**
 * @author fengtianying
 * @date 2018/11/9 16:02
 */
public class ReturnMsg<T> implements Code {

    private boolean success;

    private String code;

    private String msg;

    private T data;

    public ReturnMsg(){

    }

    public ReturnMsg(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ReturnMsg success(){
        return new ReturnMsg(Boolean.TRUE,Code.success,"成功",null);
    }

    public static ReturnMsg success(Object object){
        return new ReturnMsg(Boolean.TRUE,Code.success,"成功",object);
    }
    
    public static ReturnMsg fail(){
    	return new ReturnMsg(Boolean.FALSE,Code.nologin,"失败",null);
    }
    
    public static ReturnMsg fail(String msg, String code){
    	return new ReturnMsg(Boolean.FALSE, code, msg,null);
    }
    
    public static ReturnMsg loginFail(){
    	return new ReturnMsg(Boolean.FALSE,Code.loginfail,"账号不存在或密码错误!",null);
    }

    public static ReturnMsg accountNoExit(){
    	return new ReturnMsg(Boolean.FALSE,Code.accountNoExit,"账号不存在!",null);
    }
    public static ReturnMsg noSetEmail(){
    	return new ReturnMsg(Boolean.FALSE,Code.noSetEmail,"邮箱地址未设置!",null);
    }

    public static ReturnMsg wrongPassword(){
    	return new ReturnMsg(Boolean.FALSE,Code.loginfail,"旧密码不正确",null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
