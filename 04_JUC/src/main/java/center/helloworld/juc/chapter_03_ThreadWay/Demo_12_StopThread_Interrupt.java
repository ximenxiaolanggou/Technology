package center.helloworld.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：停止线程 - Interrupt
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_12_StopThread_Interrupt {


    public static void main(String[] args) throws InterruptedException {

        // 线程默认情况下，interrupt标记位：false
        System.out.println(Thread.currentThread().isInterrupted());
        // 执行interrupt之后，再次查看打断信息
        Thread.currentThread().interrupt();
        // interrupt标记位：ture
        System.out.println(Thread.currentThread().isInterrupted());
        // 返回当前线程，并归位为false interrupt标记位：ture
        System.out.println(Thread.interrupted());
        // 已经归位了
        System.out.println(Thread.interrupted());

        Thread t1 = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {

                    }
                    System.out.println("子线程结束~~");
                }
        );
        t1.start();
        Thread.sleep(100);
        // 将interrupted属性置为true
        t1.interrupt();
    }

}
