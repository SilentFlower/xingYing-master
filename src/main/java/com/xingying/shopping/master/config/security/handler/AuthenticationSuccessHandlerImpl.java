package com.xingying.shopping.master.config.security.handler;

import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import com.xingying.shopping.master.common.entitys.result.ReturnCode;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author SiletFlower
 * @date 2021/3/26 08:51:50
 * @description
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        HttpSession session = httpServletRequest.getSession();
        JSONUtils.output(httpServletResponse.getWriter(),
                new QueryResultBean<>(ReturnCode.LOGIN_SUCCESS, session.getId()));
    }
}
