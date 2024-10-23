package center.helloworld.c1_nio.chapter_01_ByteBuffer;

import center.helloworld.c1_nio.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 *
 * 半包粘包处理
 * @author zhishun.cai
 * @date 2024/10/12
 */
@Slf4j
public class Case_03_ByteBuffer_Halfpackage {


    ByteBuffer cache = ByteBuffer.allocate(16);


    @Test
    public void test() {

    }


    @Test
    public void handle() {
        ByteBuffer source = ByteBuffer.allocate(32);
        //                     11            24
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);

        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    /**
     * 切割函数
     * @param source
     */
    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
                System.out.println(i);
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                // 这里会修改source position指正位置
                target.put(source); // 从source 读，向 target 写
                ByteBufferUtil.debugAll(target);
                source.limit(oldLimit);
            }
        }
        source.compact();
    }
}
