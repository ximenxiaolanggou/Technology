package center.helloworld.protobuf.juc.chapter_01_ThreadCreate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *
 * 创建线程方式：事项callable接口
 * @author zhishun.cai
 * @date 2025/1/9
 */
public class Demo_03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask(new C());

        Thread t = new Thread(task);
        t.start();

        // 做一些其他业务

        // get会阻塞
        String res = task.get();
        System.out.println(res);
    }
}

class C implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Running ~~~");
        return "Hello World";
    }
}
