package com.xingying.shopping.master.service.Impl;

import com.xingying.shopping.master.service.TestDubboService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author SiletFlower
 * @date 2021/3/26 15:32:23
 * @description
 */
@Service
public class TestDubboServiceImpl implements TestDubboService {

    @Override
    public String createSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "测试");
        System.out.println(session.getId());
        return session.getId();
    }

    @Override
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        System.out.println(name);
        return name;
    }
}
