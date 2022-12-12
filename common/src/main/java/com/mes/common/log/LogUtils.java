package com.mes.common.log;

import com.mes.common.Result;
import com.mes.common.log.dto.LogDTO;
import com.mes.common.log.feign.LogFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class LogUtils {
    @Value("${spring.application.name}.replace('-service', '')")
    private static String SERVER;
    private static LogFeignService LOG_SERVICE;

    @Autowired
    private LogFeignService logService;

    @PostConstruct
    public void init() {
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
        Result<Integer> result = LOG_SERVICE.log(logDTO);
        return result.getData();
    }
}
