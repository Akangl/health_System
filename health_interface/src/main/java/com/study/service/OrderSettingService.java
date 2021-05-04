package com.study.service;

import com.study.domain.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author 12551
 * 预约设置服务
 */
public interface OrderSettingService {
    /**
     * Excel文件上传，并解析文件内容保存到数据库
     *
     * @param orderSettingList
     */
    void upload(List<OrderSetting> orderSettingList);

    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     *
     * @param date
     * @return
     */
    List<Map> getOrderSettingByMonth(String date);

    /**
     * 根据指定日期修改可预约人数
     *
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);
}
