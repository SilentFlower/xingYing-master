package com.xingying.shopping.master.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
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
    JwtTokenUtil jwtTokenUtil;

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
        UserEntity userInfo = jwtTokenUtil.getUserbyId(user.getUserId());
        userInfo.setUsername(user.getAccount());
        userInfo.setPassword(user.getPasswords());
        return userInfo;
    }
}
