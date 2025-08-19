package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 *
 * ThenAccept方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_04_thenAccept {


    public static void main(String[] args) throws IOException {
        // 任务A执行后，执行任务B
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            String id = UUID.randomUUID().toString();
            System.out.println("任务A生成ID：" + id);
            return id;
        });

        // thenAccept 是Consumer，有参数，但是没有返回结果
        taskA.thenAccept(ele -> {
            ele = ele.replace("-", "");
            System.out.println("任务B处理ID：" + ele);
        });

        System.in.read();
    }
}
