package com.oil.detection.common;

public class CommonConstants {
    public final static String DEFAULT_ENCODING = "UTF-8";
    public static String OIL_DETECTION_PIN_COOKIE = "_pin_";
    public static final String SEPARATOR = "|";
    public static final String COOKIE_FORMAT = "%s|%s|%s|%s";
    public static final String PHONE_MSG_TEMPLATE = "您的验证码：%s，请勿想任何人泄露。";

    public static class Common {
        public final static Integer STATE_0 = 0;
        public final static Integer STATE_1 = 1;
        public final static Integer STATE_2 = 2;
        public final static Integer STATE_3 = 3;
        public final static Integer STATE_4 = 4;


        public final static Integer TYPE_0 = 0;
        public final static Integer TYPE_1 = 1;
        public final static Integer TYPE_2 = 2;
        public final static Integer TYPE_3 = 3;
        public final static Integer TYPE_4 = 4;
    }

    public static class Product {
        public final static Integer TYPE_DIESEL = 1;
        public final static Integer TYPE_PETROL = 2;
    }

    public static class User {
        public final static Integer USER_TYPE_C = 1;
        public final static Integer USER_TYPE_B = 2;
    }

    public static class HomeSetting {
        public final static Integer TYPE_BANNER = 1;
        public final static Integer TYPE_DIRECTSALE = 2;
        public final static Integer TYPE_CSBJ = 3;
        public final static Integer TYPE_LINK = 4;
    }
}