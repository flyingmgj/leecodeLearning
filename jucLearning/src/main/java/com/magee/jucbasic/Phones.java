package com.magee.jucbasic;

import java.util.concurrent.locks.ReentrantLock;

public class Phones {

    private int total = 50;
    // 1、 声明锁的实例
    ReentrantLock lock = new ReentrantLock();


    public void sale() {
        try {
            lock.lock();// 2、加锁，代码规约检测会提示你加载try的第一行
            if (total > 0) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "卖出了一部手机，当前库存剩余：" + (--total));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 3、释放锁
            lock.unlock();
        }
    }



    public static void main(String[] args) throws Exception {
        Phones phones = new Phones();

        new Thread(() -> {
            for(int i = 0; i< 50; i++) {
                phones.sale();
            }
        }, "销售员小王").start();

        new Thread(() -> {
            for(int i = 0; i< 50; i++) {
                phones.sale();
            }
        }, "销售员小红").start();
    }
}
