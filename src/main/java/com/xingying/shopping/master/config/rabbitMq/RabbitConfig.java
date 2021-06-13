package com.xingying.shopping.master.config.rabbitMq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author SiletFlower
 * @date 2021/4/26 09:38:41
 * @description
 */
@Configuration
public class RabbitConfig {

    private static Logger logger = Logger.getLogger("RabbitMQConfiguration");

    @Value("${spring.rabbitmq.host}")
    public String host;

    @Value("${spring.rabbitmq.port}")
    public int port;

    @Value("${spring.rabbitmq.username}")
    public String username;

    @Value("${spring.rabbitmq.password}")
    public String password;

    @Bean
    public org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);

        logger.info("Create ConnectionFactory bean ..");
        return connectionFactory;
    }

    /**
     * 交换机常量名
     */
    public static final String EXCHANGE_NAME = "xy_change";

    /**
     * 延迟表(用于订单)
     */
    public static final String DELAY_ORDER = "xy_order";

    /**
     * 延迟表(用于商品)
     */
    public static final String DELAY_GOODS = "xy_goods";





    //定义延时交换机
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        CustomExchange customExchange = new CustomExchange(RabbitConfig.EXCHANGE_NAME, "x-delayed-message", true, false, args);
        return customExchange;
    }

    //延迟表队列
    @Bean
    public Queue delayOrder() {
        return new Queue(RabbitConfig.DELAY_ORDER, true);
    }


    // 队列绑定交换机
    @Bean
    public Binding delayBind() {
        return BindingBuilder.bind(delayOrder()).to(delayExchange()).with(RabbitConfig.DELAY_ORDER).noargs();
    }

    //延迟表队列
    @Bean
    public Queue delayGoods() {
        return new Queue(RabbitConfig.DELAY_GOODS, true);
    }


    // 队列绑定交换机
    @Bean
    public Binding delayBind2() {
        return BindingBuilder.bind(delayGoods()).to(delayExchange()).with(RabbitConfig.DELAY_GOODS).noargs();
    }


    // 定义消息转换器
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = JSONUtils.getObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }


    //定义消息模板用于发布消息，并且设置其消息转换器
    @Bean
    RabbitTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

}
