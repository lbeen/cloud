package com.mes.system.user.web;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Maps;
import com.mes.common.system.dto.UserDTO;
import com.mes.common.utils.AuthUtils;
import com.mes.mvc.log.utils.LogUtils;
import com.mes.mvc.pojo.Result;
import com.mes.system.user.entity.SystemUser;
import com.mes.system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> map, HttpServletResponse response) {
        String username = map.get("username");
        SystemUser systemUser = userService.getUserByUsername(username);
        if (systemUser == null) {
            return Result.error("用户不存在");
        }
        String password = map.get("password");
        if (!systemUser.getPassword().equals(password)) {
            return Result.error("用户密码错误");
        }

        Map<String, Object> userInfo = Maps.newHashMap();
        userInfo.put("id", systemUser.getId());
        userInfo.put("username", systemUser.getUsername());
        String token = AuthUtils.createToken(JSON.toJSONString(userInfo));

        Map<String,Object> data = Maps.newHashMap();
        data.put("token", token);
        data.put("userInfo", userInfo);
        return Result.success(data);
    }

    @PostMapping("saveUser")
    public Result<Object> saveUser(@RequestBody UserDTO userDTO) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getUsername, userDTO.getUsername());
        if (userService.getUserByUsername(userDTO.getUsername()) != null) {
            return Result.error(userDTO.getUsername() + "已存在");
        }
        userService.saveUser(userDTO);
        return Result.error("保存成功");
    }
}
