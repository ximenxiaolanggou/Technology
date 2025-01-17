package center.helloworld.juc.chapter_02_ThreadState;

/**
 *
 * 线程状态：BLOCKED
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_03_Blocked {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
           synchronized (Demo_03_Blocked.class) {

           }
        });

        t1.start();

        synchronized (Demo_03_Blocked.class) {
            Thread.sleep(500);
            System.out.println(t1.getState());
        }
    }
}
