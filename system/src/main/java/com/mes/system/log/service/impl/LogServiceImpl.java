package com.mes.system.log.service.impl;

import com.mes.common.log.dto.LogDTO;
import com.mes.system.log.service.LogService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LogServiceImpl implements LogService {
    @Override
    public int log(LogDTO logDTO) {
        String info = "time:" + logDTO.getTime();
        info += " server:" + logDTO.getServer();
        info += " clazz:" + logDTO.getClazz();
        info += " level:" + logDTO.getLevel();
        info += " user:" + logDTO.getUser();
        info += " serverIP:" + logDTO.getServerIP();
        info += " clientIp:" + logDTO.getClientIp();
        info += " content:" + logDTO.getContent();
        System.out.println(info);
        return new Random().nextInt(100);
    }
}
