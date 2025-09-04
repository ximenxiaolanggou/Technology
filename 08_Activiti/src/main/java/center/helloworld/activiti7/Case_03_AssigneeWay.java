package center.helloworld.activiti7;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @note: 任务分配
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case_03_AssigneeWay {

    /**
     * 部署
     */
    @Test
    public void deployment() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/case03_assignee_listener.bpmn")
                .name("出差申请流程")
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 值表达式启动流程
     */
    @Test
    public void startProcessByValueExpress() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map map = new HashMap();
        map.put("assignee1","武松");
        map.put("assignee2","book");

        runtimeService.startProcessInstanceByKey("Process_0xmk2zi", map);
    }

    /**
     * 方法达式启动流程
     */
    @Test
    public void startProcessByMethodExpress() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("Process_0xmk2zi");
    }
}
