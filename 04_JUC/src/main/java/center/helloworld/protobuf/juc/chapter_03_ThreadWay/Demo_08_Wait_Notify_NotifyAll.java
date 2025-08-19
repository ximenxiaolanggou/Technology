package center.helloworld.protobuf.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：wait、notify、notifyall
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_08_Wait_Notify_NotifyAll {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            sync();
        }, "T1");

        Thread t2 = new Thread(() -> {
            sync();
        }, "T2");
        t1.start();
        t2.start();

        Thread.sleep(6000);
        synchronized (Demo_08_Wait_Notify_NotifyAll.class) {
            // notify方法将等待池中的线程随机一个放入到锁池中，等线程释放锁后，抢占锁资源
            Demo_08_Wait_Notify_NotifyAll.class.notify();
        }
    }

    public static synchronized void sync() {
        try {
            for (int i = 0; i < 10; i++) {
                if(i == 5) {
                    Demo_08_Wait_Notify_NotifyAll.class.wait();
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
