package com.mes.kanban.cockpit.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import com.mes.kanban.cockpit.service.MachineCockpitService;
import com.mes.kanban.common.mapper.EquipmentMapper;
import com.mes.kanban.common.mapper.OnlineMapper;
import com.mes.kanban.common.mapper.PayLengthMapper;
import com.mes.kanban.common.mapper.YieldMapper;
import com.mes.kanban.utils.Callable;
import com.mes.kanban.utils.CommonUtils;
import com.mes.kanban.utils.DataUtils;
import com.mes.kanban.utils.ParallelRunner;
import com.mes.kanban.utils.TimeUtils;
import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.EchartsSingleData;
import com.mes.mvc.pojo.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 驾驶舱service
 */
@Service
@RequiredArgsConstructor
public class MachineCockpitServiceImpl implements MachineCockpitService {
    private static final String CUTOFF = "切断";
    private static final String BUTT = "切方";
    private static final String POLISHING = "抛光";
    private static final String FINISH = "成品";
    private static final String XQD = "小切断";

    private static final long minDuration = 40;

    private final YieldMapper yieldMapper;
    private final OnlineMapper onlineMapper;
    private final EquipmentMapper equipmentMapper;
    private final PayLengthMapper payLengthMapper;

    /**
     * 分工序产量统计
     *
     * @param DS 数据源
     */
    @DS("#DS")
    @Override
    public List<Map<String, Object>> yieldByProcess(String DS) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> param = getTimeParam(now);

        double cutoffCapacity;
        double buttCapacity;
        double polishingCapacity;
        double finishCapacity;
        if (showYesterday(now)) {
            cutoffCapacity = CommonUtils.round(equipmentMapper.runningEquipmentCountCutoff(param) * 13.6, 2);
            buttCapacity = CommonUtils.round(equipmentMapper.runningEquipmentCountButt(param) * 1.98, 2);
            polishingCapacity = CommonUtils.round(equipmentMapper.runningEquipmentCountPolishing(param) * 1.2, 2);
            finishCapacity = CommonUtils.round(polishingCapacity - 5, 2);
        } else {
            double dayPercent = TimeUtils.nowOfShiftDayPercent(now);
            cutoffCapacity = CommonUtils.round(150 * dayPercent, 2);
            buttCapacity = CommonUtils.round(100 * dayPercent, 2);
            polishingCapacity = CommonUtils.round(100 * dayPercent, 2);
            finishCapacity = CommonUtils.round(90 * dayPercent, 2);
        }

