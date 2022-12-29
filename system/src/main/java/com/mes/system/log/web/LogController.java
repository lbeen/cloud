package com.mes.system.log.web;

import com.google.common.collect.Maps;
import com.mes.common.utils.Result;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.pojo.Page;
import com.mes.system.log.service.SystemLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
                                     String user, Integer level, String content) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        param.put("user", user);
        param.put("level", level);
        param.put("content", "%" + content + "%");
        return Result.success(systemLogService.queryLogPage(param));
    }
}
