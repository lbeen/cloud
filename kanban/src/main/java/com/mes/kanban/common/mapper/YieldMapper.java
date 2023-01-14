package com.mes.kanban.common.mapper;

import com.mes.mvc.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface YieldMapper {
    /**
     * 切断产量
     *
     * @param param 查询参数
     */
    Double cutoffYield(Map<String, Object> param);

    /**
     * 切断产量分机台
     *
     * @param param 查询参数
     */
    List<Record> cutoffYieldByEquipment(Map<String, Object> param);

    /**
     * 切断产量分班次规格
     *
     * @param param 查询参数
     */
    List<Record> cutoffYieldByShiftSpec(Map<String, Object> param);

    /**
     * 切断产量分班次机台
     *
     * @param param 查询参数
     */
    List<Record> cutoffYieldByShiftEquipment(Map<String, Object> param);

    /**
     * 切方产量
     *
     * @param param 查询参数
     */
    Double buttYield(Map<String, Object> param);

    /**
     * 切方产量分机台
     *
     * @param param 查询参数
     */
    List<Record> buttYieldByEquipment(Map<String, Object> param);

    /**
     * 切方产量分班次规格
     *
     * @param param 查询参数
     */
    List<Record> buttYieldByShiftSpec(Map<String, Object> param);

    /**
     * 抛光产量
     *
     * @param param 查询参数
     */
    Double polishingYield(Map<String, Object> param);

    /**
     * 抛光产量分机台
     *
     * @param param 查询参数
     */
    List<Record> polishingYieldByEquipment(Map<String, Object> param);

    /**
     * 抛光产量分班次规格
     *
     * @param param 查询参数
     */
    List<Record> polishingYieldByShiftSpec(Map<String, Object> param);

    /**
     * 成品产量
     *
     * @param param 查询参数
     */
    Double finishYield(Map<String, Object> param);

    /**
     * 成品产量分规格
     *
     * @param param 查询参数
     */
    List<Record> finishYieldBySpec(Map<String, Object> param);

    /**
     * 成品产量分班次规格
     *
     * @param param 查询参数
     */
    List<Record> finishYieldByShiftSpec(Map<String, Object> param);
}