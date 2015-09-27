package com.oil.detection.common;

/**
 * JSS运行常量集
 */
public class CommonConstants {
    public final static String DEFAULT_ENCODING = "UTF-8";
    public static final String JIMDB_CACHE = "jimdb.cache";
    public static final String JMQ_POWER = "jmq.power";
    public final static String UMP_PREFIX = "jss.paipai.photo.";

    public static class Config {
        public static final String DELETE_RETRY_COUNT = "delete.retry.count";
    }


    public static class Index {
        public final static Integer FILE_TYPE_FILE_DIR = 0;
        public final static Integer FILE_TYPE_DIR = 2;
        public final static Integer FILE_TYPE_FILE = 1;

        public final static Integer INTERFACE_SEARCH_CASES = 1;
    }
}