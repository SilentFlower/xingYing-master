package com.xingying.shopping.master.controller;

import com.xingying.shopping.master.common.context.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;

import java.time.LocalDateTime;
import java.util.List;

import com.xingying.shopping.master.service.CouponUserService;
import com.xingying.shopping.master.entity.CouponUser;

/**
 * <p>
 * 星荧商城用户优惠券表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@RestController
@RequestMapping("${xingYing.name}/couponUser")
        public class CouponUserController {

    @Autowired
    private CouponUserService couponUserService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<CouponUser>> getListByPage(@RequestBody PageQueryEntity<CouponUser> params) {
        PageInfo<CouponUser> page = couponUserService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城用户优惠券表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getCouponUserByParam")
    public QueryResultBean<CouponUser> getCouponUser(@RequestParam String key) {
        CouponUser couponUser = couponUserService.getById(key);
        return new QueryResultBean<>(couponUser);
    }

    /**
     * 新增 星荧商城用户优惠券表
     * @param couponUser CouponUser 对象
     * @return
     */
    @PostMapping("/addCouponUser")
    public OperationResultBean<CouponUser> addCouponUser(@RequestBody CouponUser couponUser) {
        couponUser.setGetDate(LocalDateTime.now());
        couponUser.setStatus(1);
        couponUser.setUserId(UserContext.getCurrentUser().getUserId());
        boolean b = couponUserService.save(couponUser);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(couponUser);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delCouponUsers")
    public OperationResultBean<String> delCouponUsers(@RequestParam List<String> keys) {
        boolean b = couponUserService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
