package com.mes.kanban.machine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.mes.kanban.common.mapper.OnlineMapper;
import com.mes.kanban.machine.service.MachineKanbanService;
import com.mes.kanban.utils.DSUtils;
import com.mes.kanban.utils.DataUtils;
import com.mes.kanban.utils.SysUtils;
import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.ScrollData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MachineKanbanServiceImpl implements MachineKanbanService {
    private final OnlineMapper onlineMapper;

    /**
     * 毛棒在线统计
     *
     * @param DS       数据源
     * @param workshop 车间
     */
    @DS("#DS")
    @Override
    public EchartsData maoOnlineStatistics(String DS, String workshop) {
        Table<String, String, Double> table = onlineMapper.maoOnlineStatistics(
                Collections.singletonMap("workshop", SysUtils.getLJShop(workshop))).stream().collect(
                HashBasedTable::create, (t, r) -> {
                    String[] k2Arr = r.getString("k2").split("_");
                    t.put(r.getString("k1") + k2Arr[0], k2Arr[1], r.getDouble("v"));
                }, Table::putAll);

        String[] areas;
        if (DS.equals(DSUtils.DS_BS_DG)) {
            areas = SysUtils.getBsWorkshopAreas(workshop);
        } else {
            areas = SysUtils.getTCWorkshopAreas();
        }
        String[] legend = new String[areas.length * 2];
        for (int i = 0; i < areas.length; i++) {
            int index = i * 2;
            legend[index] = areas[i] + "未接收";
            legend[index + 1] = areas[i] + "未划线";
        }
        String[] types = {"<1小时", "1-2小时", "2-3小时", "3-4小时", "4-5小时", "≥5小时"};
        return DataUtils.toEchartsData(table, legend, types);
    }

    /**
     * 毛棒在线列表
     *
     * @param DS       数据源
     * @param workshop 车间
     */
    @DS("#DS")
    @Override
    public ScrollData maoOnlineList(String DS, String workshop, int hours) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime, endTime;
        if (hours == 3) {
            startTime = now.minusHours(5);
            endTime = now.minusHours(3);
        } else {
            startTime = now.minusDays(50);
            endTime = now.minusHours(5);
        }
        Map<String, Object> param = Maps.newHashMap();
        param.put("workshop", SysUtils.getLJShop(workshop));
        param.put("startTime", startTime);
        param.put("endTime", endTime);

        String[] columns;
        if (DS.equals(DSUtils.DS_BS_DG)) {
            columns = new String[]{"crystal_number_s", "spec_s", "duration", "creation_time"};
        } else {
            columns = new String[]{"crystal_number_s", "area", "spec_s", "creation_time"};
        }
        return DataUtils.getScrollData(() -> onlineMapper.maoOnlineList(param), columns);
    }
}