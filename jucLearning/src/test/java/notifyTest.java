import com.magee.jucbasic.NumberOper;
import org.junit.Test;

public class notifyTest {



    // 2个线程正常交替进行
    @Test
    public void test01(){
        NumberOper object = new NumberOper();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object.add();
            }
        }, "thread-add-1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object.sub();
            }
        }, "thread-sub-1").start();
    }


    //多个线程有问题，此时为虚假唤醒，需要将if改成while，唤醒后再判断一次是否符合条件
    @Test
    public void test02(){
        NumberOper object = new NumberOper();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object.add();
            }
        }, "thread-add-1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object.sub();
            }
        }, "thread-sub-1").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object.add();
            }
        }, "thread-add-2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                object.sub();
            }
        }, "thread-sub-2").start();
    }
}
