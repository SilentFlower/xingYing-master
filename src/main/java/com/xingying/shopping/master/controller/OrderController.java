package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import com.xingying.shopping.master.common.utils.json.JavaTypeReference;
import com.xingying.shopping.master.entity.Coupon;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
import com.xingying.shopping.master.entity.request.MakeOrderRes;
import com.xingying.shopping.master.entity.request.OrderGoodsRes;
import com.xingying.shopping.master.entity.request.PayOrder;
import com.xingying.shopping.master.entity.response.OrderStatistics;
import com.xingying.shopping.master.entity.response.OrderStatisticsForPic;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xingying.shopping.master.service.OrderService;
import com.xingying.shopping.master.entity.OrderMaster;

/**
 * <p>
 * 星荧虚拟商品交易系统的订单表（主表） 前端控制器
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@RestController
@RequestMapping("${xingYing.name}/order")
        public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
    * 分页列表
    *
    * @param params   分页信息
    * @return Result
    */
    @PostMapping("/getListByPage")
    public QueryResultBean<PageInfo<OrderMaster>> getListByPage(@RequestBody PageQueryEntity<OrderMaster> params) {
        PageInfo<OrderMaster> page = orderService.getListByPage(params);
        return new QueryResultBean<>(page);
    }

    /**
     * 获取星荧虚拟商品交易系统的订单表（主表）
     *
     * @param key
     * @return Result
     */
    @GetMapping("/getOrderByParam")
    public QueryResultBean<OrderMaster> getOrder(@RequestParam String key) {
        OrderMaster orderMaster = orderService.getById(key);
        return new QueryResultBean<>(orderMaster);
    }

    /**
     * 新增 星荧虚拟商品交易系统的订单表（主表）
     * @param orderMaster Order 对象
     * @return
     */
    @PostMapping("/addOrder")
    public OperationResultBean<OrderMaster> addOrder(@RequestBody OrderMaster orderMaster) {
        boolean b = orderService.saveOrUpdate(orderMaster);
        Assert.isTrue(b,"新增失败");
        return new OperationResultBean<>(orderMaster);
    }

    /**
     * 删除
     *
     * @param keys key字符串，根据,号分隔
     * @return Result
     */
    @PostMapping("/delOrders")
    public OperationResultBean<String> delOrders(@RequestParam List<String> keys) {
        boolean b = orderService.removeByIds(keys);
        Assert.isTrue(b, "删除失败");
        return new OperationResultBean<>("success");
    }

    /**
     * 订单支付
     * @param payOrder
     * @return
     */
    @PostMapping("/payOrder")
    public OperationResultBean<String> payOrder(@RequestBody PayOrder payOrder) {
        orderService.payOrder(payOrder);
        return new OperationResultBean<>("success");
    }

    /**
     * 订单确认
     * @param payOrder
     * @return
     */
    @PostMapping("/confirmOrder")
    public OperationResultBean<String> confirmOrder(@RequestBody PayOrder payOrder) {
        orderService.confirmOrder(payOrder);
        return new OperationResultBean<>("success");
    }

    /**
     * 订单申诉
     * @param payOrder
     * @return
     */
    @PostMapping("/appealOrder")
    public OperationResultBean<String> appealOrder(@RequestBody PayOrder payOrder) {
        orderService.appealOrder(payOrder);
        return new OperationResultBean<>("success");
    }

    /**
     * 申诉退回金额
     * @param payOrder
     * @return
     */
    @PostMapping("/backAmount")
    public OperationResultBean<String> backAmount(@RequestBody PayOrder payOrder) {
        orderService.backAmount(payOrder);
        return new OperationResultBean<>("success");
    }

    /**
     * 申诉完成
     * @param payOrder
     * @return
     */
    @PostMapping("/appealOrderDone")
    public OperationResultBean<String> appealOrderDone(@RequestBody PayOrder payOrder) {
        orderService.appealOrderDone(payOrder);
        return new OperationResultBean<>("success");
    }


    /**
     * {
     *     pageSize
     *     pageNumber
     *     data:{
     *         //可传值
     *          orderId //订单ID
     *          status //订单状态
     *          ShopName //商家店铺名
     *          goodSName //包含的商品名
     *          shopId //商家ID
     *          userId //用户ID
     *     }
     * }
     * 分页查询 订单（时间倒序）
     *
     * @param params
     * @return Result
     * {
     *   "ShopName": "",//商家名
     *   "orderId": "",//订单ID
     *   "payAmount": 0,//总金额
     *   "status": 0,//订单状态
     *   "userId": "",//用户ID
     *   "dataCreateTime": "2021-05-19 23:25:47",//创建时间
     *   "dataEditTime": "2021-05-19 23:25:47",//修改时间
     *   "dataComTime": "2021-05-19 23:25:47",//完成时间
     *   "dataCalTime": "2021-05-19 23:25:47",//取消时间
     *   "shopId": ""//订单对应的店铺
     * }
     *
     *
     */
    @PostMapping("/getOrdersByIdAndTime")
    public OperationResultBean<PageInfo<OrderMasterExt>> getOrdersByIdAndTime(@RequestBody PageQueryEntity<OrderMasterExt> params) {
        PageInfo<OrderMasterExt> pageInfo = orderService.getOrdersByIdAndTime(params);
        return new OperationResultBean<>(pageInfo);
    }

    /**
     * 查询今日支出、收入、今日订单、总成交订单
     * {
     *     //GET
     *     无参
     * }
     * @param
     * @return
     * {
     *   "todaySEarnings": 0.00,//今日收入
     *   "spendToday": 0.00,//今日支出
     *   "orderToday": 0,//今天订单
     *   "totalTradedOrders": 0//总订单
     * }
     */
    @GetMapping("/getOrdersByIdForSum")
    public QueryResultBean<OrderStatistics> getOrdersByIdForSum() {
        OrderStatistics info = orderService.getOrdersByIdForSum();
        return new QueryResultBean<>(info);
    }

    /**
     * 查询x日内的统计数据
     * {
     *     //GET
     *     无参
     * }
     * @param
     * @return
     * {
     *   "orderTime": "2021-05-20 09:47:22",//订单时间
     *   "submitOrders": 0,//总提交订单
     *   "successfulOrder": 0,//成功订单
     *   "outcome": 0.00,//支出
     *   "income": 0.00//收入
     * }
     */
    @GetMapping("/getStatisticsForPic")
    public QueryResultBean<List<OrderStatisticsForPic>> getStatisticsForPic() {
        List<OrderStatisticsForPic> list = orderService.getStatisticsForPic();
        return new QueryResultBean<>(list);
    }

    /**
     * {
     *     status
     * }
     * 查找未付款订单数
     */
    @GetMapping("/getNumberByStatus")
    public QueryResultBean<Integer> getNumberByStatus(Integer status) {
        int count = orderService.count(new QueryWrapper<OrderMaster>()
                .eq("USER_ID", UserContext.getCurrentUser().getUserId())
                .eq("STATUS", status));
        return new QueryResultBean<>(count);
    }

    /**
     * 生成订单(并放入到rabbitmq队列中，10分钟不支付自动取消)
     * （返回附属订单）
     */
    @PostMapping("/makeOrder")
    public OperationResultBean<List<String>> makeOrder(@RequestBody Map<String,String> makeOrderRes) {
        ObjectMapper objectMapper = JSONUtils.getObjectMapper();
        String couponMapStr = makeOrderRes.get("couponMap");
        String goodsMapStr = makeOrderRes.get("goodsMap");
        Map<String, List<Coupon>> couponMap = new HashMap<>();
        Map<String, List<OrderGoodsRes>> goodsMap = new HashMap<>();
        if (couponMapStr != null) {
            try {
                couponMap = objectMapper.readValue(makeOrderRes.get("couponMap"), new TypeReference<Map<String, List<Coupon>>>(){});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            couponMap = null;
        }
        if (goodsMapStr != null) {
            try {
                goodsMap = objectMapper.readValue(makeOrderRes.get("goodsMap"), new TypeReference<Map<String, List<OrderGoodsRes>>>() {});
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            goodsMap = null;
        }
        MakeOrderRes makeOrderRes1 = new MakeOrderRes();
        makeOrderRes1.setCouponMap(couponMap);
        makeOrderRes1.setGoodsMap(goodsMap);
        System.out.println(makeOrderRes1);
        orderService.makeOrder(makeOrderRes1);
        return new OperationResultBean<>(new ArrayList<>());
    }
}
