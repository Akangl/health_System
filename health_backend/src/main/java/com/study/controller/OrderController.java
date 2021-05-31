package com.study.controller;

import com.study.constant.MessageConstant;
import com.study.constant.RedisMessageConstant;
import com.study.domain.Order;
import com.study.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author Wenkang.Zhou
 * @date 2021-05-14 17:21
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 在线体检预约
     *
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map map) {
        String telephone = (String) map.get("telephone");

        //从redis中获取到验证码
        String validateCodeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);

        //用户输入的验证码
        String validateCode = (String) map.get("validateCode");

        //将用户输入的验证码和redis中得到的验证码进行比对
        //用户输入的验证码和redis库中的验证码不为空，两个验证码相同
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //如果比对成功，调用服务完成预约业务处理
            //设置预约类型：微信预约，电话预约
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            Result result = null;
            try {
                //result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if (result.isFlag()) {
                //预约成功，可以为用户发送短信

            }
            return result;
        } else {
            //如果比对失败，返回结果到页面
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }

    }
}
