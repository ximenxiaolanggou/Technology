package center.helloworld.guava.chapter_01_futures;

import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 任务全部执行完成后，执行回调
 * 异常处理
 * @author zhishun.cai
 * @date 2025/2/26
 */
public class Case_05_AllAsList {


    public static void main(String[] args) throws InterruptedException {

        // 创建JDK线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建Guava监听线程池，需要依赖JDK线程池（可以理解为装饰者）
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        // 方法1
        ListenableFuture<String> future1 = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "task1";
            }
        });

        // 方法2
        ListenableFuture<String> future2 = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(500);
                return "task2";
            }
        });

        ListenableFuture<List<String>> listListenableFuture = Futures.allAsList(future1, future2);


        Futures.addCallback(listListenableFuture, new FutureCallback<List<String>>() {

            @Override
            public void onSuccess(List<String> list) {
                for (String s : list) {
                    System.out.println(s);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("onFailure：" + throwable.getMessage());
            }
        }, executorService);


        System.out.println("end ~~~");

    }
}
