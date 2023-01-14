package com.mes.kanban.constants;

/**
 * 工厂枚举
 */
public enum Factory {
    BS("保山"), TC("腾冲");

    public final String factoryName;

    Factory(String factoryName) {
        this.factoryName = factoryName;
    }

    /**
     * 获取工厂
     *
     * @param factory 工厂代码
     */
    public static Factory getFactory(String factory) {
        if (BS.name().equalsIgnoreCase(factory)) {
            return BS;
        }
        if (TC.name().equalsIgnoreCase(factory)) {
            return TC;
        }
        throw new RuntimeException(factory + "找不到对应工厂");
    }
}
