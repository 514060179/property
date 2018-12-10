package com.simon.backstage.domain.msg;

/**
 * @author fengtianying
 * @date 2018/12/10 14:02
 */
public class ReturnMsg<T> implements Code {

    private boolean success;

    private int code;

    private String msg;

    private T data;

    public ReturnMsg(boolean success, int code, String msg, T data) {
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
