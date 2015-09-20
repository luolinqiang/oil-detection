package com.jd.jss.index.util;

import com.google.common.base.Throwables;
import com.jd.jss.index.common.CommonConstants;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

public class JsonMessageConverter {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String write(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    public static String safeWrite(Object object) {
        try {
            return write(object);
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> T read(Class<T> type, InputStream is) throws Exception {
        return (T) objectMapper.readValue(is, objectMapper.constructType(type));
    }

    public static Map<String, Object> read(String jsonMap) throws Exception {
        return objectMapper.readValue(jsonMap, objectMapper.constructType(Map.class));
    }

    public static <T> T read(Class<T> type, String json) throws Exception {
        return read(type, new ByteArrayInputStream(json.getBytes(CommonConstants.DEFAULT_ENCODING)));
    }

    public static <T> T read(Class<T> type, byte[] data) throws Exception {
        return (T) objectMapper.readValue(data, objectMapper.constructType(type));
    }
}