package center.helloworld.juc.chapter_02_ThreadState;

/**
 *
 * 线程状态：TIMED_WAITING
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_05_TimedWaiting {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        Thread.sleep(100);

        System.out.println(t1.getState());
    }
}
