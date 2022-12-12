package com.mes.view.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "system-service", path = "user")
public interface UserFeignService {
    @RequestMapping("get")
    Map<String, String> get(@RequestParam("id") String id);
}
