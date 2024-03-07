package com.magee.jucbasic;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyMap extends HashMap<String, String> {

    //加入读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

/*    @Override
    public String get(Object key) {
        System.out.println(Thread.currentThread().getName() + "获取key:" + key);
        return super.get(key);
    }*/

    @Override
    public String get(Object key) {
        try{
            rwLock.readLock().lock();

            System.out.println(Thread.currentThread().getName() + "获取key:" + key);
            return super.get(key);

        } finally {
            rwLock.readLock().unlock();
        }
    }

//    @Override
//    public String put(String key, String value) {
//        System.out.println(Thread.currentThread().getName() + "写入key:" + key);
//        String put = super.put(key, value);
//        System.out.println(Thread.currentThread().getName() + "写入key:" + key + "完成");
//        return put;
//    }

    @Override
    public String put(String key, String value) {
        try {
            rwLock.writeLock().lock();

            System.out.println(Thread.currentThread().getName() + "写入key:" + key);
            String put = super.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入key:" + key + "完成");
            return put;
        } finally {
            rwLock.writeLock().unlock();
        }
    }


}
