package center.helloworld.guava.chapter_01_futures;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhishun.cai
 * @date 2025/2/26
 */
public class Case_02_Callback {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

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


        // 添加回调（非阻塞），之前JDK中提供get方法来阻塞获取结果（这里要是想用get也是可以的）
        Futures.addCallback(future, new FutureCallback<String>() {
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
