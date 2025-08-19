package center.helloworld.protobuf.juc.chapter_05_Synchronized;

/**
 * synchronized类锁和对象锁
 */
public class Demo_01_synchronized_Class_Obj {


}

class Test {
    // 类锁 Test.class
    public static synchronized void a() {

    }

    // 对象锁 this
    public synchronized void b() {

    }
}
