package com.mes.system.user.web;

import com.google.common.collect.Maps;
import com.mes.common.system.dto.TokenUserInfo;
import com.mes.common.system.dto.UserDTO;
import com.mes.common.utils.AuthUtils;
import com.mes.common.utils.Result;
import com.mes.mvc.utils.MvcAuthUtils;
import com.mes.system.user.entity.SystemUser;
import com.mes.system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class LoginController {
    private final UserService userService;

    @PostMapping("login")
    public Result<Map<String, Object>> login(@RequestBody UserDTO userDTO) {
        SystemUser systemUser = userService.getUserByUsername(userDTO.getUsername());
        if (systemUser == null) {
            return Result.error("用户不存在");
        }
        if (!systemUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error("用户密码错误");
        }

        TokenUserInfo tokenUserInfo = new TokenUserInfo();
        BeanUtils.copyProperties(systemUser, tokenUserInfo);
        String token = AuthUtils.createToken(tokenUserInfo);

        Map<String, Object> data = Maps.newHashMap();
        data.put(AuthUtils.TOKEN_KEY, token);
        data.put(AuthUtils.USER_KEY, tokenUserInfo);
        return Result.success(data);
    }

    @PostMapping("refreshToken")
    public Result<String> refreshToken(@RequestBody TokenUserInfo userInfo) {
        TokenUserInfo tokenUserInfo = MvcAuthUtils.getUserInfo();
        if (!userInfo.equals(tokenUserInfo)) {
            return Result.error("请重新登录");
        }

        String token = AuthUtils.createToken(tokenUserInfo);
        return Result.success(token);
    }
}
