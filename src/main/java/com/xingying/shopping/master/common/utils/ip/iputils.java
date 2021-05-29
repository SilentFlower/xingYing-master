package com.xingying.shopping.master.common.utils.ip;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpClient;

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
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getArea(String ip) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://ip-api.com/json/"+ip+"?lang=zh-CN";
        ipArea area = restTemplate.getForObject(url, ipArea.class);
        System.out.println(area);
        return area.getCountry()+","+area.getRegionName()+","+area.getCity();
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


    public static void main(String[] args) {
        String res = getArea("115.191.200.34");
        System.out.println(res);
    }
}
