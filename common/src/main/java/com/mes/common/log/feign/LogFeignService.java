package com.mes.common.log.feign;

import com.mes.common.Result;
import com.mes.common.log.dto.LogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "system-service", path = "log")
public interface LogFeignService {
    @PostMapping("log")
    Result<Integer> log(@RequestBody LogDTO logDTO);
}
