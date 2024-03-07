package com.magee.jucbasic;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

    public static void main(String[] args) {

        System.out.println("==============传统实现方式================");
        long start0 = System.currentTimeMillis();
        long sum0 = 0;
        for (int i = 0; i <= 1000000000L; i++) {
            sum0 += i;
        }
        long end0 = System.currentTimeMillis();

        System.out.println("Sum: " + sum0 + "， 计算耗时：" + (end0 - start0) + "ms");
        System.out.println("==============传统实现方式================");
        System.out.println();
        System.out.println();

        System.out.println("==============Fork Join 实现方式================");
        long start1 = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 1000000000L);
        long sum1 = forkJoinPool.invoke(task);
        long end1 = System.currentTimeMillis();

        System.out.println("Sum: " + sum1 + "， 计算耗时：" + (end1 - start1) + "ms");
        System.out.println("==============Fork Join 实现方式================");
        System.out.println();
        System.out.println();


        System.out.println("==============JDK8 Stream 实现方式================");
        long start2 = System.currentTimeMillis();
        long sum2 = LongStream.range(0, 1000000000L).parallel().reduce(0, Long::sum);
        long end2 = System.currentTimeMillis();
        System.out.println("Sum: " + sum2 + "， 计算耗时：" + (end2 - start2) + "ms");
        System.out.println("==============JDK8 Stream 实现方式================");

    }





}

class CountTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10000;
    private final long start;
    private final long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if(end - start < THRESHOLD) {
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long mid = (start + end) / 2;
            CountTask leftTask = new CountTask(start, mid);
            CountTask rightTask = new CountTask(mid+1, end);

            leftTask.fork();
            rightTask.fork();

            long leftSum = leftTask.join();
            long rightSum = rightTask.join();
            return leftSum + rightSum;
        }
    }
}