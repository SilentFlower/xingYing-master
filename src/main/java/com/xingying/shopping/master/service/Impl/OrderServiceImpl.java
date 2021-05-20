package com.xingying.shopping.master.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.entity.OrderMaster;
import com.xingying.shopping.master.dao.OrderMapper;
import com.xingying.shopping.master.entity.ext.OrderMasterExt;
import com.xingying.shopping.master.entity.response.OrderStatistics;
import com.xingying.shopping.master.entity.response.OrderStatisticsForPic;
import com.xingying.shopping.master.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoweihao
 * @since 2021-05-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderMaster> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<OrderMaster> getListByPage(PageQueryEntity<OrderMaster> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<OrderMaster> list = orderMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 分页查询 订单（时间倒序）
     * @param params
     * @return
     */
    @Override
    public PageInfo<OrderMasterExt> getOrdersByIdAndTime(PageQueryEntity<OrderMasterExt> params) {
        PageHelper.startPage(params.getPageNumber(), params.getPageSize());
        List<OrderMasterExt> list = orderMapper.getOrdersByIdAndTime(params.getData());
        return new PageInfo<>(list);
    }

    /**
     * 查询今日支出、收入、今日订单、总成交订单
     * @return
     */
    @Override
    public OrderStatistics getOrdersByIdForSum() {
        return orderMapper.getOrdersByIdForSum(UserContext.getCurrentUser().getUserId());
    }

    /**
     * 查询x日内的统计数据
     * @return
     */
    @Override
    public List<OrderStatisticsForPic> getStatisticsForPic() {
        List<OrderStatisticsForPic> res = new ArrayList<OrderStatisticsForPic>();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 5; i >= 0; i--) {
            //查询的时间
            LocalDateTime orderTime  = now.minusDays(i);
            OrderStatisticsForPic one = orderMapper.getStatisticsForPic(orderTime,UserContext.getCurrentUser().getUserId());
            one.setOrderTime(orderTime);
            res.add(one);
        }
        return res;
    }
}
