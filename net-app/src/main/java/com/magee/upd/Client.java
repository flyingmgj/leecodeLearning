package com.magee.upd;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Client {

    //简单示例，一对一的通信
    public static void main(String[] args) throws Exception {
        //二、接收端(测试时候先启动接收再发送端)
        //1.创建接收端对象，注册端口（人）
        System.out.println("=========接收端启动===============");
        DatagramSocket socket2 = new DatagramSocket(8899);

        //2.创建一个要接收的数据容器（等待接收数据）
        byte[]b =new byte[1024*64];
        //3.把容器数据打包
        DatagramPacket packet2 = new DatagramPacket(b,b.length);
        //4.等待接收数据
        socket2.receive(packet2);
        //5.读取多少倒出多少
        int len = packet2.getLength();
        String rs = new String(b,0,len);
        System.out.println("接收到了数据了"+rs);

        //6.关闭资源,避免资源浪费
        socket2.close();
    }
}
