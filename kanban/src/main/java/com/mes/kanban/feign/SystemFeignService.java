package com.mes.kanban.feign;

import com.mes.common.server.constants.Servers;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.log.service.LogService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = Servers.SYSTEM)
public interface SystemFeignService extends LogService {
    @PostMapping("log/saveLog")
    @Override
    int saveLog(@RequestBody LogDTO logDTO);
}
