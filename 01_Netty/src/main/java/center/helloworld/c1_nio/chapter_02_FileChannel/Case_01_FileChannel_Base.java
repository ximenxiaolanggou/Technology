package center.helloworld.c1_nio.chapter_02_FileChannel;

import center.helloworld.c1_nio.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * FileChannel 基础
 *
 * @author zhishun.cai
 * @date 2024/10/12
 */
@Slf4j
public class Case_01_FileChannel_Base {

    /**
     *
     * @throws Exception
     */
    @Test
    public void t() throws Exception {

    }


    /**
     * 获取FileChannel
     */
    @Test
    public void acquireFileChannel() throws FileNotFoundException {
        // 通过 FileInputStream 获取的 channel 只能读
        FileChannel channel1 = new FileInputStream("").getChannel();

        // 通过 FileOutputStream 获取的 channel 只能写
        FileChannel channel2 = new FileOutputStream("").getChannel();

        // 通过 RandomAccessFile 是否能读写根据构造 RandomAccessFile 时的读写模式决定
        RandomAccessFile randomAccessFile = new RandomAccessFile("", "rw");
    }

    /**
     * 读取
     * @throws Exception
     */
    @Test
    public void read() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        FileChannel channel = new FileInputStream("").getChannel();

        channel.read(buffer);
    }

    /**
     * 写入
     * @throws Exception
     */
    @Test
    public void write() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // TODO 向buffer中存放数据
        buffer.flip(); // 切换模式
        FileChannel channel = new FileOutputStream("").getChannel();

        // 在 while 中调用 channel.write 是因为 write 方法并不能保证一次将 buffer 中的内容全部写入 channel
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    /**
     * 关闭
     * @throws Exception
     */
    @Test
    public void close() throws Exception {
        FileChannel channel = new FileOutputStream("").getChannel();
        channel.close();
    }


    /**
     * 位置
     * @throws Exception
     */
    @Test
    public void position() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(16);

        FileChannel channel = new FileInputStream("src/data.txt").getChannel();
        // 获取当前位置
        long pos = channel.position();
        log.info("位置为：{}", pos);
        // 设置当前位置
        channel.position(2);
        pos = channel.position();
        log.info("位置为：{}", pos);
        channel.read(buffer);
        ByteBufferUtil.debugAll(buffer);
        channel.close();
    }


    /**
     * size
     * @throws Exception
     */
    @Test
    public void size() throws Exception {
        FileChannel channel = new FileInputStream("src/data.txt").getChannel();
        log.info("文件大小为：{}", channel.size());
    }
}
