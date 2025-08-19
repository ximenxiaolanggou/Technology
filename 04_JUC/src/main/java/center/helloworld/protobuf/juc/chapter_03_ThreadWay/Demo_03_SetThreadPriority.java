package center.helloworld.protobuf.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：设置线程优先级
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_03_SetThreadPriority {


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("T1线程"));

        Thread t2 = new Thread(() -> System.out.println("T2线程"));

        t1.setPriority(1);
        t2.setPriority(10);

        t1.start();
        t2.start();
    }
}
