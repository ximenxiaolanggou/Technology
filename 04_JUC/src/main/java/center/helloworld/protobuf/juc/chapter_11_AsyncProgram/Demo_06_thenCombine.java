package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 *
 * ThenCombine方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_06_thenCombine {


    public static void main(String[] args) throws IOException {
        // 任务A、B并行执行后，执行任务C
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务A");
            return 78;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务B");
            return 26;
        }), (r1, r2) -> {
            System.out.println("任务C");
            return r1 + r2;
        });

        int res = future.join();
        System.out.println(res);


        System.in.read();

        //基于前面thenApply，thenAccept，thenRun知道了一般情况三种任务的概念
        //thenCombine以及thenAcceptBoth还有runAfterBoth的区别是一样的。
    }
}
