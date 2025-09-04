package center.helloworld.activiti7;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

/**
 * @note: 事件 - 启动
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case_10_Event_Start {


    /**
     * 部署
     */
    @Test
    public void deployment() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/case_11_event_start.bpmn20.xml")
                .name("事件 - 启动")
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());

    }

    /**
     * 启动流程
     */
    @Test
    public void start() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance instance = runtimeService.startProcessInstanceByMessage("msg01");

        // 输出相关信息
        System.out.println("流程定义id：" + instance.getProcessDefinitionId());
        System.out.println("流程实例id：" + instance.getId());
        System.out.println("当前活动Id：" + instance.getActivityId());
    }


    /**
     * 张三审批
     */
    @Test
    public void complete() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery().taskAssignee("张三").list().forEach(task -> {
            taskService.complete(task.getId());
        });
        Thread.sleep(Integer.MAX_VALUE);
    }

}
