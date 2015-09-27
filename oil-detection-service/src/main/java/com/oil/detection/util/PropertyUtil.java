package com.oil.detection.util;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置工具类，用于取配置文件中的键值信息
 */
public final class PropertyUtil {

    private static String configFilePath = "/conf/config.properties";

    public static Properties configProperties = null; // 配置属性集

    /**
     * 根据配置文件构建配置工具类
     *
     * @param configFilePath
     *            配置文件路径
     * @throws Exception
     *             通用异常
     */
    static {
        try {
            configProperties = init(configFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 被始化本工具类
     *
     * @param configFilePath 配置文件路径
     * @throws Exception 通用异常
     */
    private static Properties init(String configFilePath) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = PropertyUtil.class.getResourceAsStream(configFilePath);
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            properties.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            bf.close();
            inputStream.close();
        }
        return properties;
    }

    /**
     * 得到默认类实例对象的属性集
     *
     * @return 属性集
     */
    public final static Properties getProperties() {
        return configProperties;
    }

    public final static String getValue(String key) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return configProperties.getProperty(key);
    }

    public final static int getIntValue(String key) {
        String value = getValue(key);
        return Integer.parseInt(value);
    }

    public final static boolean getBooleanValue(String key) {
        String value = getValue(key);
        return Boolean.parseBoolean(value);
    }

    /**
     * 得到字符串值属性
     *
     * @param key 键的名称
     * @return 字符串值
     */
    public final static String getString(String key) {
        return configProperties.getProperty(key);
    }

}
