package com.mes.common.sys.utils;

import com.mes.common.sys.bean.ServerConfig;

public class ServerUtils {
    private static final ServerConfig SERVER_CONFIG = SpringContextUtils.getBean(ServerConfig.class);

    /**
     * 当前服务的名称
     */
    public static String serverName() {
        return SERVER_CONFIG.getServerName();
    }

    /**
     * 当前服务的端口
     */
    public static String serverIP() {
        return SERVER_CONFIG.getServerIP();
    }

    /**
     * 当前服务的IP
     */
    public static String serverPort() {
        return SERVER_CONFIG.getServerPort();
    }
}
