package com.xingying.shopping.master.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/3/27 23:24:52
 * @description
 */
public class UserEntity implements UserDetails {
    private Long id;// 用户id
    private String username;// 用户名
    private String password;// 密码
    private List<Role> userRoles;// 用户权限集合(可去
    private List<Permissions> roleMenus;// 角色菜单集合

    private Collection<? extends GrantedAuthority> authorities;
    public UserEntity() {

    }

    public UserEntity(String username, String password, Collection<? extends GrantedAuthority> authorities,
                      List<Permissions> roleMenus) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.roleMenus = roleMenus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Permissions> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(List<Permissions> roleMenus) {
        this.roleMenus = roleMenus;
    }
}
