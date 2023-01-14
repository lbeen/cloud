package com.mes.mvc.log.dto;

import lombok.Data;

@Data
public class LogDTO {
    public static int LEVEL_INFO = 0;
    public static int LEVEL_ERROR = 1;

    private String server;
    private String serverIP;
    private String serverPort;
    private String clientIP;
    private String operateUser;
    private int logLevel;
    private String logContent;
}
