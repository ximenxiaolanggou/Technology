package center.helloworld.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：获取当前线程
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_01_GetThread {


    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        // "Thread[" + getName() + "," + getPriority() + "," +  group.getName() + "]";
        // Thread[main,5,main]
        System.out.println(thread);
    }
}
