package com.mes.system.user.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mes.common.system.dto.UserDTO;
import com.mes.common.utils.Result;
import com.mes.system.user.entity.SystemUser;
import com.mes.system.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

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
