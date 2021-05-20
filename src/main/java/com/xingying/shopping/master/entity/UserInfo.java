package com.xingying.shopping.master.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author SiletFlower
 * @date 2021/5/18 02:50:56
 * @description
 */
public class UserInfo implements ShopUser{

    private UserEntity user;

    public UserInfo setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @Override
    public UserEntity getUser() {
        return user;
    }

    @Override
    public String getUserId() {
        return user.getId();
    }

    @Override
    public String getUserName() {
        return user.getUsername();
    }

    @Override
    public Set<String> getPermissions() {
        return user.getRoleMenus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthority() {
        return user.getAuthorities();
    }
}
