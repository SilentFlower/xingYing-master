package com.xingying.shopping.master.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
import com.xingying.shopping.master.entity.response.OrderStatistics;
import com.xingying.shopping.master.entity.response.OrderStatisticsForPic;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.entitys.result.OperationResultBean;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import java.util.List;

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
}
