package com.mes.system.user.web;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.mes.common.utils.AuthUtils;
import com.mes.mvc.log.utils.LogUtils;
import com.mes.mvc.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @PostMapping("login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        if ("admin".equals(username) && "123".equals(password)) {
            Map<String, Object> subject = Maps.newHashMap();
            subject.put("username", username);
            String token = AuthUtils.createJWT(JSON.toJSONString(subject));
            LogUtils.logInfo(UserController.class, "登录成功：" + username);
            return Result.success(Collections.singletonMap(AuthUtils.TOKEN_KEY, token));
        }
        return Result.error("登录失败");
    }

    @RequestMapping("get")
    public Map<String, String> get(String id) {
        Map<String, String> data = Maps.newHashMap();
        data.put("id", id);
        data.put("name", "test" + id);
        LogUtils.logInfo(UserController.class, "获取用户id=" + id);
        return data;
    }
}
