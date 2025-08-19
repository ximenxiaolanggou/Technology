package center.helloworld.guava.chapter_01_futures;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhishun.cai
 * @date 2025/3/6
 */
public class Case_08_Withtimeout {

    public static void main(String[] args) {
        // 创建JDK线程池
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        // 创建Guava监听线程池，需要依赖JDK线程池（可以理解为装饰者）
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<String> future = listeningExecutorService.submit(() -> {
            Thread.sleep(2000);
            return "success";
        });


        ListenableFuture<String> future1 = Futures.withTimeout(future, 1, TimeUnit.SECONDS, executorService);

        Futures.addCallback(future1, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("success：" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("error：" + throwable.getMessage());
            }
        },listeningExecutorService);
    }
}
