package com.magee;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JMHTestHello01 {

    /**
     * @Benchmark 类似于Junit，表示被度量代码标注
     */
    @Benchmark
    public void dealHelloWorld() throws InterruptedException {
        // 这里模拟该方法执行
        Thread.sleep(1000);
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMHTestHello01.class.getSimpleName())
                .warmupIterations(3)    //  预热的次数， 3次
                .warmupTime(TimeValue.seconds(2))   // 预热的时间，2s
                .forks(1)   // 测试的执行线程数量
                .build();
        new Runner(options).run();
    }
}
