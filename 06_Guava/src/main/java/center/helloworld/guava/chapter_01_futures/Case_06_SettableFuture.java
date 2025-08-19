package center.helloworld.guava.chapter_01_futures;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author zhishun.cai
 * @date 2025/3/5
 */
public class Case_06_SettableFuture {

    public static void main(String[] args) {
        // 创建JDK线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建Guava监听线程池，需要依赖JDK线程池（可以理解为装饰者）
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);


        // 手动创建SettableFuture，SettableFuture实现至ListenableFuture
        SettableFuture<String> settableFuture = SettableFuture.create();

        settableFuture.set("123456");

        ListenableFuture<Integer> transformFuture = Futures.transform(settableFuture, new Function<String, Integer>() {

            @Override
            public Integer apply(String str) {
                return str.length();
            }
        }, listeningExecutorService);

        Futures.addCallback(transformFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer res) {
                System.out.println("success：" + res);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, listeningExecutorService);

    }
}
