package com.magee.jucbasic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest2 {

    static Object syncObject = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(LockSupportTest2::doExecuteOn, "Thread-1");
        Thread thread2 = new Thread(LockSupportTest2::doExecuteOn, "Thread-2");

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();

        thread1.interrupt();
        LockSupport.unpark(thread2);

    }

    private static void doExecuteOn(){
        synchronized (syncObject) {
            System.out.println(Thread.currentThread().getName() + "执行中，准备挂起");
            LockSupport.park();
            if(Thread.interrupted()){
                System.out.println(Thread.currentThread().getName() + "中断了");
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }
    }

}
