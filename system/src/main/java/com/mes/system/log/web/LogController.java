package com.mes.system.log.web;

import com.google.common.collect.Maps;
import com.mes.common.utils.Result;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.pojo.Page;
import com.mes.system.log.entity.SystemLog;
import com.mes.system.log.service.SystemLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class LogController {
    private final SystemLogService systemLogService;

    @PostMapping("saveLog")
    public int saveLog(@RequestBody LogDTO logDTO) {
        return systemLogService.saveLog(logDTO);
    }

    @GetMapping("queryLogPage")
    public Result<Page> queryLogPage(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime,
                                     String user, Integer level, String content, Integer page, Integer pageSize) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("user", user);
        param.put("level", level);
        if (StringUtils.hasText(content)) {
            param.put("content", "%" + content + "%");
        }
        param.put("page", page);
        param.put("pageSize", pageSize);
        return Result.success(systemLogService.queryLogPage(param));
    }

    @GetMapping("queryLogById")
    public Result<SystemLog> queryLogById(String id) {
        return Result.success(systemLogService.getById(id));
    }
}
