package center.helloworld.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：join
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_06_Join {


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
        t1.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 1){
                try {
                    t1.join(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
