package com.magee.jucbasic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest02 {

    // 原子性int
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // 多个线程并发地增加计数器的值
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                // 等同于++操作
                counter.incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Counter value: " + counter.get());
    }
}
