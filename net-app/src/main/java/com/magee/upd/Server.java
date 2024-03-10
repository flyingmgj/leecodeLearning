package com.magee.upd;

import com.magee.ip.IPTest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("========服务端启动============");
        //不定义就默认端口
        DatagramSocket datagramSocket = new DatagramSocket();

        String data = "fuck you! This is is a Message";
        byte[] bytes = data.getBytes();
        //数据，大小，服务端的IP，服务端的端口
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8899);
        //发送
        datagramSocket.send(datagramPacket);

        //关闭socket资源
        datagramSocket.close();
    }
}
