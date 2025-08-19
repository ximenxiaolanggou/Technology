package center.helloworld.protobuf.juc.chapter_10_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// newFixedThreadPool固定线程个数
public class Demo_01_newFixedThreadPool {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });

        threadPool.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });

    }
}
