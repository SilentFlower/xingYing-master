package com.xingying.shopping.master.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.page.PageQueryEntity;
import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import com.xingying.shopping.master.dao.MessageMapper;
import com.xingying.shopping.master.dao.OrderMapper;
import com.xingying.shopping.master.entity.*;
import com.xingying.shopping.master.dao.OrderDetailMapper;
import com.xingying.shopping.master.entity.response.DetailsOrder;
import com.xingying.shopping.master.entity.response.OrderAndCoupon;
import com.xingying.shopping.master.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private SnowFakeIdGenerator snowFakeIdGenerator;

    @Override
    public PageInfo<OrderDetail> getListByPage(PageQueryEntity<OrderDetail> pageQueryEntity) {
        PageHelper.startPage(pageQueryEntity.getPageNumber(), pageQueryEntity.getPageSize());
        List<OrderDetail> list = orderDetailMapper.getListByPage(pageQueryEntity.getData());
        return new PageInfo<>(list);
    }

    /**
     * 获取订单详细内容（包含优惠券使用）
     * @param orderId
     * @return
     */
    @Override
    public DetailsOrder getAllDetails(String orderId) {
        DetailsOrder detailsOrder = new DetailsOrder();
        //查找店铺优惠券
        List<Coupon> coupon = orderDetailMapper.getShopCoupons(orderId,1);
        //查找订单详细表及相关优惠券
        List<OrderAndCoupon> orderAndCoupon = orderDetailMapper.getOrderAndCoupon(orderId);
        detailsOrder.setCoupon(coupon);
        detailsOrder.setOrderAndCoupons(orderAndCoupon);
        return detailsOrder;
    }

    /**
     * 手动发货
     * @param orderDetail
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean manualDelivery(OrderAndCoupon orderDetail) {
        LocalDateTime now = LocalDateTime.now();
        String userId = UserContext.getCurrentUser().getUserId();
        //1.发送消息提醒
        Message message = new Message(
                String.valueOf(snowFakeIdGenerator.nextId()),
                "手动发货",
                "手动发货。您的商品:"+orderDetail.getGoodsName()+"-规格:"+orderDetail.getGoodsSpc()
                        +"发货信息为:"+orderDetail.getMessage(),
                now,
                orderDetail.getUserId(),
                0
        );
        message.setSendId(userId);
        messageMapper.insert(message);
        //2.更改子表状态
        OrderDetail update = new OrderDetail();
        update.setOrderDetailId(orderDetail.getOrderDetailId());
        update.setStatus(2);
        int i1 = orderDetailMapper.updateById(update);
        Assert.isTrue(i1 > 0,"修改子订单状态失败");
        //3.查找主表中的子表是否已经全部发货
        Integer count = orderDetailMapper.selectCount(new QueryWrapper<OrderDetail>()
                .eq("ORDER_ID", orderDetail.getOrderId())
                .eq("STATUS", 1));
        //如果查找出为0则表示已经全部发货，则修改主表的状态
        if (count == 0) {
            OrderMaster orderMaster = new OrderMaster();
            orderMaster.setOrderId(orderDetail.getOrderId());
            //6为已发货
            orderMaster.setStatus(6);
            int i = orderMapper.updateById(orderMaster);
            Assert.isTrue(i > 0,"修改主订单状态失败");
        }
        //否则无事发生
        return true;
    }
}
