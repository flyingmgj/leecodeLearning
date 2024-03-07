package com.magee.jucbasic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CountDownLatch end = new CountDownLatch(10);

        for (int i = 0; i < 10 ; i++) {
            final int finalI = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "第[" + finalI + "]个bug修复完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    end.countDown();
                }
            });
        }
        end.await();
        System.out.println("bug全部修复完成，项目上线");
        // 关闭线程池
        executorService.shutdown();
    }
}
