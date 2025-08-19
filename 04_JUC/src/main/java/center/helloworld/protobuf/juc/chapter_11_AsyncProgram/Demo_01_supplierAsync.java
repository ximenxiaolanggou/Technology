package center.helloworld.protobuf.juc.chapter_11_AsyncProgram;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 * SupplierAsync方法
 *
 * @author zhishun.cai
 * @date 2025/2/8
 */
public class Demo_01_supplierAsync {


    public static void main(String[] args) throws IOException {
        CompletableFuture<String> firstTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            System.out.println("异步任务执行结束");
            return "SUCCESS";
        });

        // join和get都是获取返回结果，区别在于get内部处理了异常，get是需要自己处理异常
        String res1 = firstTask.join();
        String res2 = null;
        try {
            res2 = firstTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("res1：" + res1);
        System.out.println("res2：" + res2);

        // 因为CompletableFuture中的线程为守护线程，main线程结束都会结束，所以这里需要阻塞
        System.in.read();

    }
}
