package com.study.dao;

import com.study.domain.User;

/**
 * @author Wenkang.Zhou
 * @date 2021-05-25 23:01
 * @description 用户服务
 */
public interface UserDao {

    public User findByUsername(String username);
}
