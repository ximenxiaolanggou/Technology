package center.helloworld.c1_nio.chapter_06_Select_Accept;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 服务端 - 非阻塞
 * @author zhishun.cai
 * @date 2024/10/12
 */
public class Server {

    @Test
    public void server() throws IOException {
        List<SocketChannel> channels = new ArrayList<>();

        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 设置为非阻塞模式
        ssc.configureBlocking(false);

        // 3. 创建选择器并注册监听ACCEPT事件
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        // 4.绑定端口
        ssc.bind(new InetSocketAddress(8080));

        while (true) {

            int count = selector.select(); // 阻塞
            // 获取所有事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();// 非阻塞
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if(key.isAcceptable()) { // 连接事件
                    ServerSocketChannel c = (ServerSocketChannel) key.channel();
                    // 必须处理
                    SocketChannel channel = c.accept();
                    channel.configureBlocking(false);
                    channels.add(channel);
                    System.out.println(channel.getRemoteAddress());
                }
                // 处理完毕，必须将事件移除
                iter.remove();
            }
        }
    }
}