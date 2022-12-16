package com.mes.system.log.web;

import com.mes.common.log.dto.LogDTO;
import com.mes.common.log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    @PostMapping("saveLog")
    public int saveLog(@RequestBody LogDTO logDTO) {
        return logService.saveLog(logDTO);
    }
}
