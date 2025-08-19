package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 *
 * AnyOf方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_11_anyOf {


    public static void main(String[] args) throws IOException {
        // 所有任务其中一个执行后，就可以执行下面任务
        CompletableFuture.anyOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("任务A");
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("任务B");
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("任务C");
                })
        ).thenRun(() -> {
            System.out.println("任务D");
        });

        System.in.read();

    }
}
