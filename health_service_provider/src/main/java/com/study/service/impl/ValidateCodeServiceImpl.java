package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.service.ValidateCodeService;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = ValidateCodeService.class)
@Transactional
public class ValidateCodeServiceImpl implements ValidateCodeService {

}
