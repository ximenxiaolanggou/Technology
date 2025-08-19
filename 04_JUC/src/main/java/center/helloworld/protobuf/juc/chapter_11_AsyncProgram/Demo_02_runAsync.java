package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * RunAsync方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_02_runAsync {


    public static void main(String[] args) throws IOException {
        // 不会接收参数，也不会返回结果
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("异步任务开始执行");
            System.out.println("异步任务执行结束");
        });

        System.in.read();

    }
}
