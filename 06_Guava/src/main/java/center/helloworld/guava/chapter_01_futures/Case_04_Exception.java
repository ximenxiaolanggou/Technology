package center.helloworld.guava.chapter_01_futures;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 异常处理
 * @author zhishun.cai
 * @date 2025/2/26
 */
public class Case_04_Exception {


    public static void main(String[] args) throws InterruptedException {

        // 创建JDK线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建Guava监听线程池，需要依赖JDK线程池（可以理解为装饰者）
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        // 抛出异常
        ListenableFuture<String> future = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                int i = 1 / 0;
                return null;
            }
        });

        // 捕获异常，如果抛出的异常是上面定义的，然后进入方法体，说明异常已被处理，然后会进入回调的onSuccess方法中
        // 如果抛出的异常在下面没有定义，则无法进入方法体内，然后回进入回调的onFailure方法中
        ListenableFuture<String> catchingFuture = Futures.catching(future, ArithmeticException.class, e -> {
            String msg = "异常被处理, " + e.getMessage();
            return msg;
        }, executorService);


        Futures.addCallback(catchingFuture, new FutureCallback<String>() {

            @Override
            public void onSuccess(String s) {
                System.out.println("success：" + s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("onFailure：" + throwable.getMessage());
            }
        }, executorService);


        System.out.println("end ~~~");

    }
}
