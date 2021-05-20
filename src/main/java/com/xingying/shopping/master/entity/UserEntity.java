package com.xingying.shopping.master.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author SiletFlower
 * @date 2021/3/27 23:24:52
 * @description
 */
public class UserEntity implements UserDetails {
    private String id;// 用户id
    private String username;// 用户名
    private String password;// 密码
    private Set<String> roleMenus;// 权限集(url)

    private Collection<? extends GrantedAuthority> authorities;
    public UserEntity() {

    }

    public UserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities,
                      Set<String>  roleMenus) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.roleMenus = roleMenus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Set<String> roleMenus) {
        this.roleMenus = roleMenus;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleMenus=" + roleMenus +
                ", authorities=" + authorities +
                '}';
    }
}
