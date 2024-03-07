package com.magee.jucbasic;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    // 得到绿灯通行凭证
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "======> 通行");
                    ThreadUtil.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "=======！结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放凭证
                    semaphore.release();
                }
            }, "第" + i + "辆车").start();
        }
    }
}
