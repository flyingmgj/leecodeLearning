import com.alibaba.ttl.threadpool.TtlExecutors;
import com.magee.ThreadLocal01;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class java {

    //定义一个线程池，线程数量为1
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    //需要用TtlExecutors来创建线程池
    static ExecutorService ttlexecutorService = TtlExecutors.getTtlExecutorService(Executors.newSingleThreadExecutor());


    @Test
    public void test01() {
        ThreadLocal<String> threadLocal = ThreadLocal01.threadLocal;
        threadLocal.set("我是主线程的threadlocal变量");
        System.out.println("-----> 主线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());

        new Thread(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        }, "son-thread").start();
    }

    @Test
    public void test02() {
        InheritableThreadLocal<String> threadLocal = ThreadLocal01.inheritableThreadLocal;
        threadLocal.set("我是主线程的threadlocal变量");
        System.out.println("-----> 主线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());

        new Thread(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        }, "son-thread").start();
    }


    //使用线程池存在的问题，获取不到值
    @Test
    public void test03() throws Exception {
        InheritableThreadLocal<String> threadLocal = ThreadLocal01.inheritableThreadLocal;

        // 线程池执行子线程
        executorService.submit(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        });

        // 主线程睡眠3s，模拟运行
        Thread.sleep(3000);
        // 将变量修改为11111
        threadLocal.set("我是主线程的threadlocal变量，变量值为：11111");

        // 这里线程池重新执行线程任务
        executorService.submit(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        });

        // 线程池关闭
        executorService.shutdown();
    }

    //使用ttl
    @Test
    public void test04() throws Exception {
        ExecutorService executorService = ttlexecutorService;
        ThreadLocal<String> threadLocal = ThreadLocal01.transmittableThreadLocal;

        //threadLocal.set("我是主线程的threadlocal变量，变量值为：000000");

        // 线程池执行子线程
        executorService.submit(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        });

        // 主线程睡眠3s，模拟运行
        Thread.sleep(3000);
        // 将变量修改为11111，在InheritableThreadLocal中修改是无效的
        threadLocal.set("我是主线程的threadlocal变量，变量值为：11111");

        // 这里线程池重新执行线程任务
        executorService.submit(() -> {
            System.out.println("-----> 子线程" + Thread.currentThread() + " <----- 获取threadlocal变量：" + threadLocal.get());
        });

        // 线程池关闭
        executorService.shutdown();
    }

    @Test
    public void test05(){
        String str = "1,2,3,4,6";
        String[] split = str.split(",");
        for(int i=0; i<split.length; i++) {
            System.out.println(split[i]);
        }
    }
}
