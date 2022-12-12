package com.mes.system.user.web;

import com.google.common.collect.Maps;
import com.mes.system.log.LogUtils;
import org.springframework.beans.factory.annotation.Value;
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
        LogUtils.logInfo(UserController.class, "获取用户id=" + id);
        return data;
    }
}
