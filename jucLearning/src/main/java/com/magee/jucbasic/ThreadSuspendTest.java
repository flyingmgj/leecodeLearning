package com.magee.jucbasic;

import java.util.concurrent.TimeUnit;

public class ThreadSuspendTest {

    static Object syncObject = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (syncObject) {
                System.out.println(Thread.currentThread().getName() + "执行中，准备挂起");

                // 线程挂起
                Thread.currentThread().suspend();
            }
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            synchronized (syncObject) {
                System.out.println(Thread.currentThread().getName() + "执行中，准备挂起");

                // 线程挂起
                Thread.currentThread().suspend();
            }
        }, "Thread-2");

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();


        // 继续执行
        System.out.println("thread1线程准备执行了resume");
        thread1.resume();
        System.out.println("thread1线程执行了resume");
        // 继续执行
        System.out.println("thread2线程准备执行了resume");
        thread2.resume();
        System.out.println("thread2线程执行了resume");

        thread1.join();
        thread2.join();
        System.out.println("程序运行结束");
    }
}
