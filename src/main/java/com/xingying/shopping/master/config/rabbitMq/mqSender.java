package com.xingying.shopping.master.config.rabbitMq;

import com.xingying.shopping.master.entity.rabbit.OrderRabbit;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SiletFlower
 * @date 2021/4/26 10:52:26
 * @description
 * 封装发送信息的方法
 */
@Component
public class mqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 延迟发送消息到队列(用于质控里的数据元)
     * @param message
     * @param delayTimes
     */
    public void sendMessageForOrder(OrderRabbit message,Integer delayTimes) {
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME,RabbitConfig.DELAY_ORDER,message, message1 -> {
            message1.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            message1.getMessageProperties().setDelay(delayTimes);
            return message1;
        });
    }

}
