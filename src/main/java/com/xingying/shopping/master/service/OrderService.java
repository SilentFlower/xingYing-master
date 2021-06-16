package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
import com.xingying.shopping.master.entity.rabbit.OrderRabbit;
import com.xingying.shopping.master.entity.request.MakeOrderRes;
import com.xingying.shopping.master.entity.request.PayOrder;
import com.xingying.shopping.master.entity.response.AppealNum;
import com.xingying.shopping.master.entity.response.OrderStatistics;
import com.xingying.shopping.master.entity.response.OrderStatisticsForPic;

import java.util.List;

/**
 * <p>
 *  OrderService 服务类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
public interface OrderService extends IService<OrderMaster> {

    PageInfo<OrderMaster> getListByPage(PageQueryEntity<OrderMaster> params);

    /**
     * 分页查询 订单（时间倒序）
     * @param params
     * @return
     */
    PageInfo<OrderMasterExt> getOrdersByIdAndTime(PageQueryEntity<OrderMasterExt> params);

    /**
     * 查询今日支出、收入、今日订单、总成交订单
     * @return
     */
    OrderStatistics getOrdersByIdForSum();

    /**
     * 查询x日内的统计数据
     * @return
     */
    List<OrderStatisticsForPic> getStatisticsForPic();

    /**
     * 生成订单(并放入到rabbitmq队列中，10分钟不支付自动取消)
     * @param makeOrderRes
     * @return
     */
    List<String> makeOrder(MakeOrderRes makeOrderRes);

    /**
     * rabbitmq的处理方法
     * @param orderRabbit
     */
    void mqHandler(OrderRabbit orderRabbit);

    /**
     * 订单支付
     * @param payOrder
     */
    boolean payOrder(PayOrder payOrder);

    /**
     * 订单确认
     * @param payOrder
     */
    boolean confirmOrder(PayOrder payOrder);

    /**
     * 订单申述
     * @param payOrder
     */
    boolean appealOrder(PayOrder payOrder);

    /**
     * 金额退回
     * @param payOrder
     */
    boolean backAmount(PayOrder payOrder);

    /**
     * 申诉结束
     * @param payOrder
     */
    boolean appealOrderDone(PayOrder payOrder);

    /**
     * 申诉订单数量查询
     * @return
     */
    AppealNum getAppealNum();
}
