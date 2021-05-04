package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.dao.OrderSettingDao;
import com.study.domain.OrderSetting;
import com.study.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 12551
 * 预约设置服务
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional

public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * Excel文件上传，并解析文件内容保存到数据库
     *
     * @param orderSettingList
     */
    public void upload(List<OrderSetting> orderSettingList) {
        //判断集合是否为空
        if (orderSettingList != null && orderSettingList.size() > 0) {
            //将集合中的ordersetting对象遍历出来
            for (OrderSetting orderSetting : orderSettingList) {
                //检查此数据（日期）是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //已经存在，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //不存在，执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    /**
     * 根据指定日期修改可预约人数
     *
     * @param orderSetting
     */
    public void editNumberByDate(OrderSetting orderSetting) {
        //先查询出当前日期是否设置了预约人数
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //已经预约了人数，对人数进行修改
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            //未预约人数，进行添加操作
            orderSettingDao.add(orderSetting);
        }

    }

    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     *
     * @param date
     * @return
     */
    public List<Map> getOrderSettingByMonth(String date) {
        //从数据库获取到当月的预约设置数据
        Map dateMap = new HashMap();
        String dateBegin = date + "-1";
        String dateEnd = date + "-31";

        dateMap.put("dateBegin", dateBegin);
        dateMap.put("dateEnd", dateEnd);
        List<OrderSetting> orderSettingList = orderSettingDao.getOrderSettingByMonth(dateMap);

        //将数据库的数据封装成前端所需要的数据格式
        List<Map> data = new ArrayList<Map>();

        if (orderSettingList != null && orderSettingList.size() > 0) {
            for (OrderSetting orderSetting : orderSettingList) {
                Map orderSettingMap = new HashMap();
                //获得日期（几号）
                orderSettingMap.put("date", orderSetting.getOrderDate().getDate());
                //可预约人数
                orderSettingMap.put("number", orderSetting.getNumber());
                //已预约人数
                orderSettingMap.put("reservations", orderSetting.getReservations());

                data.add(orderSettingMap);
            }
        }
        return data;
    }
}
