package com.oil.detection.web.base;

import com.oil.detection.common.ReturnCode;
import com.oil.detection.exception.CustomRuntimeException;
import com.oil.detection.log.RunLog;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public abstract class GlobalControllor {

    @ResponseBody
    @ExceptionHandler({TypeMismatchException.class})
    public Map<String, ? extends Object> typeMismatchException(TypeMismatchException e) {
        RunLog.LOG.error(e.getMessage(), e.getCause());
        this.printStackTrace(e);
        Map<String, Object> map = (Map<String, Object>) ReturnCode.ERROR_PARAMS.Map();
        map.put("msg", "值为 '" + e.getValue() + "' 参数错误！");
        return map;
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Map<String, ? extends Object> exception(Exception e) {
        RunLog.LOG.error(e.getMessage(), e.getCause());
        printStackTrace(e);
        Map<String, Object> map = (Map<String, Object>) ReturnCode.ERROR_SERVER.Map();
        map.put("data", e.getMessage());
        return map;
    }

    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public Map<String, ? extends Object> exception(RuntimeException e) {
        RunLog.LOG.error(e.getMessage(), e.getCause());
        printStackTrace(e);
        Map<String, Object> map = (Map<String, Object>) ReturnCode.ERROR_SERVER.Map();
        map.put("data", e.getMessage());
        map.remove("msg");
        return map;
    }

    @ResponseBody
    @ExceptionHandler({CustomRuntimeException.class})
    public Map<String, ? extends Object> exception(CustomRuntimeException e) {
        RunLog.LOG.error(e.getMessage(), e.getCause());
        printStackTrace(e);
        return e.getReturnCode().Map();
    }

    private void printStackTrace(Exception e) {
        StringWriter w = new StringWriter();
        PrintWriter pw = new PrintWriter(w);
        e.printStackTrace(pw);
        pw.flush();
        RunLog.LOG.error(w.toString());
    }
}
