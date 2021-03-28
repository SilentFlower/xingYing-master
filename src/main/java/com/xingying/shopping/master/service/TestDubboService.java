package com.xingying.shopping.master.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SiletFlower
 * @date 2021/3/26 15:31:46
 * @description
 */
public interface TestDubboService {
    String createSession(HttpServletRequest request);

    String getSession(HttpServletRequest request);
}
