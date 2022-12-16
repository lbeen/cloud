package com.mes.common.log.service;

import com.mes.common.log.dto.LogDTO;

public interface LogService {
    int saveLog(LogDTO logDTO);
}
