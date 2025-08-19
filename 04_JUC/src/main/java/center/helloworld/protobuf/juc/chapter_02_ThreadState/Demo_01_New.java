package center.helloworld.protobuf.juc.chapter_02_ThreadState;

/**
 * 线程状态：NEW
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_01_New {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("子线程运行~~");
        });

        // NEW
        System.out.println(t1.getState());
    }
}
