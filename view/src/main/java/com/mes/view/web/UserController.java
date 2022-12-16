package com.mes.view.web;

import com.mes.common.log.utils.LogUtils;
import com.mes.view.feign.SystemFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final SystemFeignService userFeignService;

    @RequestMapping("get")
    public Map<String, String> get(String id) {
        LogUtils.logInfo(UserController.class, "获取用户id=" + id);
        return userFeignService.get(id);
    }
}
