package com.xingying.shopping.master.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author SiletFlower
 * @date 2021/5/18 02:50:09
 * @description
 */
public interface ShopUser {

    /**
     * 获取用户
     * @return
     */
    UserEntity getUser();

    /**
     * 获取用户ID
     * @return
     */
    String getUserId();

    /**
     * 获取用户姓名
     * @return
     */
    String getUserName();

    /**
     * 获取权限
     */
    Set<String> getPermissions();

    /**
     * 获取角色
     */
    Collection<? extends GrantedAuthority> getAuthority();
}
