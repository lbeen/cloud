package com.mes.common.log.utils;

import com.mes.common.Result;
import com.mes.common.log.dto.LogDTO;
import com.mes.common.log.feign.LogFeignService;
import com.mes.common.sys.utils.ServerUtils;
import com.mes.common.sys.utils.SpringContextUtils;

import java.time.LocalDateTime;

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
        LogDTO logDTO = new LogDTO(LocalDateTime.now(), ServerUtils.serverName(), ServerUtils.serverIP(),
                clazz.getSimpleName(), level, "user", content);
        Result<Integer> result = LOG_SERVICE.log(logDTO);
        return result.getData();
    }
}
