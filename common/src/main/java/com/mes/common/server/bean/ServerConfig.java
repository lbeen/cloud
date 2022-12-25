package com.mes.common.server.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * 服务配置类
 */
@Slf4j
@Component
public class ServerConfig {
    /**
     * 当前服务的名称
     */
    @Value("${spring.application.name}")
    private String serverName;
    /**
     * 当前服务的端口
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 当前服务的名称
     */
    public String getServerName() {
        return serverName.replace("-service", "");
    }

    /**
     * 当前服务的端口
     */
    public String getServerIP() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (Exception e) {
            log.error("获取服务器IP失败", e);
            return "";
        }
    }

    /**
     * 当前服务的IP
     */
    public String getServerPort() {
        return serverPort;
    }
}
