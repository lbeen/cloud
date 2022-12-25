package com.mes.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mes.common.system.dto.UserDTO;
import com.mes.system.user.entity.SystemUser;

public interface UserService extends IService<SystemUser> {
    SystemUser getUserByUsername(String username);

    void saveUser(UserDTO userDTO);
}
