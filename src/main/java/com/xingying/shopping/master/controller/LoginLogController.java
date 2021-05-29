package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.xingying.shopping.master.common.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.LoginLogService;
import com.xingying.shopping.master.entity.LoginLog;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-23
 */
@RestController
@RequestMapping("${xingYing.name}/loginLog")
        public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<LoginLog>> getListByPage(@RequestBody PageQueryEntity<LoginLog> params) {
        PageInfo<LoginLog> page = loginLogService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getLoginLogByParam")
    public QueryResultBean<LoginLog> getLoginLog(@RequestParam String key) {
        LoginLog loginLog = loginLogService.getById(key);
        return new QueryResultBean<>(loginLog);
    }

    /**
     * 新增
     * @param loginLog LoginLog 对象
     * @return
     */
    @PostMapping("/addLoginLog")
    public OperationResultBean<LoginLog> addLoginLog(@RequestBody LoginLog loginLog) {
        boolean b = loginLogService.saveOrUpdate(loginLog);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(loginLog);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delLoginLogs")
    public OperationResultBean<String> delLoginLogs(@RequestParam List<String> keys) {
        boolean b = loginLogService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * {
     *     pageSize
     *     pageNumber
     *     data:{
     *
     *     }
     * }
     * 获取用户的登陆记录（默认是按登陆时间倒序）
     *{
     *   "userId": "",//用户ID
     *   "userIp": "",//用户IP
     *   "userArea": "",//登录区域
     *   "userDevice": "",//登录设备
     *   "loginDate": "2021-05-23 20:09:12"//登陆时间
     * }
     * @param params
     * @return Result
     */
    @PostMapping("/getLoginLogById")
    public QueryResultBean<PageInfo<LoginLog>> getLoginLogById(@RequestBody PageQueryEntity<LoginLog> params) {
        PageHelper.startPage(params.getPageNumber(), params.getPageSize());
        //（默认是按登陆时间倒序）
        List<LoginLog> list = loginLogService.list(new QueryWrapper<LoginLog>()
                .eq("USER_ID", UserContext.getCurrentUser().getUserId())
                .orderByDesc("LOGIN_DATE"));
        return new QueryResultBean<>(new PageInfo<>(list));
    }
}
