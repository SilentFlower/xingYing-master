package com.xingying.shopping.master.config.rabbitMq;


import com.xingying.shopping.master.entity.OrderMaster;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;
/**
 * @author SiletFlower
 * @date 2021/4/26 11:02:50
 * @description
 */
@Component
public class mqConsumer {


    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.DELAY_ORDER)
    public void processOrder(OrderMaster orderMaster) {

    }



}
