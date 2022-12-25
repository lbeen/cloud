package com.mes.mvc.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.StringUtils;

public class QueryCondition<T> extends LambdaQueryWrapper<T> {
    /**
     * 如果值不为空，添加等于条件
     *
     * @param column 字段名
     * @param value  值
     */
    public void eqIfNotBlank(SFunction<T, String> column, String value) {
        if (StringUtils.hasText(value)) {
            this.eq(column, value);
        }
    }
}
