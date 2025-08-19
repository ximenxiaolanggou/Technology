package center.helloworld.protobuf.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：停止线程 - stop
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_10_StopThread_Stop {


    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true);
        });

        t.start();

        Thread.sleep(1000);
        // 不推荐使用
        t.stop();
    }
}
