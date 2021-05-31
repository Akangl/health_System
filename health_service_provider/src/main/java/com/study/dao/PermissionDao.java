package com.study.dao;

import com.study.domain.Permission;

import java.util.Set;

/**
 * @Author: Wenkang.Zhou
 * @Date: 2021/5/25 23:12
 **/
public interface PermissionDao {
    /**
     * @param roleId
     * @Author: Wenkang.Zhou
     * @Description:
     * @Date: 2021/5/25 23:12
     * @return: java.util.Set<com.study.domain.Permission>
     **/
    Set<Permission> findByRoleId(Integer roleId);
}
