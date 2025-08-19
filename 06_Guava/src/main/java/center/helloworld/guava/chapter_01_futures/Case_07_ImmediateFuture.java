package center.helloworld.guava.chapter_01_futures;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/**
 *
 * 如果API不是异步的，但您需要封装已完成的操作转换为 ListenableFuture，则可以创建 ImmediateFuture。
 * 这个使用 Futures.immediateFuture(...) 工厂方法。
 *
 * @author zhishun.cai
 * @date 2025/3/6
 */
public class Case_07_ImmediateFuture {


    public static void main(String[] args) {

    }

    public static ListenableFuture<String> getUser() {
        try {
            String user = user();
            return Futures.immediateFuture(user);
        } catch (Exception e) {
            return Futures.immediateFailedFuture(e);
        }
    }

    public static String user() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "damoncai";
    }
}
