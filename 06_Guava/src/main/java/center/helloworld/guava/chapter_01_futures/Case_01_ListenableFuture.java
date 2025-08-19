package center.helloworld.guava.chapter_01_futures;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

/**
 * @author zhishun.cai
 * @date 2025/2/26
 */
public class Case_01_ListenableFuture {


    public static void main(String[] args) throws InterruptedException {
        // 创建JDK线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建Guava监听线程池，需要依赖JDK线程池（可以理解为装饰者）
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        // 提交任务
        ListenableFuture<String> future = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("任务开始~~");
                Thread.sleep(2000);
                System.out.println("任务结束~~");
                return "Hello, Guava Futures!";
            }
        });


        System.out.println("end ~~~");
    }
}
