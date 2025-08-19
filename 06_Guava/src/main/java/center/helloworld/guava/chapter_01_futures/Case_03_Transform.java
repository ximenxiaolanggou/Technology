package center.helloworld.guava.chapter_01_futures;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhishun.cai
 * @date 2025/2/26
 */
public class Case_03_Transform {


    public static void main(String[] args) throws InterruptedException {

        // 创建JDK线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建Guava监听线程池，需要依赖JDK线程池（可以理解为装饰者）
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        // 提交任务 - 生成UUID
        ListenableFuture<String> future = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return UUID.randomUUID().toString();
            }
        });

        // 将UUID中的 '-' 去除掉
        ListenableFuture<String> transformFuture = Futures.transform(future, new Function<String, String>() {

            @Override
            public String apply(String s) {
                return s.replace("-", "");
            }
        }, listeningExecutorService);


        // 添加回调（非阻塞），之前JDK中提供get方法来阻塞获取结果（这里要是想用get也是可以的）
        Futures.addCallback(transformFuture, new FutureCallback<String>() {

            @Override
            public void onSuccess(String result) {
                System.out.println("Success: " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.err.println("Failure: " + t.getMessage());
            }
        }, executorService);



        System.out.println("end ~~~");

    }
}
