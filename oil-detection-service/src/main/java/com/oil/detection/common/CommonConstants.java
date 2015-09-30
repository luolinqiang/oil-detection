package com.oil.detection.common;

public class CommonConstants {
    public final static String DEFAULT_ENCODING = "UTF-8";
    public static String OIL_DETECTION_PIN_COOKIE = "_pin_";
    public static final String SEPARATOR = "|";
    public static final String COOKIE_FORMAT = "%s|%s|%s|%s";

    public static class Common {
        public final static Long STATE_0 = 0l;
        public final static Long STATE_1 = 2l;
        public final static Long STATE_2 = 3l;
        public final static Long STATE_3 = 3l;
        public final static Long STATE_4 = 4l;
    }

    public static class User {
        public final static Integer USER_TYPE_C = 1;
        public final static Integer USER_TYPE_B = 2;
    }

    public static class HomeSetting {
        public final static Integer TYPE_BANNER = 1;
        public final static Integer TYPE_DIRECTSALE = 2;
        public final static Integer TYPE_CSBJ = 3;
        public final static Integer TYPE_LINK = 3;
    }
}