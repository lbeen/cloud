package com.mes.common.log.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    public static int LEVEL_INFO = 0;
    public static int LEVEL_ERROR = 1;

    private final LocalDateTime time;
    private final String server;
    private final String serverIP;
    private final String clazz;
    private final int level;
    private final String user;
    private final String content;
}
