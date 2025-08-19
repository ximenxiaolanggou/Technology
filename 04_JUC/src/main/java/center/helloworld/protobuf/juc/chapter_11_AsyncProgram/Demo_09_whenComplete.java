package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 *
 * WhenComplete方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_09_whenComplete {


    public static void main(String[] args) throws IOException {
        // 任务A、B并行执行，只要任务A或者任务B执行完毕后就执行任务C
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务A");
            return 78;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务B");
            return 26;
        }), r -> {
            System.out.println("任务C");
            return r;
        }).whenComplete((res, ex) -> {
            System.out.println(ex.getMessage());
        });

        int res = future.join();
        System.out.println(res);


        System.in.read();

        //基于前面thenApply，thenAccept，thenRun知道了一般情况三种任务的概念
        //applyToEither，acceptEither，runAfterEither三个方法拼接任务的方式都是一样的
    }
}
