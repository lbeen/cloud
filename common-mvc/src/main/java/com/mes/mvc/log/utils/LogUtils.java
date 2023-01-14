package com.mes.mvc.log.utils;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.mes.common.server.utils.ServerUtils;
import com.mes.common.server.utils.SpringContextUtils;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.log.service.LogService;
import com.mes.mvc.utils.MvcAuthUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志工具类
 */
@Slf4j
public class LogUtils {
    private static final LogService LOG_SERVICE = SpringContextUtils.getBean(LogService.class);

    public static int logInfo(String content) {
        return log(MvcAuthUtils.getUsername(), LogDTO.LEVEL_INFO, content);
    }

    public static int logInfo(String username, String content) {
        return log(username, LogDTO.LEVEL_INFO, content);
    }

    public static int logError(Throwable throwable) {
        log.error("记录错误日志", throwable);

        StringBuilder content = new StringBuilder().append(throwable.getMessage());

        StackTraceElement[] stackTraces = throwable.getStackTrace();
        if (ArrayUtils.isNotEmpty(stackTraces)) {
            for (StackTraceElement stackTrace : stackTraces) {
                content.append("\n").append(stackTrace);
                if (content.length() > 3500) {
                    content.setLength(3500);
                    break;
                }
            }
        }
        return log(MvcAuthUtils.getUsername(), LogDTO.LEVEL_ERROR, content.toString());
    }

    private static int log(String user, int level, String content) {
        LogDTO logDTO = new LogDTO();
        logDTO.setServer(ServerUtils.serverName());
        logDTO.setServerIP(ServerUtils.serverIP());
        logDTO.setServerPort(ServerUtils.serverPort());
        logDTO.setOperateUser(user);
        logDTO.setClientIP(MvcAuthUtils.getClientIP());
        logDTO.setLogLevel(level);
        logDTO.setLogContent(content);
        return LOG_SERVICE.saveLog(logDTO);
    }
}
