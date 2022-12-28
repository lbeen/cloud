package com.mes.system.user.web;

import com.mes.common.system.dto.TokenUserInfo;
import com.mes.common.system.dto.UserDTO;
import com.mes.common.utils.Result;
import com.mes.mvc.utils.MvcAuthUtils;
import com.mes.system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PostMapping("saveUser")
    public Result<Object> saveUser(@RequestBody @Valid UserDTO userDTO) {
        if (userService.getUserByUsername(userDTO.getUsername()) != null) {
            return Result.error(userDTO.getUsername() + "已存在");
        }
        userService.saveUser(userDTO);
        return Result.message("保存成功");
    }

    @PostMapping("changePassword")
    public Result<Object> changePassword(@RequestBody @Valid UserDTO userDTO) {
        TokenUserInfo userInfo = MvcAuthUtils.getUserInfo();
        if (userInfo == null || !userInfo.getUsername().equals(userDTO.getUsername())) {
            return Result.error("登录用户错误");
        }
        userService.changePassword(userInfo.getId(), userDTO.getPassword());
        return Result.message("保存成功");
    }
}
