package com.oil.detection.domain.resultxml;

import com.oil.detection.util.JsonMessageConverter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.mutable.Mutable;

import java.io.Serializable;

/**
 * Writeable的实例，应该能够转换JSON, 还能够支持Java序列化，还能够以一种优美的方式toString 实在是太强大了。
 *
 * @author dengliang@jd.com
 */
public class Writeable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String json = null;
    public static final ToStringStyle TO_STRING_STYLE = new BestToStringStyle();


    public String toJson() {
        if (this instanceof Mutable) {
            return JsonMessageConverter.safeWrite(this);
        } else {
            if (json == null) {
                json = JsonMessageConverter.safeWrite(this);
            }
            return json;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, TO_STRING_STYLE);
    }

    private static class BestToStringStyle extends ToStringStyle {

        private static final long serialVersionUID = 1L;

        public BestToStringStyle() {
            setUseShortClassName(true);
            setUseIdentityHashCode(false);
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
            if (value != null) {
                super.append(buffer, fieldName, value, fullDetail);
            }
        }

        @Override
        public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {
            if (array != null && array.length > 0) {
                super.append(buffer, fieldName, array, fullDetail);
            }
        }
    }
}
