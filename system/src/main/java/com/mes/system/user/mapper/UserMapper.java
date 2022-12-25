package com.mes.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mes.system.log.entity.SystemLog;
import com.mes.system.user.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SystemUser> {}
