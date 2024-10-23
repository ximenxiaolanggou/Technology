package center.helloworld.c1_nio.chapter_02_FileChannel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 *
 * 拷贝文件
 *
 * @author zhishun.cai
 * @date 2024/10/12
 */
@Slf4j
public class Case_02_FileChannel_CopyFile {

    /**
     * 拷贝文件
     * @throws Exception
     */
    @Test
    public void copy() throws Exception {
        FileChannel source = new FileInputStream("src/data.txt").getChannel();
        FileChannel des = new FileOutputStream("src/data2.txt").getChannel();

        // 拷贝文件
        source.transferTo(0, source.size(), des);
    }


    /**
     * 拷贝大文件
     * @throws Exception
     */
    @Test
    public void copyBigFile() throws Exception {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("to.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
