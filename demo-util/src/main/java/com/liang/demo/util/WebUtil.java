package com.liang.demo.util;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by mashunfeng on 16-12-9.
 */
public class WebUtil {

    /**
     * 获取客户端ip
     *
     * @param request
     * @return
     */
    public static String clientIp(HttpServletRequest request) {
        String ip = request.getHeader("J-Forwarded-For");

        if (BaseUtil.isNullOrEmpty(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            ip = ip.split(",")[0];
        }

        return ip;
    }

    public static String getIp(HttpServletRequest request) {
        //
        String ip = request.getHeader("X-Forwarded-For");
//			log.debug("X-Forwarded-For ip="+ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
//	           log.debug("Proxy-Client-IP="+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
//	           log.debug("WL-Proxy-Client-IP=
//	        		   "+ip);
        }
        if (ip == null || "".equals(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
//			  log.debug("getRemoteAddr IP="+ip);
        }
        //
        String[] ips = null;
        if (ip != null) {
            ips = ip.split(",");
            ip = ips[0];
        }
        return ip;
    }


    // 获取本机IP
    public static List<String> getHostAddress() {
        List<String> list = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals("127.0.0.1")) {
                        list.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    // 获取本机IP
    public static String getHostAddressOne() {
        List<String> list = getHostAddress();
        if (!BaseUtil.isNullOrEmpty(list)) {
            return list.get(0);
        }
        return "";
    }
}
