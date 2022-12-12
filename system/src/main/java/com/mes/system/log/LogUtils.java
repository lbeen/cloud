package com.mes.system.log;

import com.mes.common.log.dto.LogDTO;
import com.mes.system.log.service.LogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LogUtils {
    @Value("${spring.application.name}.replace('-service', '')")
    private static String SERVER;
    private static LogService LOG_SERVICE;

    public LogUtils(LogService logService) {
        LOG_SERVICE = logService;
    }

    public static int logInfo(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    public static int logError(Class<?> clazz, String content) {
        return log(clazz, LogDTO.LEVEL_ERROR, content);
    }

    private static int log(Class<?> clazz, int level, String content) {

        LogDTO logDTO = new LogDTO(LocalDateTime.now(), SERVER, clazz.getSimpleName(), level, "user", "serverIP",
                "clientIp", content);
        return LOG_SERVICE.log(logDTO);
    }
}
