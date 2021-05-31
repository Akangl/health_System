package com.study.service;

import com.study.domain.User;

/**
 * @Author: Wenkang.Zhou
 * @Date: 2021/5/25 22:49
 **/
public interface UserService {

    /**
     * @param username
     * @Author: Wenkang.Zhou
     * @Description: 查询用户信息
     * @Date: 2021/5/25 22:50
     * @return: com.study.domain.User
     **/
    User findByUsername(String username);
}
