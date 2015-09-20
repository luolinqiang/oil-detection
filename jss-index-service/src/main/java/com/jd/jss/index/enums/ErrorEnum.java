package com.jd.jss.index.enums;

public enum ErrorEnum {
    Instance(0, "msg"),
    success(200, "success"),
    Client_Param_Error(400, "client param error"),
    Server_Internal_Error(500, "internal server error");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
