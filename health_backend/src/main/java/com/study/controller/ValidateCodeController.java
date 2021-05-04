package com.study.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.constant.MessageConstant;
import com.study.constant.SMSUtils;
import com.study.constant.ValidateCodeUtils;
import com.study.entity.Result;
import com.study.service.ValidateCodeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信验证码
 *
 * @author Wenkang.Zhou
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

    @Reference
    private ValidateCodeService validateCodeService;

    /**
     * 体检预约时发送手机验证码
     *
     * @param telephone
     * @return
     */
    @RequestMapping("/send4order")
    public Result send4order(String telephone) {
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //短信验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
