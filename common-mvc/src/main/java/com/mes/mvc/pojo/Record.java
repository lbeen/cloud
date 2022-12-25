package com.mes.mvc.pojo;

import oracle.sql.TIMESTAMP;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

/**
 * 数据记录（key全为小写）
 */
public class Record extends HashMap<String, Object> {
    @Override
    public Object put(String key, Object value) {
        return super.put(key.toLowerCase(), value);
    }

    @Override
    public Object get(Object key) {
        return super.get(key.toString().toLowerCase());
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return super.getOrDefault(key.toString().toLowerCase(), defaultValue);
    }

    @Override
    public Object remove(Object key) {
        return super.remove(key.toString().toLowerCase());
    }

    /**
     * 获取字符串
     */
    public String getString(String key) {
        return Objects.toString(get(key), null);
    }

    /**
     * 获取Integer
     */
    public Integer getInteger(String key) {
        Object value = get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) get(key);
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).intValue();
        }
        if (value instanceof Long) {
            return ((Long) value).intValue();
        }
        throw new RuntimeException(value.getClass() + "can not cast to Long");
    }

    /**
     * 获取Integer
     */
    public Double getDouble(String key) {
        Object value = get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Double) {
            return (Double) get(key);
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        if (value instanceof Long) {
            return ((Long) value).doubleValue();
        }
        if (value instanceof Float) {
            return ((Float) value).doubleValue();
        }
        throw new RuntimeException(value.getClass() + "can not cast to Double");
    }

    public LocalDateTime getLocalDateTime(String key){
        Object value = get(key);
        try {
            return ((TIMESTAMP) value).toLocalDateTime();
        } catch (SQLException e) {
            throw new RuntimeException(value.getClass() + "can not cast to LocalDateTime");
        }
    }
}
