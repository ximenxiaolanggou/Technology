package center.helloworld.protobuf.juc.chapter_03_ThreadWay;

/**
 *
 * 线程常用方式：设置线程名称
 *
 * @author zhishun.cai
 * @date 2025/1/10
 */
public class Demo_02_SetThreadName {


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {}, "模块名称-功能名称-序号");

        // 可以通过构造方法设置线程名称,也可以通过set方法设置
        t1.setName("模块名称-功能名称-序号");
        System.out.println(t1.getName());
    }
}
