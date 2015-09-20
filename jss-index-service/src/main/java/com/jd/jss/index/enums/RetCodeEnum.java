package com.jd.jss.index.enums;

public enum RetCodeEnum {
    Success(0), PermissionError(-1), InternalError(-2), ParamError(-3);
    private int code;

    public int getCode() {
        return code;
    }

    private RetCodeEnum(int code) {
        this.code = code;
    }
}
