package center.helloworld.base;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

/**
 *
 * 初始化数据库
 * @author zhishun.cai
 * @date 2025/3/7
 */
public class Base_01_InitDB {

    @Test
    public void initDB() {
        //使用classpath下的activiti.cfg.xml中的配置创建processEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
}
