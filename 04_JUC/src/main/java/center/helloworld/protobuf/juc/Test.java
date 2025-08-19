package center.helloworld.protobuf.juc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhishun.cai
 * @date 2025/1/15
 */
public class Test {

  static List<Integer> list = new ArrayList<Integer>();

  public static void main(String[] args) throws IOException {

    Thread t1 = null;
    Thread t2 = null;

    Thread finalT1 = t1;
    Thread finalT2 = t2;

    t1 = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(500);
                if(list.size() == 10) {
                    LockSupport.unpark(finalT2);
                    LockSupport.park(finalT1);
                }
                list.add(1);
                System.out.println("t1总共：" + list.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    });


    t2 = new Thread(() -> {
     while (true) {
         try {
             Thread.sleep(500);
             if(list.size() == 0) {
                 LockSupport.unpark(finalT1);
                 LockSupport.park(finalT2);
             }
             list.remove(0);
             System.out.println("t2总共：" + list.size());
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     }
    });

    t1.start();
    t2.start();

    System.in.read();
  }

}
