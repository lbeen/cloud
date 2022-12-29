package com.mes.system.log.service;

import com.mes.mvc.log.service.LogService;
import com.mes.mvc.pojo.Page;

import java.util.Map;

public interface SystemLogService extends LogService {
    Page queryLogPage(Map<String,Object> param);
}
