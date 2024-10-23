package center.helloworld.c1_nio.chapter_01_ByteBuffer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * ByteBuffer 基本使用
 * @author zhishun.cai
 * @date 2024/10/11
 */

@Slf4j
public class Case_01_Bytebuffer_Base {


    @Test
    public void byteBuff() throws Exception {
        String filePath = "src/data.txt";

        // 创建缓冲 - 创建后默认是写入模式
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);

        FileChannel channel = new FileInputStream(filePath).getChannel();

        int len = -1;
        while(true) {
            len = channel.read(buffer);
            log.info("读取的长度为：{}", len);

            if(len == -1) {
                // 数据结束退出
                break;
            }

            // 切换buffer读模式
            buffer.flip();
            // 判断是否还有数据
            while (buffer.hasRemaining()) {
                log.info("字符为：{}", (char)buffer.get());
            }
            // 切换buffer写模式
            buffer.clear();
        }
    }
}
