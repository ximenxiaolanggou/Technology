package center.helloworld.protobuf.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：停止线程 - 定义变量
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_11_StopThread_Var {


    static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (flag);
        });

        t.start();

        Thread.sleep(1000);
        // 不推荐使用
        flag = false;
    }

}
