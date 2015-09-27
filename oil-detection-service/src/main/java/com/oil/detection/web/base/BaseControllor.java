package com.oil.detection.web.base;

import com.oil.detection.domain.resultxml.ErrorJson;
import com.oil.detection.enums.ErrorEnum;
import com.oil.detection.exception.JsiParamRuntimeException;
import com.oil.detection.exception.JsiRuntimeException;
import com.oil.detection.log.RunLog;
import com.oil.detection.web.interceptor.RequestHandleInterceptor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseControllor {
    /**
     * JsiRuntimeException
     */
    @ResponseBody
    @ExceptionHandler({JsiRuntimeException.class})
    public ErrorJson jsiRuntimeException(JsiRuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getAttribute(RequestHandleInterceptor.X_JSS_REQUEST_ID) + "";

        String requestURI = getRequestUrl(request);

        RunLog.LOG.error(String.format("【requestId:%s,requestURL:%s,ErrorMsg:%s】", requestId, requestURI, e.getMessage()));
        RunLog.LOG.error(e.getMessage(), e);
        System.out.println(e.getMessage());
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);

        ErrorJson errorJson = new ErrorJson(ErrorEnum.Server_Internal_Error, requestId, requestURI);
        errorJson.data = e.getErrData();
        return errorJson;
    }

    /**
     * RuntimeException
     */
    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public ErrorJson runtimeException(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getAttribute(RequestHandleInterceptor.X_JSS_REQUEST_ID) + "";

        String requestURI = getRequestUrl(request);

        RunLog.LOG.error(String.format("【requestId:%s,requestURL:%s,ErrorMsg:%s】", requestId, requestURI, e.getMessage()));
        RunLog.LOG.error(e.getMessage(), e);
        System.out.println(e.getMessage());
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);

        ErrorJson errorJson = new ErrorJson(ErrorEnum.Server_Internal_Error, requestId, requestURI);
        return errorJson;
    }

    /**
     * JsiParamRuntimeException
     */
    @ResponseBody
    @ExceptionHandler({JsiParamRuntimeException.class})
    public ErrorJson jsiParamRuntimeException(JsiParamRuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        String requestId = request.getAttribute(RequestHandleInterceptor.X_JSS_REQUEST_ID) + "";

        String requestURI = getRequestUrl(request);

        RunLog.LOG.error(String.format("【requestId:%s,requestURL:%s,ErrorMsg:%s】", requestId, requestURI, e.getMessage()));
        RunLog.LOG.error(e.getMessage(), e);
        System.out.println(e.getMessage());
        response.setStatus(HttpStatus.SC_BAD_REQUEST);

        ErrorJson errorJson = new ErrorJson(e.getErrorEnum(), requestId, requestURI);
        return errorJson;
    }

    private String getRequestUrl(HttpServletRequest request) {
        String requestURI = request.getMethod() + " " + request.getRequestURI();
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            requestURI += "?" + queryString;
        }
        return requestURI;
    }
}