        List<Map<String, Object>> list = Lists.newArrayList();
        list.add(getYieldMap(CUTOFF, yieldMapper.cutoffYield(param), cutoffCapacity));
        list.add(getYieldMap(BUTT, yieldMapper.buttYield(param), buttCapacity));
        list.add(getYieldMap(POLISHING, yieldMapper.polishingYield(param), polishingCapacity));
        list.add(getYieldMap(FINISH, yieldMapper.finishYield(param), finishCapacity));
        return list;
    }

    private Map<String, Object> getYieldMap(String process, Double yield, double capacity) {
        if (yield == null) {
            yield = 0D;
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("process", process);
        map.put("yield", yield);
        map.put("capacity", capacity);
        return map;
    }

    /**
     * 各个工序分机台产量统计
     *
     * @param DS      数据源
     * @param process 工序
     */
    @DS("#DS")
    @Override
    public EchartsSingleData yieldByEquipment(String DS, String process) {
        Map<String, Object> param = getTimeParam(LocalDateTime.now());
        switch (process) {
            case CUTOFF:
                return DataUtils.getEchartsDataSortByK(() -> yieldMapper.cutoffYieldByEquipment(param));
            case BUTT:
                return DataUtils.getEchartsDataSortByK(() -> yieldMapper.buttYieldByEquipment(param));
            case POLISHING:
                return DataUtils.getEchartsDataSortByK(() -> yieldMapper.polishingYieldByEquipment(param));
            case FINISH:
                return DataUtils.getEchartsDataSortByK(() -> yieldMapper.finishYieldBySpec(param));
            default:
                return null;
        }
    }

    /**
     * 各个工序在线统计
     *
     * @param DS 数据源
     */
    @DS("#DS")
    @Override
    public Object[] onlineByProcess(String DS) {
        Object[] data = new Object[4];
        if (showYesterday(LocalDateTime.now())) {
            data[0] = getOnlineMap(CUTOFF, onlineMapper.yesterdayOnlineTotal(0), 2);
            data[1] = getOnlineMap(BUTT, onlineMapper.yesterdayOnlineTotal(1), 7);
            data[2] = getOnlineMap(POLISHING, onlineMapper.yesterdayOnlineTotal(2), 6);
            data[3] = getOnlineMap(FINISH, onlineMapper.yesterdayOnlineTotal(3), 4);
            return data;
        }

        ParallelRunner runner = ParallelRunner.create(4);
        runner.execute(DS, () -> data[0] = getOnlineMap(CUTOFF, onlineMapper.cutoffOnlineStatisticsAllMass(), 2));
        runner.execute(DS, () -> data[1] = getOnlineMap(BUTT, onlineMapper.buttOnlineStatisticsAllMass(), 7));
        runner.execute(DS, () -> data[2] = getOnlineMap(POLISHING, onlineMapper.polishingOnlineStatisticsAllMass(), 6));
        runner.executeAndAwait(() -> data[3] = getOnlineMap(FINISH, onlineMapper.finishOnlineStatisticsAllMass(), 4));
        return data;
    }

    private Map<String, Object> getOnlineMap(String process, Record onlineRecord, double target) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("process", process);
        map.put("weight", CommonUtils.round(onlineRecord.getDouble("weight"), 2));
        map.put("in_process", CommonUtils.round(onlineRecord.getDouble("in_process"), 2));
        map.put("target", target);
        return map;
    }

    /**
     * 各个工序分规格在线统计
     *
     * @param DS      数据源
     * @param process 工序
     */
    @DS("#DS")
    @Override
    public EchartsData onlineBySpec(String DS, String process) {
        Callable<List<Record>> query;
        if (showYesterday(LocalDateTime.now())) {
            switch (process) {
                case CUTOFF:
                    query = () -> onlineMapper.yesterdayOnlineBySpec(0);
                    break;
                case BUTT:
                    query = () -> onlineMapper.yesterdayOnlineBySpec(1);
                    break;
                case POLISHING:
                    query = () -> onlineMapper.yesterdayOnlineBySpec(2);
                    break;
                case FINISH:
                    query = () -> onlineMapper.yesterdayOnlineBySpec(3);
                    break;
                default:
                    return null;
            }
        } else {
            switch (process) {
                case CUTOFF:
                    query = onlineMapper::cutoffOnlineStatisticsMass;
                    break;
                case BUTT:
                    query = onlineMapper::buttOnlineStatisticsMass;
                    break;
                case POLISHING:
                    query = onlineMapper::polishingOnlineStatisticsMass;
                    break;
                case FINISH:
                    query = onlineMapper::finishOnlineStatisticsMass;
                    break;
                default:
                    return null;
            }
        }
        Table<String, String, Double> table = query.call().stream().collect(HashBasedTable::create, (t, r) -> {
            t.put("实际在线", r.getString("k"), r.getDouble("v1"));
            t.put("加工中", r.getString("k"), r.getDouble("v2"));
        }, Table::putAll);
        return DataUtils.toEchartsDataSortByV(table, new String[]{"实际在线","加工中"});
    }

    /**
     * 各个工序设备数据
     *
     * @param DS 数据源
     */
    @DS("#DS")
    @Override
    public List<Map<String, Object>> equipmentByProcess(String DS) {
        LocalDateTime now = LocalDateTime.now();
        Map<String, Object> param = getTimeParam(now);
        Map<String, Double> maintenanceTime = DataUtils.getMap(() -> equipmentMapper.maintenanceTimeByProcess(param));

        Map<String, Record> waitingTime = equipmentMapper.waitingTimeByProcess(param).stream().collect(
                Collectors.toMap(r -> r.getString("process"), r -> r));

        List<Map<String, Object>> list = Lists.newArrayList();
        list.add(getEquipmentMap(CUTOFF, now, maintenanceTime, waitingTime));
        list.add(getEquipmentMap(BUTT, now, maintenanceTime, waitingTime));
        list.add(getEquipmentMap(POLISHING, now, maintenanceTime, waitingTime));
        list.add(getEquipmentMap(XQD, now, maintenanceTime, waitingTime));
        return list;
    }

    private Map<String, Object> getEquipmentMap(String process, LocalDateTime now, Map<String, Double> maintenanceTime,
                                                Map<String, Record> waitingTime) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("process", process);

        String prefix = getProcessEquipmentPrefix(process);
        map.put("maintenance", CommonUtils.round(maintenanceTime.get(getProcessEquipmentPrefix(process)), 2));
        map.put("maintenanceTotal", getTotalTime(prefix, now));
        Record processWaitingTime = waitingTime.get(getProcessCode(process));
        if (processWaitingTime == null) {
            map.put("waiting", 0);
            map.put("waitingTotal", 0);
        } else {
            map.put("waiting", CommonUtils.round(processWaitingTime.getDouble("waiting"), 2));
            map.put("waitingTotal", CommonUtils.round(processWaitingTime.getDouble("total"), 2));
        }
        return map;
    }

    private double getTotalTime(String prefix, LocalDateTime now) {
        Map<String, Object> param = getTimeParam(now);
        param.put("process", prefix);

        Double count = equipmentMapper.equipmentCount(param);
        if (showYesterday(now)) {
            return 24 * 60;
        } else if (now.getHour() < 8) {
            return CommonUtils.round(
                    Duration.between(TimeUtils.get8oClock(now.minusDays(1)), now).toMinutes() * count / 60, 2);
        } else {
            return CommonUtils.round(Duration.between(TimeUtils.get8oClock(now), now).toMinutes() * count / 60, 2);
        }
    }

    /**
     * 设备维修用时
     *
     * @param DS      数据源
     * @param process 工序
     */
    @DS("#DS")
    @Override
    public EchartsData maintenanceTimeByEquipment(String DS, String process) {
        Map<String, Object> param = getTimeParam(LocalDateTime.now());
        param.put("process", getProcessEquipmentPrefix(process));
        Table<String, String, Double> table = DataUtils.getTable(
                () -> equipmentMapper.maintenanceTimeByEquipment(param));
        String[] reasons = table.rowKeySet().toArray(new String[0]);
        String[] equipments = new TreeSet<>(table.columnKeySet()).toArray(new String[0]);
        return DataUtils.toEchartsData(table, reasons, equipments);
    }

    /**
     * 待料时间
     *
     * @param DS      数据源
     * @param process 工序
     */
    @DS("#DS")
    @Override
    public List<Record> waitingTimeByEquipment(String DS, String process) {
        Map<String, Object> param = getTimeParam(LocalDateTime.now());
        param.put("process", getProcessCode(process));
        return equipmentMapper.waitingTimeByEquipment(param);
    }

    /**
     * 各个工序交料数据
     *
     * @param DS 数据源
     */
    @DS("#DS")
    @Override
    public EchartsSingleData payLengthByProcess(String DS) {
        Map<String, Object> param = getTimeParam(LocalDateTime.now());
        return DataUtils.getEchartsDataSortByV(() -> payLengthMapper.machinePayLengthByProcess(param));
    }

    /**
     * 交料数据分原因
     *
     * @param DS      数据源
     * @param process 工序
     */
    @DS("#DS")
    @Override
    public EchartsSingleData payLengthByReason(String DS, String process) {
        Map<String, Object> param = getTimeParam(LocalDateTime.now());
        param.put("process", process);
        return DataUtils.getEchartsDataSortByV(() -> payLengthMapper.machinePayLengthByReason(param));
    }

    /**
     * 前台传入参数获取工序方法
     *
     * @param process 工序
     */
    private String getProcessEquipmentPrefix(String process) {
        switch (process) {
            case CUTOFF:
                return "QDJ";
            case BUTT:
                return "QFJ";
            case POLISHING:
                return "YTJ";
            case XQD:
                return "XQD";
            default:
                throw new RuntimeException("工序找不到对应机台");
        }
    }

    /**
     * 前台传入参数获取工序方法
     *
     * @param process 工序
     */
    private String getProcessCode(String process) {
        switch (process) {
            case CUTOFF:
                return "cutoff";
            case BUTT:
                return "butt";
            case POLISHING:
                return "polishing";
            case XQD:
                return "xqd";
            default:
                throw new RuntimeException("工序找不到对应机台");
        }
    }

    private Map<String, Object> getTimeParam(LocalDateTime now) {
        LocalDateTime startTime, endTime;
        if (now.getHour() < 8) {
            startTime = TimeUtils.get8oClock(now.minusDays(1));
            endTime = now;
        } else if (now.getHour() < 10) {
            startTime = TimeUtils.get8oClock(now.minusDays(1));
            endTime = TimeUtils.get8oClock(now);
        } else {
            startTime = TimeUtils.get8oClock(now);
            endTime = now;
        }

        Map<String, Object> param = Maps.newHashMap();
        param.put("startTime", startTime);
        param.put("endTime", endTime);
        return param;
    }

    private boolean showYesterday(LocalDateTime now) {
        int hour = now.getHour();
        return hour >= 8 && hour < 10;
    }
}
