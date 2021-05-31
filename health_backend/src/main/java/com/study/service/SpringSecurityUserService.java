package com.study.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.domain.Permission;
import com.study.domain.Role;
import com.study.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Wenkang.Zhou
 * @date 2021-05-25 22:22
 * @description 实现认证和授权
 */
@Component
public class SpringSecurityUserService implements UserDetailsService {

    //使用dubbo通过网络远程调用服务提供方获取数据库中的用户信息
    @Reference
    private UserService userService;

    /**
     * @param username
     * @Author: Wenkang.Zhou
     * @Description: 根据用户名查询数据库获取用户信息
     * @Date: 2021/5/25 22:38
     * @return: org.springframework.security.core.userdetails.UserDetails
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            //用户不存在
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<>();

        //为当前用户授权
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //遍历角色集合，为用户授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                //遍历权限集合，为用户授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
        return securityUser;
    }


}
