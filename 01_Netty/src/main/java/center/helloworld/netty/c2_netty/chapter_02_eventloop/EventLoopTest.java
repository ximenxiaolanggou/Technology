package center.helloworld.netty.c2_netty.chapter_02_eventloop;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutor;
import org.junit.jupiter.api.Test;

/**
 * @author zhishun.cai
 * @create 2023/5/11
 * @note
 */
public class EventLoopTest {

    @Test
    public void el() {
        DefaultEventExecutorGroup group = new DefaultEventExecutorGroup(2);
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
    }

    @Test
    public void el2() {
        DefaultEventExecutorGroup group = new DefaultEventExecutorGroup(2);
        for (EventExecutor eventLoop : group) {
            System.out.println(eventLoop);
        }
    }
}
