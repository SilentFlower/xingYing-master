package com.xingying.shopping.master.service.Impl;

import com.xingying.shopping.master.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author SiletFlower
 * @date 2021/3/26 15:11:51
 * @description
 */
@Service
@Component
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "你好我是主系统";
    }
}
