package com.study.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.constant.MessageConstant;
import com.study.constant.POIUtils;
import com.study.domain.OrderSetting;
import com.study.entity.Result;
import com.study.service.OrderSettingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 *
 * @author 12551
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    /**
     * Excel文件上传，并解析文件内容保存到数据库
     *
     * @param excelFile
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(@RequestParam MultipartFile excelFile) {
        try {
            //使用POI工具类解析表格数据
            List<String[]> readExcel = POIUtils.readExcel(excelFile);

            //判断Excel表格是否为空
            if (readExcel != null && readExcel.size() > 0) {
                //将数据封装为OrderSetting
                List<OrderSetting> orderSettingList = new ArrayList<OrderSetting>();
                //遍历Excel表格数据
                for (String[] strings : readExcel) {
                    /**
                     * string：excel表格中每一行数据
                     * new Date(strings[0])：第一列数据 => 日期
                     * Integer.parseInt(strings[1])：第二行数据 => 数量
                     **/
                    OrderSetting orderSetting = new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                    //将每一行数据作为一个OrderSetting对象，添加到List集合中去
                    orderSettingList.add(orderSetting);
                }
                orderSettingService.upload(orderSettingList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     * {date: 1, number: 120, reservations: 1}
     *
     * @return
     */
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String data) {
        //date参数格式为：2021-04
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(data);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    /**
     * 根据指定日期修改可预约人数
     *
     * @param orderSetting value        预约后的预约人数    (200)
     *                     orderDate    休要修改人数的日期 (2021-04-30)
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);

    }
}
