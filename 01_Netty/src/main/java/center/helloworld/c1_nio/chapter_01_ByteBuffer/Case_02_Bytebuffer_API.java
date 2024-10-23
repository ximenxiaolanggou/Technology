package center.helloworld.c1_nio.chapter_01_ByteBuffer;

import center.helloworld.c1_nio.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 *
 * ByteBuffer API
 * @author zhishun.cai
 * @date 2024/10/11
 */

@Slf4j
public class Case_02_Bytebuffer_API {


    /**
     * 分配内存空间
     * @throws Exception
     */
    @Test
    public void allocateBuffer() throws Exception {
        // allocate分配的是堆内存
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // allocateDirect 分配的是直接内存
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(10);
    }

    /**
     * 向buffer中写入数据
     */
    @Test
    public void writeBuffer() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        // 方式1：调用写入方法
        buffer.put((byte) 127);

        // 方式2：channel的read方法
        buffer.clear();
        FileChannel channel = new FileInputStream("src/data.txt").getChannel();
        channel.read(buffer);
    }


    /**
     * 向buffer中读取数据
     */
    @Test
    public void readBuffer() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 99);

        // 方式1：调用写入方法
        buffer.flip();
        log.info("读出数据：{}", buffer.get());
        log.info("读出数据：{}", buffer.get());
        log.info("读出数据：{}", buffer.get());


        // 方式2：channel的read方法
        buffer.clear();
        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 99);

        FileChannel channel = new FileOutputStream("src/data1.txt").getChannel();
        channel.write(buffer);
    }

    /**
     * mark和reset
     */
    @Test
    public void mark() {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        buffer.put((byte) 97);
        buffer.put((byte) 98);
        buffer.put((byte) 99);

        buffer.flip();

        log.info("读出数据：{}", buffer.get());

        buffer.mark();
        log.info("标记后读出数据：{}", buffer.get());
        log.info("标记后读出数据：{}", buffer.get());

        buffer.reset();
        log.info("第一次重置标记后读出数据：{}", buffer.get());
        log.info("第一次重置标记后读出数据：{}", buffer.get());

        buffer.reset();
        log.info("第二次重置标记后读出数据：{}", buffer.get());
        log.info("第二次重置标记后读出数据：{}", buffer.get());
    }

    /**
     * 字符串与 ByteBuffer 互转
     */
    @Test
    public void stringByteBufferConver() {
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("你好，China！");
        ByteBufferUtil.debugAll(buffer1);

        CharBuffer buffer2 = StandardCharsets.UTF_8.decode(buffer1);

        log.info("{}", buffer2.getClass());
        log.info("{}", buffer2.toString());

    }


    /**
     * Scattering Reads
     */
    @Test
    public void scatteringRead() {
        try (RandomAccessFile file = new RandomAccessFile("src/data.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer a = ByteBuffer.allocate(3);
            ByteBuffer b = ByteBuffer.allocate(3);
            ByteBuffer c = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{a, b, c});
            a.flip();
            b.flip();
            c.flip();
            ByteBufferUtil.debugAll(a);
            ByteBufferUtil.debugAll(b);
            ByteBufferUtil.debugAll(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Gathering Writes
     */
    @Test
    public void gatheringWrites() {
        try (RandomAccessFile file = new RandomAccessFile("src/data.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer d = ByteBuffer.allocate(4);
            ByteBuffer e = ByteBuffer.allocate(4);
            channel.position(11);

            d.put(new byte[]{'f', 'o', 'u', 'r'});
            e.put(new byte[]{'f', 'i', 'v', 'e'});
            d.flip();
            e.flip();
            ByteBufferUtil.debugAll(d);
            ByteBufferUtil.debugAll(e);
            channel.write(new ByteBuffer[]{d, e});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
