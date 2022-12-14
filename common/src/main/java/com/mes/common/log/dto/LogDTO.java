package com.mes.common.log.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    public static int LEVEL_INFO = 0;
    public static int LEVEL_ERROR = 1;

    private int id;
    private LocalDateTime createTime;
    private String server;
    private String serverIP;
    private String clazz;
    private String clientIP;
    private String user;
    private int level;
    private String content;
}