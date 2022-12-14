package com.mes.system.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mes.common.log.dto.LogDTO;
import com.mes.system.log.entity.SystemLog;

public interface LogService extends IService<SystemLog> {
    int saveLog(LogDTO logDTO);
}
