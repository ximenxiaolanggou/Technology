package center.helloworld.juc.chapter_02_ThreadState;

/**
 *
 * 线程状态：TERMINATED
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_06_Terminated {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {

        });

        t.start();
        Thread.sleep(1000);

        System.out.println(t.getState());
    }
}
