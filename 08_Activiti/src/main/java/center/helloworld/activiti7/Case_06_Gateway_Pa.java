package center.helloworld.activiti7;


import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @note: 排他网关
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case_06_Gateway_Pa {

    /**
     * 部署
     */
    @Test
    public void deployment() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/case_07_gateway_par.bpmn20.xml")
                .name("排他网关")
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 启动流程
     */
    @Test
    public void start() {
        Map<String, Object> variables = new HashMap<String, Object>();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance instance = runtimeService.startProcessInstanceById("g2:1:77503",variables);

        // 输出相关信息
        System.out.println("流程定义id：" + instance.getProcessDefinitionId());
        System.out.println("流程实例id：" + instance.getId());
        System.out.println("当前活动Id：" + instance.getActivityId());
    }

    /**
     * 发起流程后先自己创建节点过
     */
    @Test
    public void complete() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        // 张三 complete
        taskService.createTaskQuery().taskAssignee("张三").list().forEach(task -> {
            taskService.complete(task.getId());
        });

        // r1 complete
        taskService.createTaskQuery().taskAssignee("r1").list().forEach(task -> {
            taskService.complete(task.getId());
        });

        // r2 complete
        taskService.createTaskQuery().taskAssignee("r2").list().forEach(task -> {
            taskService.complete(task.getId());
        });

        // r3 complete
        taskService.createTaskQuery().taskAssignee("r3").list().forEach(task -> {
            taskService.complete(task.getId());
        });
    }


}
