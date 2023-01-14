package com.mes.kanban.cockpit.service;


import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.EchartsSingleData;
import com.mes.mvc.pojo.Record;

import java.util.List;
import java.util.Map;

/**
 * 驾驶舱service
 */
public interface MachineCockpitService {
    /**
     * 分工序产量统计
     *
     * @param DS 数据源
     */
    List<Map<String, Object>> yieldByProcess(String DS);

    /**
     * 各个工序分机台产量统计
     *
     * @param DS      数据源
     * @param process 工序
     */
    EchartsSingleData yieldByEquipment(String DS, String process);

    /**
     * 各个工序在线统计
     *
     * @param DS 数据源
     */
    Object[] onlineByProcess(String DS);

    /**
     * 各个工序分规格在线统计
     *
     * @param DS      数据源
     * @param process 工序
     */
    EchartsData onlineBySpec(String DS, String process);

    /**
     * 各个工序设备数据
     *
     * @param DS 数据源
     */
    List<Map<String, Object>> equipmentByProcess(String DS);

    /**
     * 故障分机台
     *
     * @param DS      数据源
     * @param process 工序
     */
    EchartsData maintenanceTimeByEquipment(String DS, String process);

    /**
     * 待料分机台
     *
     * @param DS      数据源
     * @param process 工序
     */
    List<Record> waitingTimeByEquipment(String DS, String process);

    /**
     * 各个工序交料数据
     *
     * @param DS 数据源
     */
    EchartsSingleData payLengthByProcess(String DS);
    /**
     * 交料数据分原因
     *
     * @param DS      数据源
     * @param process 工序
     */
    EchartsSingleData payLengthByReason(String DS, String process);
}
