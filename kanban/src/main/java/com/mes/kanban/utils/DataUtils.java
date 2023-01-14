package com.mes.kanban.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.mes.kanban.utils.pojo.EchartsData;
import com.mes.kanban.utils.pojo.EchartsPieData;
import com.mes.kanban.utils.pojo.EchartsSingleData;
import com.mes.kanban.utils.pojo.ScrollData;
import com.mes.mvc.pojo.Record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataUtils {

    /**
     * 获取echarts数据
     *
     * @param query 查询方法
     */
    public static EchartsSingleData getEchartsDataSortByK(Callable<List<Record>> query) {
        Map<String, Double> map = getMap(query);
        return toEchartsData(map, Sets.newTreeSet(map.keySet()).toArray(new String[0]));
    }

    /**
     * 获取echarts数据
     *
     * @param query 查询方法
     */
    public static EchartsSingleData getEchartsDataSortByV(Callable<List<Record>> query) {
        Map<String, Double> map = getMap(query);
        return toEchartsData(map, CommonUtils.getSortValueKey(map));
    }

    /**
     * 获取echarts数据
     *
     * @param query 查询方法
     * @param xAxis X轴条目
     */
    public static EchartsSingleData getEchartsData(Callable<List<Record>> query, String[] xAxis) {
        return toEchartsData(getMap(query), xAxis);
    }

    /**
     * 获取数据map
     *
     * @param query 查询方法
     */
    public static Map<String, Double> getMap(Callable<List<Record>> query) {
        return query.call().stream().collect(Collectors.toMap(r -> r.getString("k"), r -> {
            Double v = r.getDouble("v");
            return v == null ? 0 : v;
        }));
    }

    /**
     * 转换成Echarts数据
     *
     * @param map   数据map
     * @param xAxis X轴条目
     */
    private static EchartsSingleData toEchartsData(Map<String, Double> map, String[] xAxis) {
        double[] yAxis = new double[xAxis.length];
        for (int i = 0; i < xAxis.length; i++) {
            Double value = map.get(xAxis[i]);
            if (value != null) {
                yAxis[i] = value;
            }
        }
        return new EchartsSingleData(xAxis, yAxis);
    }

    /**
     * 获取echarts数据
     *
     * @param query  查询方法
     * @param legend 数据条目
     */
    public static EchartsData getEchartsDataSortByV(Callable<List<Record>> query, String[] legend) {
        return toEchartsDataSortByV(getTable(query), legend);
    }

    /**
     * 获取echarts数据
     *
     * @param table  数据table
     * @param legend 数据条目
     */
    public static EchartsData toEchartsDataSortByV(Table<String, String, Double> table, String[] legend) {
        Map<String, Double> totalMap = Maps.newHashMap();
        Map<String, Map<String, Double>> rowMap = table.rowMap();
        for (Map<String, Double> map : rowMap.values()) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                CommonUtils.increaseValue(totalMap, entry.getKey(), entry.getValue());
            }
        }
        return toEchartsData(table, legend, CommonUtils.getSortValueKey(totalMap));
    }

    /**
     * 获取echarts数据
     *
     * @param query  查询方法
     * @param legend 数据条目
     */
    public static EchartsData getEchartsDataSortByK(Callable<List<Record>> query, String[] legend) {
        return toEchartsDataSortByK(getTable(query), legend);
    }

    /**
     * 获取echarts数据
     *
     * @param table  数据table
     * @param legend 数据条目
     */
    public static EchartsData toEchartsDataSortByK(Table<String, String, Double> table, String[] legend) {
        return toEchartsData(table, legend, Sets.newTreeSet(table.columnKeySet()).toArray(new String[0]));
    }

    /**
     * 获取echarts数据
     *
     * @param query  查询方法
     * @param legend 数据条目
     * @param xAxis  X轴条目
     */
    public static EchartsData getEchartsData(Callable<List<Record>> query, String[] legend, String[] xAxis) {
        return toEchartsData(getTable(query), legend, xAxis);
    }

    /**
     * 获取数据table
     *
     * @param query 查询方法
     */
    public static Table<String, String, Double> getTable(Callable<List<Record>> query) {
        return query.call().stream().collect(HashBasedTable::create,
                (t, r) -> t.put(r.getString("k1"), r.getString("k2"), r.getDouble("v")), Table::putAll);
    }

    /**
     * 获取滚动列表数据数据
     *
     * @param query   查询方法
     * @param columns 字段
     */
    public static ScrollData getScrollData(Callable<List<Record>> query, String[] columns) {
        return toScrollData(query.call(), columns);
    }

    /**
     * 转换成Echarts数据
     *
     * @param table  数据table
     * @param legend 数据条目
     * @param xAxis  X轴条目
     */
    public static EchartsData toEchartsData(Table<String, String, Double> table, String[] legend, String[] xAxis) {
        double[][] yAxis = new double[legend.length][xAxis.length];
        for (int i = 0; i < legend.length; i++) {
            Map<String, Double> row = table.row(legend[i]);
            for (int j = 0; j < xAxis.length; j++) {
                Double value = row.get(xAxis[j]);
                if (value != null) {
                    yAxis[i][j] = value;
                }
            }
        }
        return new EchartsData(legend, xAxis, yAxis);
    }

    /**
     * 获取echarts数据
     *
     * @param query  查询方法
     * @param legend 数据条目
     */
    public static EchartsPieData getEchartsPieData(Callable<List<Record>> query, String[] legend) {
        Map<String, Double> map = getMap(query);
        List<Map<String, Object>> series = Lists.newArrayList();
        for (String key : legend) {
            series.add(ImmutableMap.of("name", key, "value", map.getOrDefault(key, 0D)));
        }
        return new EchartsPieData(legend, series);
    }

    /**
     * 获取滚动列表数据数据
     *
     * @param list    数据list
     * @param columns 字段
     */
    public static ScrollData toScrollData(List<Record> list, String[] columns) {
        String[][] data = list.stream().map(r -> Stream.of(columns).map(r::getString).toArray(String[]::new)).toArray(
                String[][]::new);
        return new ScrollData(data);
    }
}
