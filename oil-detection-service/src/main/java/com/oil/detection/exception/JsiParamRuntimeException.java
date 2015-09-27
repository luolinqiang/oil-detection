package com.oil.detection.exception;

import com.oil.detection.enums.ErrorEnum;
import com.oil.detection.enums.RetCodeEnum;

public class JsiParamRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private RetCodeEnum retCodeEnum;
    private ErrorEnum errorEnum;

    public JsiParamRuntimeException(RetCodeEnum retCodeEnum, ErrorEnum errorEnum) {
        super(errorEnum.toString());
        this.retCodeEnum = retCodeEnum;
        this.errorEnum = errorEnum;
    }

    public JsiParamRuntimeException(RetCodeEnum retCodeEnum, ErrorEnum errorEnum, Throwable e) {
        super(errorEnum.toString(), e);
        this.retCodeEnum = retCodeEnum;
        this.errorEnum = errorEnum;
    }

    public JsiParamRuntimeException(RetCodeEnum retCodeEnum, ErrorEnum errorEnum, String message) {
        super(String.format("%s:%s", errorEnum.toString(), message));
        this.retCodeEnum = retCodeEnum;
        this.errorEnum = errorEnum;
    }

    public JsiParamRuntimeException(RetCodeEnum retCodeEnum, ErrorEnum errorEnum, String message, Throwable e) {
        super(String.format("%s:%s", errorEnum.toString(), message), e);
        this.retCodeEnum = retCodeEnum;
        this.errorEnum = errorEnum;
    }


    public RetCodeEnum getRetCodeEnum() {
        return retCodeEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}
