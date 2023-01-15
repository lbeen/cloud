package com.mes.kanban.utils;

import com.mes.kanban.constants.SysConstants;

/**
 * 系统工具
 */
public class SysUtils {

    /**
     * 获取拉晶车间
     *
     * @param shop 车间
     */
    public static String getLJShop(String shop) {
        return shop.substring(0, 5) + "LJ";
    }

    /**
     * 获取保山单晶车间
     */
    public static String[] getBsSingleWorkshops() {
        return new String[]{"一车间", "二车间", "三车间南", "三车间北"};
    }

    /**
     * 获取保山机加车间
     */
    public static String[] getBsMachineWorkshops() {
        return new String[]{"一车间", "二车间", "三车间"};
    }

    /**
     * 获取保山车间区域
     */
    public static String[] getBsWorkshopAreas(String workshop) {
        switch (workshop) {
            case SysConstants.MACHINE_SHOP_1:
                return new String[]{"一车间"};
            case SysConstants.MACHINE_SHOP_2:
                return new String[]{"二车间"};
            case SysConstants.MACHINE_SHOP_3:
                return new String[]{"三车间南", "三车间北"};
            default:
                throw new RuntimeException("车间找不到对应区域");
        }
    }

    /**
     * 获取腾冲单晶车间
     */
    public static String[] getTcSingleWorkshops() {
        return new String[]{"一车间南", "一车间北"};
    }

    /**
     * 获取腾冲机加车间
     */
    public static String[] getTcMachineWorkshops() {
        return new String[]{"一车间南", "一车间北"};
    }

    /**
     * 获取腾冲车间区域
     */
    public static String[] getTCWorkshopAreas() {
        return new String[]{"一车间南", "一车间北"};
    }

    /**
     * 获取单晶车间名称
     */
    public static String getSingleWorkshopName(String machineWorkshop) {
        switch (machineWorkshop) {
            case SysConstants.MACHINE_SHOP_1:
                return "单晶一车间";
            case SysConstants.MACHINE_SHOP_2:
                return "单晶二车间";
            case SysConstants.MACHINE_SHOP_3:
                return "单晶三车间";
            default:
                return null;
        }
    }
}
