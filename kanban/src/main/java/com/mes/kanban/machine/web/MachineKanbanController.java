package com.mes.kanban.machine.web;

import com.mes.common.utils.Result;
import com.mes.kanban.machine.service.MachineKanbanService;
import com.mes.kanban.utils.DSUtils;
import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.ScrollData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("machine")
@RequiredArgsConstructor
public class MachineKanbanController {
    private final MachineKanbanService machineKanbanService;

    /**
     * 毛棒在线统计
     *
     * @param factory  工厂
     * @param workshop 车间
     */
    @GetMapping("/maoOnlineStatistics")
    public Result<EchartsData> maoOnlineStatistics(String factory, String workshop) {
        return Result.success(machineKanbanService.maoOnlineStatistics(DSUtils.getDGDataSource(factory), workshop));
    }

    /**
     * 毛棒在线统计
     *
     * @param factory  工厂
     * @param workshop 车间
     */
    @GetMapping("/maoOnlineList")
    public Result<ScrollData> maoOnlineList(String factory, String workshop, int hours) {
        return Result.success(machineKanbanService.maoOnlineList(DSUtils.getDGDataSource(factory), workshop, hours));
    }
}