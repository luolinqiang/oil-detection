package com.oil.detection.util;

import com.oil.detection.common.ReturnCode;
import com.oil.detection.exception.CustomRuntimeException;
import com.oil.detection.log.RunLog;
import org.springframework.beans.BeanUtils;

public class TransferUtils {
    public static Object transfer(Object object, Class cls) {
        try {
            Object target = cls.newInstance();
            BeanUtils.copyProperties(object, target);
            return target;
        } catch (Exception e) {
            RunLog.LOG.error(e.getMessage(), e);
            throw new CustomRuntimeException(ReturnCode.ERROR_SERVER_TRANSFER);
        }
    }
}
