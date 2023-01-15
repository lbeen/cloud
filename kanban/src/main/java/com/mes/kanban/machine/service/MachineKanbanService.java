package com.mes.kanban.machine.service;

import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.ScrollData;

public interface MachineKanbanService {
    /**
     * 毛棒在线统计
     *
     * @param DS       数据源
     * @param workshop 车间
     */
    EchartsData maoOnlineStatistics(String DS, String workshop);

    /**
     * 毛棒在线列表
     *
     * @param DS       数据源
     * @param workshop 车间
     */
    ScrollData maoOnlineList(String DS, String workshop, int hours);

}