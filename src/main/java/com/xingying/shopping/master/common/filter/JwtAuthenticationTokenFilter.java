package com.xingying.shopping.master.common.filter;

import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.entity.UserEntity;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author SiletFlower
 * @date 2021/4/9 11:08:30
 * @description
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = jwtTokenUtil.getTokenFromFront(request);
        if (jwtToken != null) {
            String uid = jwtTokenUtil.getuidByToken(jwtToken);
            if (uid != null) {
                UserEntity userInfo = jwtTokenUtil.getUserByToken(jwtToken,uid);
                if (userInfo != null) {
                    //小于12小时自动续期
                    jwtTokenUtil.tokenExtend(jwtToken, uid);
                    Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
