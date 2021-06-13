package com.xingying.shopping.master.config.rabbitMq;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xingying.shopping.master.dao.CouponFlowMapper;
import com.xingying.shopping.master.dao.CouponUserMapper;
import com.xingying.shopping.master.dao.OrderDetailMapper;
import com.xingying.shopping.master.dao.OrderMapper;
import com.xingying.shopping.master.entity.CouponFlow;
import com.xingying.shopping.master.entity.CouponUser;
import com.xingying.shopping.master.entity.OrderDetail;
import com.xingying.shopping.master.entity.OrderMaster;
import com.xingying.shopping.master.entity.rabbit.OrderRabbit;
import com.xingying.shopping.master.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/4/26 11:02:50
 * @description
 */
@Component
public class mqConsumer {
    @Autowired
    private OrderService orderService;

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.DELAY_ORDER)
    public void processOrder(OrderRabbit orderRabbit) {
        System.out.println("执行");
        orderService.mqHandler(orderRabbit);
    }



}
