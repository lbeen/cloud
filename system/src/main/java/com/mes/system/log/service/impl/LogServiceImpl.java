package com.mes.system.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mes.system.log.entity.SystemLog;
import com.mes.system.log.mapper.LogMapper;
import com.mes.system.log.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SystemLog> implements LogService {}
