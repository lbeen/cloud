package com.mes.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    /**
     * 成功代码
     */
    private static final int CODE_SUCCESS = 0;
    /**
     * 代码（0：成功，其他失败）
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(CODE_SUCCESS, message, data);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(-1, message, null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }
}
