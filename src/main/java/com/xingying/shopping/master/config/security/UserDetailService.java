package com.xingying.shopping.master.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.dao.PermissionsMapper;
import com.xingying.shopping.master.dao.RoleMapper;
import com.xingying.shopping.master.dao.UserToRoleMapper;
import com.xingying.shopping.master.dao.UserXyMapper;
import com.xingying.shopping.master.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SiletFlower
 * @date 2021/3/25 03:04:26
 * @description
 */
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserXyMapper userXyMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionsMapper permissionsMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据account查询用户
        UserXy user = userXyMapper.selectOne(new QueryWrapper<UserXy>()
                .eq("ACCOUNT", username));
        if (user == null) {
            logger.debug("用户未找到");
            throw new UsernameNotFoundException("user  " + username + " not found.");
        }
        //接着获取对应角色
        //可优化成一次查询(优化完成)
        List<Role> roles = roleMapper.getRoleByUserId(user.getUserId());
        List<Permissions> permissions = permissionsMapper.getPermissionsByRoles(roles);
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(roles);
        UserEntity userDetails = new UserEntity(user.getAccount(), user.getPasswords(), simpleGrantedAuthorities, permissions);
        userDetails.setId(user.getUserId());
        return userDetails;
    }

    private Collection<SimpleGrantedAuthority> createAuthorities(List<Role> roles){
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return simpleGrantedAuthorities;
    }
}
