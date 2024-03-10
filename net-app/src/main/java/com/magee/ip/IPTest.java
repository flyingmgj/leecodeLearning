package com.magee.ip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.zip.Adler32;

public class IPTest {

    //错误获取IP的方式，仅在windows下单网卡的情况是正确的--直接使用localHost.getHostAddress()
    //linux下不适用
    //windows下多网卡也不适用
    //同时开了vpn也有影响
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());
        System.out.println("----------------下面才是正确的获取方式----------------");
        localHost = getLocalHostExactAddress();
        System.out.println(localHost.getHostAddress());
    }


    //无论linux或者windows都可以用
    public static InetAddress getLocalHostExactAddress() {
        InetAddress candidateAddress = null;

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()) {
                NetworkInterface iface = networkInterfaces.nextElement();
                //获取该网卡接口下的ip，会有多个，需要一个个遍历找到需要的

                for(Enumeration<InetAddress> inetAddresses = iface.getInetAddresses(); inetAddresses.hasMoreElements();) {
                    InetAddress inetAddr = inetAddresses.nextElement();
                    // 排除loopback回环类型地址（不管是IPv4还是IPv6 只要是回环地址都会返回true）
                    // 回环地址---即本机上的应用可以通过网络协议的方式进行互相通信，此时由操作系统直接来完成发包不通过路由
                    // 回环地址说明是127.0.0.1 - 127.255.255.255
                    // 排除loopback回环类型地址（不管是IPv4还是IPv6 只要是回环地址都会返回true）
                    if(!inetAddr.isLoopbackAddress()) {
                        //  (站点本地地址) 相当于私有地址
                        if(inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了，就是我们要找的
                            // ~~~~~~~~~~~~~绝大部分情况下都会在此处返回你的ip地址值~~~~~~~~~~~~~
                            return inetAddr;
                        }
                        // 若不是site-local地址 那就记录下该地址当作候选
                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }

                    }
                }
            }
            // 如果出去loopback回环地之外无其它地址了，那就回退到原始方案吧
            return candidateAddress == null ? InetAddress.getLocalHost() : candidateAddress;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
