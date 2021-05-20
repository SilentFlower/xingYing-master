package com.xingying.shopping.master.config.security;

import com.xingying.shopping.master.entity.Permissions;
import com.xingying.shopping.master.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * @author SiletFlower
 * @date 2021/3/27 23:30:55
 * @description
 */
@Component("rbacPermission")
public class RbacPermission {

    private static final Logger logger = LoggerFactory.getLogger(RbacPermission.class);

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserEntity) {
            // 读取用户所拥有的权限菜单
            Set<String> menus = ((UserEntity) principal).getRoleMenus();
            System.out.println(menus.size());
            for (String menu : menus) {
                if (antPathMatcher.match(menu, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

}
