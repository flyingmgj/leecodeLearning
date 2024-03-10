package com.magee.tcp;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("==============服务端启动===============");
        Socket socket = new Socket("127.0.0.1",7777);//参数一：服务端地址 参数二：服务端端口
        //2.从scoket管道中获得一个字节输出流,负责发送数据
        OutputStream os = socket.getOutputStream();
        //3.字节流升级成打印流
        PrintStream ps = new PrintStream(os);
        //4.发送消息
        ps.println("大哥，我来了");
        ps.flush();//刷新
    }
}
