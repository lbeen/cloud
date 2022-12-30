package com.mes.system.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mes.mvc.log.service.LogService;
import com.mes.mvc.pojo.Page;
import com.mes.system.log.entity.SystemLog;

import java.util.Map;

public interface SystemLogService extends LogService, IService<SystemLog> {
    Page queryLogPage(Map<String, Object> param);
}
