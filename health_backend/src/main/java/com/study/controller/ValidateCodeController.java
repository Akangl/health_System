package com.study.controller;/*


/**
 * 短信验证码
 */

import com.study.constant.MessageConstant;
import com.study.constant.RedisMessageConstant;
import com.study.constant.ValidateCodeUtils;
import com.study.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author 12551
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 体检预约时发送手机验证码
     *
     * @param telephone
     * @return
     */


    @RequestMapping("/send4order")
    public Result send4order(String telephone) {
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("随机生成的验证码为 === { " + code + "}");

        //为开通短信验证服务，由本地获取到短信验证码。控制台输出打印。
        /*try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //短信验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }*/


        /**
         * 将验证码保存五分钟
         *  String key：手机号码作为key(但是当用户同时进行预约、登录操作就会发送两个相同的，所以需要工具类在手机号码后加上后缀，来区分)
         *  int seconds：保留的时间(s)
         *  String value：随机生成的验证码
         */
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 300, code.toString());

        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

}

