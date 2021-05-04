package com.study.dao;

import com.study.domain.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 12551
 * 预约设置服务
 */
public interface OrderSettingDao {

    /**
     * 新增
     *
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 根据日期更新预约人数
     *
     * @param orderSetting
     */
    void editNumberByOrderDate(OrderSetting orderSetting);

    /**
     * 根据预约日期查询
     *
     * @param orderDate
     * @return
     */
    long findCountByOrderDate(Date orderDate);

    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     *
     * @param dateMap
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(Map dateMap);
}
