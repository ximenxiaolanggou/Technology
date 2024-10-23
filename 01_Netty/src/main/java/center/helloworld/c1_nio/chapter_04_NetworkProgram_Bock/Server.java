package center.helloworld.c1_nio.chapter_04_NetworkProgram_Bock;

import center.helloworld.c1_nio.util.ByteBufferUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;


/**
 * 服务端 - 阻塞
 * @author zhishun.cai
 * @date 2024/10/12
 */
public class Server {

    public static void main(String[] args) throws Exception {
        // 数据
        ByteBuffer buffer = ByteBuffer.allocate(100);
        // 连接集合
        ArrayList<SocketChannel> channels = new ArrayList<>();
        // 1. 创建服务器
        ServerSocketChannel server = ServerSocketChannel.open();
        // 2. 绑定服务器端口
        server.bind(new InetSocketAddress(8080));

        while (true) {
            SocketChannel channel = server.accept();
            channels.add(channel);
            channel.read(buffer);
            buffer.flip();
            ByteBufferUtil.debugRead(buffer);
            buffer.clear();
        }
    }
}
