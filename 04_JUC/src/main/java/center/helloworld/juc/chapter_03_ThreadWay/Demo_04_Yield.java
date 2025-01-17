package center.helloworld.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：yield
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_04_Yield {


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if(i == 50){
                    Thread.yield();
                }
                System.out.println("t1:" + i);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("t2:" + i);
            }
        });
        t2.start();
        t1.start();
    }
}
