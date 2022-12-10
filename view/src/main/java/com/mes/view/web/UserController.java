package com.mes.view.web;

import com.mes.view.feign.UserFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("view/user")
public class UserController {
    private final UserFeignService userFeignService;

    @Value("${server.port}")
    private String port;

    @RequestMapping("getUser")
    public Map<String, String> get(String id) {
        Map<String, String> data = userFeignService.get(id);
        data.put("viwPort", port);
        return data;
    }
}
