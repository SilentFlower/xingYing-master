package com.xingying.shopping.master.service;

import com.github.pagehelper.PageInfo;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.OrderMaster;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
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
}
