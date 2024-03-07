package com.magee.jucbasic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    static Object syncObject = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (syncObject) {
                System.out.println(Thread.currentThread().getName() + "执行中，准备挂起");
                LockSupport.park();
            }
        }, "Thread-1");


        Thread thread2 = new Thread(() -> {
            synchronized (syncObject) {
                System.out.println(Thread.currentThread().getName() + "执行中，准备挂起");
                LockSupport.park();
            }
        }, "Thread-2");


        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();


        // 继续执行
        System.out.println("thread1线程准备执行了unpark");
        LockSupport.unpark(thread1);
        System.out.println("thread1线程执行了unpark");
        // 继续执行
        System.out.println("thread2线程准备执行了unpark");
        LockSupport.unpark(thread2);
        System.out.println("thread2线程执行了unpark");

        thread1.join();
        thread2.join();
        System.out.println("程序运行结束");

    }


}
