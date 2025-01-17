package center.helloworld.juc.chapter_02_ThreadState;

/**
 *
 * 线程状态：WAITING
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_04_Waiting {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
           synchronized (Demo_04_Waiting.class) {
               try {
                   Demo_04_Waiting.class.wait();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        t1.start();
        Thread.sleep(1000);

        System.out.println(t1.getState());
    }
}
