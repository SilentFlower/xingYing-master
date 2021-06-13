package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.xingying.shopping.master.entity.response.DetailsOrder;
import com.xingying.shopping.master.entity.response.OrderAndCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

import com.xingying.shopping.master.service.OrderDetailService;
import com.xingying.shopping.master.entity.OrderDetail;

/**
 * <p>
 * 星荧虚拟商品交易系统的订单表（详细） 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@RestController
@RequestMapping("${xingYing.name}/orderDetail")
        public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<OrderDetail>> getListByPage(@RequestBody PageQueryEntity<OrderDetail> params) {
        PageInfo<OrderDetail> page = orderDetailService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧虚拟商品交易系统的订单表（详细）
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getOrderDetailByParam")
    public QueryResultBean<OrderDetail> getOrderDetail(@RequestParam String key) {
        OrderDetail orderDetail = orderDetailService.getById(key);
        return new QueryResultBean<>(orderDetail);
    }

    /**
     * 新增 星荧虚拟商品交易系统的订单表（详细）
     * @param orderDetail OrderDetail 对象
     * @return
     */
    @PostMapping("/addOrderDetail")
    public OperationResultBean<OrderDetail> addOrderDetail(@RequestBody OrderDetail orderDetail) {
        boolean b = orderDetailService.saveOrUpdate(orderDetail);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(orderDetail);
    }

    /**
     * 手动发货
     * @param orderDetail OrderDetail 对象
     * @return
     */
    @PostMapping("/manualDelivery")
    public OperationResultBean<String> manualDelivery(@RequestBody OrderAndCoupon orderDetail) {
        boolean b = orderDetailService.manualDelivery(orderDetail);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 获取订单详细内容（包含优惠券使用）
     * @param orderId
     * @return
     */
    @GetMapping("/getAllDetails")
    public QueryResultBean<DetailsOrder> getAllDetails(String orderId) {
        DetailsOrder detailsOrder = orderDetailService.getAllDetails(orderId);
        return new QueryResultBean<>(detailsOrder);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delOrderDetails")
    public OperationResultBean<String> delOrderDetails(@RequestParam List<String> keys) {
        boolean b = orderDetailService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }
}
