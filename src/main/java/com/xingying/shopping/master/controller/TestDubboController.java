package com.xingying.shopping.master.controller;

import com.xingying.shopping.master.common.context.UserContext;
import com.xingying.shopping.master.common.entitys.result.QueryResultBean;
import com.xingying.shopping.master.common.utils.token.JwtTokenUtil;
import com.xingying.shopping.master.config.dubbo.DubboHost;
import com.xingying.shopping.master.entity.ShopUser;
import com.xingying.shopping.master.entity.UserEntity;
import com.xingying.shopping.master.entity.UserXy;
import com.xingying.shopping.master.service.HelloService;
import com.xingying.shopping.master.service.TestDubboService;
import com.xingying.shopping.master.service.UserXyService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/3/14 16:19:22
 * @description
 */
@RestController
@RequestMapping("${xingYing.name}")
public class TestDubboController {
    @Autowired
    private HelloService helloService;
    @Autowired
    private TestDubboService testDubboService;
    @Autowired
    private UserXyService userXyService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping("/")

    public String test(@RequestBody UserEntity userEntity,HttpServletRequest request) {
        String s = helloService.sayHello("测试");
        System.out.println(s);
        return s;
    }

    @RequestMapping("/session")
    public String test02(HttpServletRequest request) {
        String id = testDubboService.createSession(request);
        return id;
    }

    @RequestMapping("/getSession")
    public String test03(HttpServletRequest request) {
        String name = testDubboService.getSession(request);
        return name;
    }


    @RequestMapping("/getUser")
    public QueryResultBean<UserXy> test06(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserXy user = (UserXy) session.getAttribute("User");
        return new QueryResultBean<>(user);
    }

    /**
     * 测试登陆成功
     */
    @RequestMapping("/index")
    public QueryResultBean<Map<String,Object>> index(HttpServletRequest request) {
        UserEntity user = UserContext.getCurrentUser().getUser();
        String token = jwtTokenUtil.getTokenFromFront(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("user", user);
        return new QueryResultBean<>(map);
    }
    /**
     * 模拟修改密码后清除所有登陆设备
     *
     */
    @RequestMapping("/editPasswd")
    public String editPasswd(HttpServletRequest request){
        String token = jwtTokenUtil.getTokenFromFront(request);
        String id = UserContext.getCurrentUser().getUserId();
        jwtTokenUtil.makeTokenExpireByUid(id);
        return "success";
    }


}
