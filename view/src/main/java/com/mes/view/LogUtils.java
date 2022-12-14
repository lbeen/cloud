package com.mes.view;

import com.mes.common.log.dto.LogDTO;
import com.mes.common.log.feign.LogFeignService;
import com.mes.common.sys.utils.ServerUtils;

/**
 * 日志工具类
 */
public class LogUtils {
    private static final LogFeignService LOG_SERVICE = SpringContextUtils.getBean(LogFeignService.class);

    public static int logInfo(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    public static int logError(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    private static int log(Class<?> clazz, int level, String content) {
        LogDTO logDTO = new LogDTO();
        logDTO.setServer(ServerUtils.serverName());
        logDTO.setServerIP(ServerUtils.serverIP());
        logDTO.setClazz(clazz.getSimpleName());
        logDTO.setLevel(level);
        logDTO.setContent(content);
        return LOG_SERVICE.log(logDTO).getData();
    }
}
