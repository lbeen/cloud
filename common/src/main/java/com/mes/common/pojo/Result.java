package com.mes.common.pojo;

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
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(CODE_SUCCESS, "操作成功", data);
    }

    public static Result<Object> error(String msg) {
        return new Result<>(-1, msg, null);
    }
}
