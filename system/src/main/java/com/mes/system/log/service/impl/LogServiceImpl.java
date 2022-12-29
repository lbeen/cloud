package com.mes.system.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mes.mvc.log.dto.LogDTO;
import com.mes.mvc.pojo.Page;
import com.mes.mvc.utils.PageUtils;
import com.mes.system.log.entity.SystemLog;
import com.mes.system.log.mapper.LogMapper;
import com.mes.system.log.service.SystemLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SystemLog> implements SystemLogService {
    @Override
    public int saveLog(LogDTO logDTO) {
        SystemLog systemLog = new SystemLog();
        BeanUtils.copyProperties(logDTO, systemLog);
        save(systemLog);
        return systemLog.getId();
    }

    public Page queryLogPage(Map<String, Object> param) {
        return PageUtils.getPage(this.baseMapper::queryLogCount, this.baseMapper::queryLogPage, param);
    }
}
