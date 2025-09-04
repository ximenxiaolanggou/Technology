package center.helloworld.activiti7;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.jupiter.api.Test;

/**
 * @note: 定时器启动事件
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case_07_Event_Timer_Start {

    /**
     * 部署
     */
    @Test
    public void deployment() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/case_08_event_timer_start.bpmn20.xml")
                .name("事件-定时器-启动")
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());

        Thread.sleep(Integer.MAX_VALUE);
    }


    /**
     * 部署
     */
    @Test
    public void deployment2() throws InterruptedException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/case_09_event_tiemr_start_cycle.bpmn20.xml")
                .name("事件-定时器-启动")
                .deploy();
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());

        Thread.sleep(Integer.MAX_VALUE);
    }


}
