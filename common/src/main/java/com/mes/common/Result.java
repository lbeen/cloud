package com.mes.common;

import lombok.Getter;

@Getter
public class Result<T> {
    /**
     * 成功代码
     */
    private static final int CODE_SUCCESS = 0;
    /**
     * 代码（0：成功，其他失败）
     */
    private final int code;
    /**
     * 返回信息
     */
    private final String msg;
    /**
     * 返回数据
     */
    private final T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(CODE_SUCCESS, "操作成功", data);
    }

    public static Result<Object> error(String msg) {
        return new Result<>(-1, msg, null);
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
