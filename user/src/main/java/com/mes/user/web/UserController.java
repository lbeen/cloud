package com.mes.user.web;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @RequestMapping("get")
    public Map<String, String> get(String id) {
        Map<String, String> data = Maps.newHashMap();
        data.put("id", id);
        data.put("name", "test" + id);
        return data;
    }
}
