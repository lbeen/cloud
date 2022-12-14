package com.mes.system.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mes.system.log.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<SystemLog> {}
