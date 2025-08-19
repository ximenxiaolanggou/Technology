package center.helloworld.protobuf.juc.chapter_01_ThreadCreate;

/**
 *
 * 创建线程方式：实现Runnerable接口
 *
 * @author zhishun.cai
 * @date 2025/1/9  
 */  
public class Demo_02_Runnerable {

  public static void main(String[] args) throws Exception {
    Thread t = new Thread(new R());
    t.start();
    Thread.sleep(1000);
  }
}

class R implements Runnable {

  @Override
  public void run() {
    System.out.println("Running ~~");
  }
}
