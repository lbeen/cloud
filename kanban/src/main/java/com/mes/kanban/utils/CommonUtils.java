package com.mes.kanban.utils;

import com.google.common.collect.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Map;

/**
 * 公共工具
 */
public class CommonUtils {

    /**
     * 保留小数
     *
     * @param num   数字
     * @param scale 小数位数
     */
    public static double round(Double num, int scale) {
        if (num == null) {
            num = 0.0;
        }
        return new BigDecimal(num).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 获取map值排序后的key
     */
    public static String[] getSortValueKey(Map<String, Double> map) {
        return map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).map(
                Map.Entry::getKey).toArray(String[]::new);
    }

    /**
     * value增加
     */
    public static void increaseValue(Map<String, Double> map, String key, Double addValue) {
        if (addValue == null) {
            return;
        }
        Double value = map.get(key);
        if (value == null) {
            value = addValue;
        } else {
            value += addValue;
        }
        map.put(key, value);
    }

    /**
     * value增加
     */
    public static void increaseValue(Table<String, String, Double> table, String rowKey, String columnKey,
                                     Double addValue) {
        if (addValue == null) {
            return;
        }
        Double value = table.get(rowKey, columnKey);
        if (value == null) {
            value = addValue;
        } else {
            value += addValue;
        }
        table.put(rowKey, columnKey, value);
    }
}
