package com.xingying.shopping.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.CouponFlowService;
import com.xingying.shopping.master.entity.CouponFlow;

/**
 * <p>
 * 星荧优惠券使用记录表 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-29
 */
@RestController
@RequestMapping("${xingYing.name}/couponFlow")
        public class CouponFlowController {

    @Autowired
    private CouponFlowService couponFlowService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<CouponFlow>> getListByPage(@RequestBody PageQueryEntity<CouponFlow> params) {
        PageInfo<CouponFlow> page = couponFlowService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧优惠券使用记录表
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getCouponFlowByParam")
    public QueryResultBean<CouponFlow> getCouponFlow(@RequestParam String key) {
        CouponFlow couponFlow = couponFlowService.getById(key);
        return new QueryResultBean<>(couponFlow);
    }

    /**
     * 新增 星荧优惠券使用记录表
     * @param couponFlow CouponFlow 对象
     * @return
     */
    @PostMapping("/addCouponFlow")
    public OperationResultBean<CouponFlow> addCouponFlow(@RequestBody CouponFlow couponFlow) {
        boolean b = couponFlowService.saveOrUpdate(couponFlow);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(couponFlow);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delCouponFlows")
    public OperationResultBean<String> delCouponFlows(@RequestParam List<String> keys) {
        boolean b = couponFlowService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
