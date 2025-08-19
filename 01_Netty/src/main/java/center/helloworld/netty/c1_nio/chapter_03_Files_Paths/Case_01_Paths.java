package center.helloworld.netty.c1_nio.chapter_03_Files_Paths;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Paths 工具类
 * @author zhishun.cai
 * @date 2024/10/12
 */

@Slf4j
public class Case_01_Paths {

    @Test
    public void path() {
        Path path = Paths.get("");
    }
}
