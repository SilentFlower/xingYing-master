package com.xingying.shopping.master.config.security.handler;

import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import com.xingying.shopping.master.common.entitys.result.ReturnCode;
import com.xingying.shopping.master.common.utils.ip.iputils;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.dao.LoginLogMapper;
import com.xingying.shopping.master.entity.LoginLog;
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/3/26 08:51:50
 * @description
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private LoginLogMapper loginLogMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        UserEntity userInfo = (UserEntity) authentication.getPrincipal();
        //登陆日志记录
        try {
            String area = "";
            String ipAddr = "";
            String type = "";
            ipAddr = iputils.getIpAddr(httpServletRequest);
            area = iputils.getArea(ipAddr);
            type = iputils.getDeviceType(httpServletRequest);
            LoginLog loginLog = new LoginLog();
            loginLog.setLoginDate(LocalDateTime.now());
            loginLog.setUserId(userInfo.getId());
            loginLog.setUserIp(ipAddr);
            loginLog.setUserArea(area);
            loginLog.setUserDevice(type);
            //插入用户登录记录表
            loginLogMapper.insert(loginLog);
            System.out.println("完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断原先的token是否需要清除
        String oldToken = jwtTokenUtil.getTokenFromFront(httpServletRequest);
        if (oldToken != null && oldToken != "") {
            String oldId = jwtTokenUtil.getuidByToken(oldToken);
            //清除旧的token
            jwtTokenUtil.redisDel(oldToken + "#" + oldId);
        }
        String token = jwtTokenUtil.generateToken(String.valueOf(userInfo.getId()), userInfo);
        Cookie cookie = new Cookie(jwtTokenUtil.getTokenName(), token);
        cookie.setMaxAge(1000*60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        httpServletResponse.addCookie(cookie);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("user", userInfo);
        JSONUtils.output(httpServletResponse.getWriter(),
                new QueryResultBean<>(ReturnCode.LOGIN_SUCCESS, map));
    }
}
