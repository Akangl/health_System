package com.study.dao;

import com.study.domain.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findByUserId(Integer id);
}
