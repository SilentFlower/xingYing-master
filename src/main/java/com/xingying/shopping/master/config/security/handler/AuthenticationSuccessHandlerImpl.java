package com.xingying.shopping.master.config.security.handler;

import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import com.xingying.shopping.master.common.entitys.result.ReturnCode;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * @author SiletFlower
 * @date 2021/3/26 08:51:50
 * @description
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //判断原先的token是否需要清除
        String oldToken = jwtTokenUtil.getTokenFromFront(httpServletRequest);
        if (oldToken != null && oldToken != "") {
            String oldId = jwtTokenUtil.getuidByToken(oldToken);
            //清除旧的token
            jwtTokenUtil.redisDel(oldToken + "#" + oldId);
        }
        UserEntity userInfo = (UserEntity) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(String.valueOf(userInfo.getId()), userInfo);
        Cookie cookie = new Cookie(jwtTokenUtil.getTokenName(), token);
        cookie.setPath("/master");
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        JSONUtils.output(httpServletResponse.getWriter(),
                new QueryResultBean<>(ReturnCode.LOGIN_SUCCESS, token));
    }
}
