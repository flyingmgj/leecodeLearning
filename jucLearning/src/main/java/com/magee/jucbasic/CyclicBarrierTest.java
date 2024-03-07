package com.magee.jucbasic;


import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.*;

public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 等待3人报名成功，打印组团成功
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("每3个人报名，则组团成功"));

        // 模拟10个人报名
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                // 这里模拟每个人报名时长1s
                ThreadUtil.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "第[" + finalI + "]个报名成功");
                try {
                    //超时等待自动退出，否则会一直等待
                    barrier.await(5, TimeUnit.SECONDS);
                } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }
        // 关闭线程池
        executorService.shutdown();
    }

}
