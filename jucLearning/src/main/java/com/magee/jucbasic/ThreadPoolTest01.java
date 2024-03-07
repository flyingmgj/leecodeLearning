package com.magee.jucbasic;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest01 {

    static {
        ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("common-thread-pool-").build();
        new ThreadPoolExecutor(7,20, 1*60, TimeUnit.SECONDS, new ArrayBlockingQueue(50),
                threadFactory,new ThreadPoolExecutor.AbortPolicy());

    }
}
