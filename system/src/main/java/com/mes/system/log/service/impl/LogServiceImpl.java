package com.mes.system.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.log.service.LogService;
import com.mes.mvc.utils.MvcAuthUtils;
import com.mes.system.log.entity.SystemLog;
import com.mes.system.log.mapper.LogMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SystemLog> implements LogService {
    @Override
    public int saveLog(LogDTO logDTO) {
        SystemLog systemLog = new SystemLog();
        BeanUtils.copyProperties(logDTO, systemLog);
        save(systemLog);
        return systemLog.getId();
    }
}
