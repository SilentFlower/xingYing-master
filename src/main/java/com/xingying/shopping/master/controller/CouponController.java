package com.xingying.shopping.master.controller;

import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.entity.ext.CouponExt;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.xingying.shopping.master.service.CouponService;
import com.xingying.shopping.master.entity.Coupon;

/**
 * <p>
 * 星荧商城的优惠券表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@RestController
@RequestMapping("${xingYing.name}/coupon")
        public class CouponController {

    @Autowired
    private CouponService couponService;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

    /**
    * 分页列表
     * {
     *     pageSize
     *     pageNumber
     *     data:{
     *         couponFlag // 启用标志 1表示启用
     *         expiredFlag // 过期标志 1表示过期
     *         startTime 查询的创建时间
     *         endTime  查询的创建时间
     *         goodsName 商品名
     *         couponName 优惠券名
     *         //后台获取
     *         shopId
     *
     *     }
     * }
    *
    * @param params   分页信息
    * @return Result
     * {
     *   "haveUseCount": 0,
     *   "goodsName": 0,
     *   "couponId": "",
     *   "couponType": "",
     *   "goodsId": "",
     *   "couponUseType": "",
     *   "couponValue": 0.00,
     *   "couponLimit": 0.00,
     *   "couponDateCreate": "2021-05-30 09:39:08",
     *   "couponDateEnd": "2021-05-30 09:39:08",
     *   "couponFlag": 0,
     *   "couponNum": 0,
     *   "couponName": ""
     * }
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<CouponExt>> getListByPage(@RequestBody PageQueryEntity<CouponExt> params) {
        CouponExt data = params.getData();
        //如果无店铺ID则获取
        if (data.getShopId() == null || data.getShopId() == "") {
            data.setShopId(UserContext.getCurrentUser().getUserId());
        }
        PageInfo<CouponExt> page = couponService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧商城的优惠券表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getCouponByParam")
    public QueryResultBean<Coupon> getCoupon(@RequestParam String key) {
        Coupon coupon = couponService.getById(key);
        return new QueryResultBean<>(coupon);
    }

    /**
     * {
     *
     *
     *   "couponType": "",
     *   "goodsId": "",
     *   "couponUseType": "",
     *   "couponValue": 0.00,
     *   "couponLimit": 0.00,
     *   "couponDateEnd": "2021-05-29 10:08:44",
     *   "couponNum": 0,
     *   //后台获取
     *   "shopId": ""
     *   "couponId": "",
     *   "couponFlag": 0,
     *   "couponDateCreate": "2021-05-29 10:08:44",
     * }
     * 新增 星荧商城的优惠券表
     * @param coupon Coupon 对象
     * @return
     */
    @PostMapping("/addCoupon")
    public OperationResultBean<Coupon> addCoupon(@RequestBody Coupon coupon) {
        coupon.setCouponFlag(1);
        coupon.setCouponDateCreate(LocalDateTime.now());
        coupon.setShopId(UserContext.getCurrentUser().getUserId());
        coupon.setCouponId(String.valueOf(snowFakeIdGenerator.nextId()));
        boolean b = couponService.saveOrUpdate(coupon);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(coupon);
    }

    /**
     * {
     *
     *   couponId// ID
     *   couponDateEnd// 过期时间
     *   couponNum// 优惠券张数
     *   couponLimit //限制金额
     *   couponValue //价值
     *
     *
     * }
     * 新增 星荧商城的优惠券表
     * @param coupon Coupon 对象
     * @return
     */
    @PostMapping("/editCoupon")
    public OperationResultBean<Coupon> editCoupon(@RequestBody Coupon coupon) {
        boolean b = couponService.updateById(coupon);
        Assert.isTrue(b,"修改失败");
        return new OperationResultBean<>(coupon);
    }

    /**
     * 作废
     * {
     *     couponId:[
     *
     *     ]
     * }
     *
     *
     *
     * @return Result
     */
    @PostMapping("/makeInvalid")
    public OperationResultBean<String> makeInvalid(@RequestBody List<String> couponIds) {
        List<Coupon> list = new ArrayList<>();
        for (String couponId : couponIds) {
            Coupon coupon = new Coupon();
            coupon.setCouponId(couponId);
            //作废
            coupon.setCouponFlag(0);
            list.add(coupon);
        }
        boolean b = couponService.updateBatchById(list);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delCoupons")
    public OperationResultBean<String> delCoupons(@RequestParam List<String> keys) {
        boolean b = couponService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
