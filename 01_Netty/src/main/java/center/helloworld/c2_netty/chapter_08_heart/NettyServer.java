package center.helloworld.c2_netty.chapter_08_heart;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhishun.cai
 * @create 2023/5/11
 * @note Netty 服务端
 */
@Slf4j
public class NettyServer {


    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup()) // 1
                .channel(NioServerSocketChannel.class) // 2
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 3
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new IdleStateHandler(5,0,0)); // 5
                        ch.pipeline().addLast(new ChannelDuplexHandler() {
                            @Override
                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                IdleStateEvent event = (IdleStateEvent) evt;
                                // 读事件
                                if(event.state() == IdleState.READER_IDLE) {
                                    System.out.println("检测到5s未接收到数据~~");
                                    ctx.channel().close();
                                }
                            }

                        });
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() { // 6
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8080); // 4
    }
}
