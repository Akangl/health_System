package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.entity.Result;
import com.study.service.ValidateCodeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author 12551
 */
@Service(interfaceClass = ValidateCodeService.class)
@Transactional
public class ValidateCodeServiceImpl implements ValidateCodeService {

    public Result order(Map map) {
        return null;
    }
}
