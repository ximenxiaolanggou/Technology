package center.helloworld.advanced;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 流程挂起和激活
 * @author zhishun.cai
 * @date 2025/3/7
 */
public class Base_02_SuspendProcess {

    /**
     * 全部流程实例挂起与激活
     */
    @Test
    public void suspendAllProcessInstance() {

        // 创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 创建RuntimeService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 查询流程定义
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey("myEvection")
                .singleResult();
        
        // 状态
        boolean suspended = processDefinition.isSuspended();

        // 流程定义ID
        String processDefinitionId = processDefinition.getId();

        // 判断是否为暂停
        if(suspended) {
            // 如果是暂停，可以执行激活操作 ,参数1 ：流程定义id ，参数2：是否激活，参数3：激活时间
            repositoryService.activateProcessDefinitionById(processDefinitionId,
                    true,
                    null
            );
            System.out.println("流程定义："+processDefinitionId+",已激活");
        }else {
            // 如果是激活状态，可以暂停，参数1 ：流程定义id ，参数2：是否暂停，参数3：暂停时间
            repositoryService.suspendProcessDefinitionById(processDefinitionId,
                    true,
                    null);
            System.out.println("流程定义："+processDefinitionId+",已挂起");
        }
    }
}
