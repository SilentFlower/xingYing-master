package com.xingying.shopping.master.config.security.handler;

import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import com.xingying.shopping.master.common.entitys.result.ReturnCode;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author SiletFlower
 * @date 2021/3/27 22:41:35
 * @description
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    private static final Logger log = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        JSONUtils.output(response.getWriter(),
                new QueryResultBean<>(ReturnCode.ERROR_AUTH,accessDeniedException.getMessage()));
    }
}
