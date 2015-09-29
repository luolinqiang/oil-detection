package com.oil.detection.util;

import com.oil.detection.common.ReturnCode;
import com.oil.detection.exception.CustomRuntimeException;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

public class Precondition {

    public static void assertion(boolean expression, ReturnCode returnCode) {
        if (!expression) {
            throw new CustomRuntimeException(returnCode);
        }
    }

    public static void assertionNotBlank(Object object, String field) {
        Field[] fields = object.getClass().getDeclaredFields();//获得对象所有属性
        for (Field field1 : fields) {
            if (field1.getName() != null && field1.getName().equals(field)) {
                try {
                    Object o = field1.get(object);
                    assertion(o != null, ReturnCode.ERROR_PARAMS);
                    if (o instanceof String) {
                        String str = (String) o;
                        assertion(StringUtils.isNotBlank(str), ReturnCode.ERROR_PARAMS);
                    }
                    return;
                } catch (IllegalAccessException e) {
                    assertion(true, ReturnCode.ERROR_PARAMS_REFLECT);
                }
            }
        }
    }

}
