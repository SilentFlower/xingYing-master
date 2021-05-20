package com.xingying.shopping.master.dao;

import com.xingying.shopping.master.entity.OrderMaster;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
import com.xingying.shopping.master.entity.response.OrderStatistics;
import com.xingying.shopping.master.entity.response.OrderStatisticsForPic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@Repository
public interface OrderMapper extends BaseMapper<OrderMaster> {

    List<OrderMaster> getListByPage(OrderMaster orderMaster);

    /**
     * 分页查询 订单（时间倒序）
     * @param data
     * @return
     */
    List<OrderMasterExt> getOrdersByIdAndTime(OrderMasterExt data);

    /**
     * 查询今日支出、收入、今日订单、总成交订单
     * @param userId
     * @return
     */
    OrderStatistics getOrdersByIdForSum(String userId);

    /**
     * 查询某天某用户的统计数据
     * @param orderTime
     * @param userId
     * @return
     */
    OrderStatisticsForPic getStatisticsForPic(@Param("orderTime") LocalDateTime orderTime, @Param("userId") String userId);
}
