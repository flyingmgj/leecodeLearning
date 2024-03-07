package com.magee.jucbasic;

public class ReadWriteLock {


    /**
     * 写入的时候，不会被读线程插队
     * @param args
     */
    public static void main(String[] args) {
        MyMap map = new MyMap();

        // 写操作
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> map.put(String.valueOf(finalI), String.valueOf(finalI))).start();
        }

        // 读操作
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> map.get(String.valueOf(finalI))).start();
        }
    }
}
