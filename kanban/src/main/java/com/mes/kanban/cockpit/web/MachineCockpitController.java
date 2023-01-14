package com.mes.kanban.cockpit.web;

import com.mes.common.utils.Result;
import com.mes.kanban.cockpit.service.MachineCockpitService;
import com.mes.kanban.utils.DSUtils;
import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.EchartsSingleData;
import com.mes.mvc.pojo.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cockpit/machine")
@RequiredArgsConstructor
public class MachineCockpitController {
    private final MachineCockpitService machineCockpitService;

    /**
     * 分工序产量统计
     *
     * @param factory 工厂
     */
    @GetMapping("/yieldByProcess")
    public Result<List<Map<String, Object>>> yieldByProcess(String factory) {
        return Result.success(machineCockpitService.yieldByProcess(DSUtils.getDGDataSource(factory)));
    }

    /**
     * 各个工序分机台产量统计
     *
     * @param factory 工厂
     * @param process 工序
     */
    @GetMapping("/yieldByEquipment")
    public Result<EchartsSingleData> yieldByEquipment(String factory, String process) {
        return Result.success(machineCockpitService.yieldByEquipment(DSUtils.getDGDataSource(factory), process));
    }

    /**
     * 各个工序在线统计
     *
     * @param factory 工厂
     */
    @GetMapping("/onlineByProcess")
    public Result<Object[]> onlineByProcess(String factory) {
        return Result.success(machineCockpitService.onlineByProcess(DSUtils.getDGDataSource(factory)));
    }

    /**
     * 各个工序分规格在线统计
     *
     * @param factory 工厂
     * @param process 工序
     */
    @GetMapping("/onlineBySpec")
    public Result<EchartsData> onlineBySpec(String factory, String process) {
        return Result.success(machineCockpitService.onlineBySpec(DSUtils.getDGDataSource(factory), process));
    }

    /**
     * 各个工序设备数据
     *
     * @param factory 工厂
     */
    @GetMapping("/equipmentByProcess")
    public Result<List<Map<String, Object>>> equipmentByProcess(String factory) {
        return Result.success(machineCockpitService.equipmentByProcess(DSUtils.getDGDataSource(factory)));
    }

    /**
     * 故障分机台
     *
     * @param factory 工厂
     * @param process 工序
     */
    @GetMapping("/maintenanceTimeByEquipment")
    public Result<EchartsData> maintenanceTimeByEquipment(String factory, String process) {
        return Result.success(
                machineCockpitService.maintenanceTimeByEquipment(DSUtils.getDGDataSource(factory), process));
    }

    /**
     * 待料分机台
     *
     * @param factory 工厂
     * @param process 工序
     */
    @GetMapping("/waitingTimeByEquipment")
    public Result<List<Record>> waitingTimeByEquipment(String factory, String process) {
        return Result.success(machineCockpitService.waitingTimeByEquipment(DSUtils.getDGDataSource(factory), process));
    }

    /**
     * 各个工序交料数据
     *
     * @param factory 工厂
     */
    @GetMapping("/payLengthByProcess")
    public Result<EchartsSingleData> payLengthByProcess(String factory) {
        return Result.success(machineCockpitService.payLengthByProcess(DSUtils.getDGDataSource(factory)));
    }

    /**
     * 交料数据分原因
     *
     * @param factory 工厂
     * @param process 工序
     */
    @GetMapping("/payLengthByReason")
    public Result<EchartsSingleData> payLengthByReason(String factory, String process) {
        return Result.success(machineCockpitService.payLengthByReason(DSUtils.getDGDataSource(factory), process));
    }
}
