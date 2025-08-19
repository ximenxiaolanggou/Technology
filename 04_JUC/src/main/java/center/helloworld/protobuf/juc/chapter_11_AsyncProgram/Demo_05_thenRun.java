package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 *
 * ThenRun方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_05_thenRun {


    public static void main(String[] args) throws IOException {
        // 任务A执行后，执行任务B
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            String id = UUID.randomUUID().toString();
            System.out.println("任务A生成ID：" + id);
            return id;
        });

        // thenRun 是Function，没有参数，没有返回结果
        taskA.thenRun(() -> {
            String id = UUID.randomUUID().toString();
            System.out.println("任务B生成ID：" + id);
        });

        System.in.read();
    }
}
