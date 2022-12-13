package com.mes.system.log;

import com.mes.common.log.dto.LogDTO;
import com.mes.common.sys.utils.ServerUtils;
import com.mes.common.sys.utils.SpringContextUtils;
import com.mes.system.log.service.LogService;

import java.time.LocalDateTime;

public class LogUtils {
    private static final LogService LOG_SERVICE = SpringContextUtils.getBean(LogService.class);

    public static int logInfo(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    public static int logError(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    private static int log(Class<?> clazz, int level, String content) {
        LogDTO logDTO = new LogDTO(LocalDateTime.now(), ServerUtils.serverName(), ServerUtils.serverIP(),
                clazz.getSimpleName(), level, "user", content);
        return LOG_SERVICE.log(logDTO);
    }
}
