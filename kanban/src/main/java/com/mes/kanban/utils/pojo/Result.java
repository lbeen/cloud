package com.mes.kanban.utils.pojo;

import lombok.Getter;

/**
 * 与form交互对象
 */
@Getter
public class Result {
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
    private final Object data;

    public static Result success(String msg) {
        return success(msg, null);
    }

    public static Result success(Object data) {
        return success(null, data);
    }

    public static Result success(String msg, Object data) {
        return new Result(0, msg, data);
    }

    private Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
