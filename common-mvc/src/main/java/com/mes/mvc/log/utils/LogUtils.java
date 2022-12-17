package com.mes.mvc.log.utils;

import com.mes.common.sys.utils.ServerUtils;
import com.mes.common.sys.utils.SpringContextUtils;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.log.service.LogService;

/**
 * 日志工具类
 */
public class LogUtils {
    private static final LogService LOG_SERVICE = SpringContextUtils.getBean(LogService.class);

    public static int logInfo(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_INFO, content);
    }

    public static int logError(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    private static int log(Class<?> clazz, int level, String content) {
        LogDTO logDTO = new LogDTO();
        logDTO.setServer(ServerUtils.serverName());
        logDTO.setServerIP(ServerUtils.serverIP());
        logDTO.setServerPort(ServerUtils.serverPort());
        logDTO.setClazz(clazz.getSimpleName());
        logDTO.setLevel(level);
        logDTO.setContent(content);
        return LOG_SERVICE.saveLog(logDTO);
    }
}
