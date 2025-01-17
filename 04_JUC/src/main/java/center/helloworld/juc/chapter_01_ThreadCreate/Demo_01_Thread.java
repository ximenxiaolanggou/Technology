package center.helloworld.juc.chapter_01_ThreadCreate;

/**
 * 创建线程方式：继承Thread类
 * @author zhishun.cai
 * @date 2025/1/9
 */
public class Demo_01_Thread {

    public static void main(String[] args) throws Exception {
        T t = new T();
        t.start();
        Thread.sleep(1000);
    }
}

class T extends Thread {

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        System.out.println("Running ~~");
    }
}
