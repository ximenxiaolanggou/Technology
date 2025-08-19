package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 *
 * ThenApply方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_03_thenApply {


    public static void main(String[] args) throws IOException {
        // 任务A执行后，执行任务B
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            String id = UUID.randomUUID().toString();
            System.out.println("任务A生成ID：" + id);
            return id;
        });

        // thenApply 是Function，有参数，并且有返回结果
        CompletableFuture<String> taskB = taskA.thenApply(ele -> {
            ele = ele.replace("-", "");
            System.out.println("任务B处理ID：" + ele);
            return ele;
        });

        String res = taskB.join();
        System.out.println("ID：" + res);

        System.in.read();

    }
}
