package com.magee.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("========客户端启动============");
        //1.创建接收管道，注册端口
        ServerSocket serverSocket = new ServerSocket(7777);//参数一：定义服务端口
        //2.等待管道连接
        Socket accept = serverSocket.accept();
        //3.从管道中获取一个字节输入流
        InputStream is = accept.getInputStream();
        //4.字节流升级生缓冲输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //5.按照行读取消息会更好
        String a;
        if ((a = br.readLine())!=null){
            System.out.println(accept.getRemoteSocketAddress()+"说了："+a);
        }
    }
}
