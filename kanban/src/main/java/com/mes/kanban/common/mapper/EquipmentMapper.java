package com.mes.kanban.common.mapper;

import com.mes.mvc.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquipmentMapper {
    /**
     * 设备总数
     */
    Double equipmentCount(Map<String, Object> param);

    /**
     * 待料时间分工序统计
     *
     * @param param 查询参数
     */
    List<Record> waitingTimeByProcess(Map<String, Object> param);

    /**
     * 待料时间分设备统计
     *
     * @param param 查询参数
     */
    List<Record> waitingTimeByEquipment(Map<String, Object> param);

    /**
     * 故障时间分工序统计
     *
     * @param param 查询参数
     */
    List<Record> maintenanceTimeByProcess(Map<String, Object> param);

    /**
     * 故障时间分设备统计
     *
     * @param param 查询参数
     */
    List<Record> maintenanceTimeByEquipment(Map<String, Object> param);

    /**
     * 运行设备总数切断
     */
    Double runningEquipmentCountCutoff(Map<String, Object> param);

    /**
     * 运行设备总数切方
     */
    Double runningEquipmentCountButt(Map<String, Object> param);

    /**
     * 运行设备总数抛光
     */
    Double runningEquipmentCountPolishing(Map<String, Object> param);
}