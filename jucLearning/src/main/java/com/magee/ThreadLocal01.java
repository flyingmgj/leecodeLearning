package com.magee;


import com.alibaba.ttl.TransmittableThreadLocal;

public class ThreadLocal01 {

    //线程独有
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    //支持父子线程通信
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    //解决线程池通信初始化异常的问题
    public static ThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();


    public static void main(String[] args) {
        threadLocal.set("我是主线程的threadlocal变量");
        System.out.println("-----> 主线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());

        new Thread(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        }, "son-thread").start();
    }
}
