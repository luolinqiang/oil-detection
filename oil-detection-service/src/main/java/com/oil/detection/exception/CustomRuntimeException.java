package com.oil.detection.exception;

import com.oil.detection.common.ReturnCode;

public class CustomRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ReturnCode returnCode;

    public CustomRuntimeException(ReturnCode returnCode) {
        super();
        this.returnCode = returnCode;
    }

    public CustomRuntimeException(ReturnCode returnCode, Throwable throwable) {
        super(returnCode.msg(), throwable);
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }
}
