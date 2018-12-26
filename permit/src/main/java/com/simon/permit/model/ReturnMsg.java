package com.simon.permit.model;


/**
 * @author fengtianying
 * @date 2018/12/25 13:47
 */
public class ReturnMsg {

    private boolean success;

    private String msg;

    private Object data;

    public ReturnMsg() {
    }

    public ReturnMsg(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static ReturnMsg success(){
        return new ReturnMsg(Boolean.TRUE,"成功!",null);
    }
    public static ReturnMsg success(Object object){
        return new ReturnMsg(Boolean.TRUE,"成功!",object);
    }
    public static ReturnMsg fail(String msg){
        return new ReturnMsg(Boolean.FALSE,msg,null);
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
