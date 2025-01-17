package center.helloworld.juc.chapter_02_ThreadState;

/**
 *
 * 线程状态：RUNNABLE
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_02_Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true);
        });

        t1.start();

        Thread.sleep(1000);

        System.out.println(t1.getState());
    }
}
