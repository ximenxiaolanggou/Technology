package center.helloworld.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：sleep
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_05_Sleep {


    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis());
    }
}
