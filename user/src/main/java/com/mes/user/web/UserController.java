package com.mes.user.web;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("get")
    public Map<String, String> get(String id) {
        Map<String, String> data = Maps.newHashMap();
        data.put("id", id);
        data.put("name", "test" + id);
        data.put("userPort", port);
        return data;
    }
}
