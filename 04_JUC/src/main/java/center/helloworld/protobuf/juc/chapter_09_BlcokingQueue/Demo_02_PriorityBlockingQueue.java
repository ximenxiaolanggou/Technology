package center.helloworld.protobuf.juc.chapter_09_BlcokingQueue;

import java.util.concurrent.PriorityBlockingQueue;

public class Demo_02_PriorityBlockingQueue {

    public static void main(String[] args) {

        demo_01();
    }

    public static void demo_01() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

        queue.offer(3);
        queue.offer(5);
        queue.offer(2);
        queue.offer(1);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
