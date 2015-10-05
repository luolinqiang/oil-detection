package com.oil.detection.util;

import com.oil.detection.common.ReturnCode;
import javassist.*;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

public class BeanCustomUtils {
    public static Object addFieldValue(Object obj, String fieldName, String fieldValue) {
        String fupperFieldName = firstUpper(fieldName);

        ClassPool pool = ClassPool.getDefault();
        //获取一个Student类的CtClass对象
        pool.insertClassPath(new ClassClassPath(obj.getClass()));
        CtClass ctClass = pool.makeClass(obj.getClass().getName() + fupperFieldName);
        Object o = null;
        try {
            //为ctClass设置一个父类
            ctClass.setSuperclass(pool.get(obj.getClass().getName()));
            //为cTclass对象添加一个属性name
            ctClass.addField(CtField.make("private String " + fieldName + ";", ctClass));
            ctClass.addMethod(CtMethod.make("public void set" + fupperFieldName + "(String " + fieldName + "){this." + fieldName + " = " + fieldName + ";}", ctClass));
            ctClass.addMethod(CtMethod.make("public String get" + fupperFieldName + "(){return this." + fieldName + ";}", ctClass));


            //获取ctClass对象对应的Class对象student
            Class objClass = ctClass.toClass();
            o = objClass.newInstance();
            Field fu = objClass.getDeclaredField(fieldName);
            fu.setAccessible(true);
            fu.set(o, fieldValue);
            BeanUtils.copyProperties(obj, o);
        } catch (Exception e) {
            Precondition.assertion(false, ReturnCode.ERROR_SERVER_REFLECT_FIELD);
        }
        return o;
    }

    private static String firstUpper(String fieldName) {
        StringBuilder sb = new StringBuilder(fieldName);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        fieldName = sb.toString();
        return fieldName;
    }

}
