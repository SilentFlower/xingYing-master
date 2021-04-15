package com.xingying.shopping.master.config.security.handler;

import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.ReturnCode;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author SiletFlower
 * @date 2021/3/27 22:39:53
 * @description
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String jwtToken = jwtTokenUtil.getTokenFromFront(request);
        String uid = jwtTokenUtil.getuidByToken(jwtToken);
        jwtTokenUtil.redisDel(jwtToken + "#" + uid);
        Cookie cookie = new Cookie(jwtTokenUtil.getTokenName(), "登出");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.setContentType("text/json;charset=utf-8");
        JSONUtils.output(response.getWriter(),
                new OperationResultBean<>(ReturnCode.NOT_LOGGED_IN,"注销成功"));
    }
}
