package center.helloworld.c1_nio.chapter_05_NetworkProgram_NoBock;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


/**
 * 客户端 - 阻塞
 * @author zhishun.cai
 * @date 2024/10/12
 */
public class Client {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("Hello,world".getBytes());

        // 1. 创建客户端
        SocketChannel client = SocketChannel.open();

        // 2. 连接
        client.connect(new InetSocketAddress(8080));
        Thread.sleep(100);
        buffer.flip();
        client.write(buffer);
        System.in.read();
    }
}
