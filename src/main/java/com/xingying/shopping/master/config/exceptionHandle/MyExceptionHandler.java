package com.xingying.shopping.master.config.exceptionHandle;

import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.ResultBean;
import com.xingying.shopping.master.common.entitys.result.ReturnCode;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author SiletFlower
 * @date 2021/3/22 01:03:23
 * @description
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public void resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {

        ResultBean<String> feedback;
        String ajax = request.getHeader("X-Requested-With");
        if (StringUtils.isNotEmpty(ajax)) {
            if (ex instanceof IllegalArgumentException){
                feedback = new OperationResultBean<>(ReturnCode.ERROR_PARAMS_FORMAT, ex.getMessage());
            }else {
                feedback = new OperationResultBean<>(ReturnCode.ACTIVE_EXCEPTION,ex.getMessage());
            }

        } else {
            feedback = new OperationResultBean<>(ReturnCode.ACTIVE_FAILURE,ex.getMessage());
        }
        try {
            response.setContentType("text/json;charset=utf-8");
            JSONUtils.output(response.getWriter(),feedback);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()){
            logger.debug("业务异常=>",ex);
        }
    }

}
