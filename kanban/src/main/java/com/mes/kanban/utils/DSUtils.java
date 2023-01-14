package com.mes.kanban.utils;

import com.mes.kanban.constants.Factory;

/**
 * 系统工具
 */
public class DSUtils {
    /**
     * 保山DG库数据源
     */
    public static final String DS_BS_DG = "bs-dg";
    /**
     * 腾冲DG库数据源
     */
    public static final String DS_TC_DG = "tc-dg";

    /**
     * 获取DG库数据源
     *
     * @param factory 工厂
     */
    public static String getDGDataSource(String factory) {
        Factory factoryEnum = Factory.getFactory(factory);
        if (factoryEnum == Factory.BS) {
            return DS_BS_DG;
        }
        if (factoryEnum == Factory.TC) {
            return DS_TC_DG;
        }
        throw new RuntimeException("工厂匹配不到对应DG库数据源");
    }
}
