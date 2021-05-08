package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.xingying.shopping.master.service.UserAuthsService;
import com.xingying.shopping.master.entity.UserAuths;

import java.util.List;

/**
 * <p>
 * 星荧商城用户第三方授权信息表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-04-16
 */
@RestController
@RequestMapping("${xingYing.name}/userAuths")
        public class UserAuthsController {

    @Autowired
    private UserAuthsService userAuthsService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<UserAuths>> getListByPage(@RequestBody PageQueryEntity<UserAuths> params) {
        PageInfo<UserAuths> page = userAuthsService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城用户第三方授权信息表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getUserAuthsByParam")
    public QueryResultBean<UserAuths> getUserAuths(@RequestParam String key) {
        UserAuths userAuths = userAuthsService.getById(key);
        return new QueryResultBean<>(userAuths);
    }

    /**
     * 新增 星荧商城用户第三方授权信息表
     * @param userAuths UserAuths 对象
     * @return
     */
    @PostMapping("/addUserAuths")
    public OperationResultBean<UserAuths> addUserAuths(@RequestBody UserAuths userAuths) {
        boolean b = userAuthsService.saveOrUpdate(userAuths);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(userAuths);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delUserAuthss")
    public OperationResultBean<String> delUserAuthss(@RequestParam List<String> keys) {
        boolean b = userAuthsService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
