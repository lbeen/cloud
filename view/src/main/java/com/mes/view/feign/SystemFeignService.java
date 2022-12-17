package com.mes.view.feign;

import com.mes.common.sys.constants.Servers;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.log.service.LogService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = Servers.SYSTEM)
public interface SystemFeignService extends LogService {
    @PostMapping("log/saveLog")
    @Override
    int saveLog(@RequestBody LogDTO logDTO);

    @RequestMapping("user/get")
    Map<String, String> get(@RequestParam("id") String id);
}
