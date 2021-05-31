package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.study.dao.PermissionDao;
import com.study.dao.RoleDao;
import com.study.dao.UserDao;
import com.study.domain.Permission;
import com.study.domain.Role;
import com.study.domain.User;
import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author Wenkang.Zhou
 * @date 2021-05-25 22:57
 * @description UserService接口实现类
 */

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * @param username
     * @Author: Wenkang.Zhou
     * @Description: 根据用户名查询数据库获取用户信息和关联的角色信息，同时查询角色关联的权限信息
     * @Date: 2021/5/25 22:59
     * @return: com.study.domain.User
     **/
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        Integer id = user.getId();
        Set<Role> roles = roleDao.findByUserId(id);
        for (Role role : roles) {
            Integer roleId = role.getId();
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            role.setPermissions(permissions);
        }
        user.setRoles(roles);
        return user;
    }
}
