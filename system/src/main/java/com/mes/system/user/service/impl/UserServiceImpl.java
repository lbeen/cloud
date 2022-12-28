package com.mes.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mes.common.system.dto.UserDTO;
import com.mes.system.user.entity.SystemUser;
import com.mes.system.user.mapper.UserMapper;
import com.mes.system.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements UserService {

    @Override
    public SystemUser getUserByUsername(String username) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getUsername, username);
        return getOne(wrapper);
    }

    @Transactional
    @Override
    public void saveUser(UserDTO userDTO) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(userDTO, systemUser);
        save(systemUser);
    }

    @Transactional
    @Override
    public void changePassword(String id, String password) {
        LambdaUpdateWrapper<SystemUser> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(SystemUser::getId, id);
        wrapper.set(SystemUser::getPassword, password);
        update(wrapper);
    }
}
