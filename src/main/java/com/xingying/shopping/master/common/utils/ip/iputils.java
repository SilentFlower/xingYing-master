package com.xingying.shopping.master.common.utils.ip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xingying.shopping.master.common.utils.json.JSONUtils;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/5/23 20:37:18
 * @description
 */
public class iputils {

    /**
     * 通过HttpServletRequest返回IP地址`
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //代理IP
            ip = request.getRemoteAddr();
        }
        String[] ips = ip.split(",");
        return ips[0];
    }

    public static String getArea(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://ip-api.com/json/"+ip+"?lang=zh-CN";
        ipArea area = restTemplate.getForObject(url, ipArea.class);
        StringBuilder res = new StringBuilder();
        if (area.getCountry() != null && area.getCountry() != "") {
            res.append(area.getCountry() + ",");
        }
        if (area.getRegionName() != null && area.getRegionName() != "") {
            res.append(area.getRegionName() + ",");
        }
        if (area.getCity() != null && area.getCity() != "") {
            res.append(area.getCity());
        }
        if(res.toString() == null || res.toString() == ""){
            return "未知";
        }else{
            return res.toString();
        }
    }

    /**
     * 获取设备类型
     * @return
     */
    public static String getDeviceType(HttpServletRequest request) {
        // ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String agentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(agentString);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem(); // 操作系统信息
        eu.bitwalker.useragentutils.DeviceType deviceType = operatingSystem.getDeviceType(); // 设备类型
        switch (deviceType) {
            case COMPUTER:
                return "PC";
            case TABLET: {
                if (agentString.contains("Android")) return "Android Pad";
                if (agentString.contains("iOS")) return "iPad";
                return "Unknown";
            }
            case MOBILE: {
                if (agentString.contains("Android")) return "Android";
                if (agentString.contains("iOS")) return "IOS";
                return "Unknown";
            }
            default:
                return "Unknown";
        }
    }


}
