package com.mes.system.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mes.mvc.pojo.Record;
import com.mes.system.log.entity.SystemLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogMapper extends BaseMapper<SystemLog> {

    int queryLogCount(Map<String, Object> param);

    List<Record> queryLogPage(Map<String, Object> param);
}
