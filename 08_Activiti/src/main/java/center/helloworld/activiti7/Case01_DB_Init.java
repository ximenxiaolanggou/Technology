package center.helloworld.activiti7;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

/**
 * @note: 数据库初始化
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case01_DB_Init {

    @Test
    public void dbInit() {
//        使用classpath下的activiti.cfg.xml中的配置创建processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
