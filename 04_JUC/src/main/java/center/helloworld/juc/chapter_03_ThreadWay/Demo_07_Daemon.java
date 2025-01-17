package center.helloworld.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：daemon
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_07_Daemon {


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("t1:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设置为守护线程，主线程结束，子线程也会结束
        t1.setDaemon(true);
        t1.start();
    }
}
