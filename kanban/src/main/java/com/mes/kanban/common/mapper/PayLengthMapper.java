package com.mes.kanban.common.mapper;

import com.mes.mvc.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PayLengthMapper {
    /**
     * 机加交料分工序
     */
    List<Record> machinePayLengthByProcess(Map<String, Object> param);

    /**
     * 机加交料分原因
     */
    List<Record> machinePayLengthByReason(Map<String, Object> param);
}
