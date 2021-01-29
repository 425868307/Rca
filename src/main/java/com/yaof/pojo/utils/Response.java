package com.yaof.pojo.utils;

public class Response<T> {

    public static final String CODE_SUCCESS = "1";
    public static final String CODE_ERROR = "0";
    public static final String CODE_EXCEPTION = "99";

    String code;
    String msg;
    T data;

    public Response() {
        super();
    }

    public Response(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
