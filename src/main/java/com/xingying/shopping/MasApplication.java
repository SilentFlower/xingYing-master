package com.xingying.shopping;

import com.xingying.shopping.master.common.utils.key.SnowFakeIdGenerator;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
@EnableDubboConfig

@MapperScan("com.xingying.shopping.${xingYing.name}.dao")
@DubboComponentScan("com.xingying.shopping.${xingYing.name}")
public class MasApplication {
    //设置机器码
    private static final long XINGYING_MASTER_ID = 1;



    //注册容器，并设置datacenterId和workerId
    @Bean
    public SnowFakeIdGenerator idGenerator(){
        return new SnowFakeIdGenerator(XINGYING_MASTER_ID, XINGYING_MASTER_ID);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MasApplication.class, args);
    }

}
