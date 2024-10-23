package center.helloworld.c1_nio.chapter_03_Files_Paths;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Files 工具类
 * @author zhishun.cai
 * @date 2024/10/12
 */

@Slf4j
public class Case_02_Files {

    @Test
    public void file() throws Exception {
        // 1. 判断文件是否存在
        boolean exist = Files.exists(Paths.get(""));

        // 2. 创建一级目录
        Files.createDirectory(Paths.get(""));

        // 3. 创建多级目录
        Files.createDirectories(Paths.get(""));

        // 4. 拷贝文件
        Path source = Paths.get("helloword/data.txt");
        Path target = Paths.get("helloword/target.txt");
        Files.copy(source, target);

        // 5. 移动文件
        Path source5 = Paths.get("helloword/data.txt");
        Path target5 = Paths.get("helloword/data.txt");

        // StandardCopyOption.ATOMIC_MOVE 保证文件移动的原子性
        Files.move(source5, target5, StandardCopyOption.ATOMIC_MOVE);

        // 6. 删除文件
        Path target6 = Paths.get("helloword/target.txt");

        Files.delete(target6);

        // 7. 删除目录
        Path target7 = Paths.get("helloword/d1");

        Files.delete(target7);
    }
}
