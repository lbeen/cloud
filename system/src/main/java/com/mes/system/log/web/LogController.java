package com.mes.system.log.web;

import com.mes.common.Result;
import com.mes.common.log.dto.LogDTO;
import com.mes.system.log.entity.SystemLog;
import com.mes.system.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @PostMapping("log")
    public Result<Integer> log(@RequestBody LogDTO logDTO) {
        SystemLog systemLog = new SystemLog();
        BeanUtils.copyProperties(logDTO, systemLog);
        logService.save(systemLog);
        return Result.success(systemLog.getId());
    }
}