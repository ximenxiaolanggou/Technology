package center.helloworld.activiti7;


import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @note: 候选人操作
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case_04_Candidate {

    /**
     * 部署
     */
    @Test
    public void deployment() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/case04_candidate.bpmn20.xml")
                .name("候选人")
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
        ProcessInstance instance = runtimeService.startProcessInstanceById("m1:1:52503");

        // 输出相关信息
        System.out.println("流程定义id：" + instance.getProcessDefinitionId());
        System.out.println("流程实例id：" + instance.getId());
        System.out.println("当前活动Id：" + instance.getActivityId());
    }

    /**
     * 根据候选人查询审批流程
     */
    @Test
    public void findByCandidate() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery().taskCandidateOrAssigned("张三").list().forEach(System.out::println);
    }

    /**
     * 任务拾取
     */
    @Test
    public void claim() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery().taskCandidateOrAssigned("张三").list().forEach(task -> {
            taskService.claim(task.getId(), "张三");
        });
    }


    /**
     * 任务归还
     */
    @Test
    public void unclaim() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery().taskCandidateOrAssigned("张三").list().forEach(task -> {
            taskService.unclaim(task.getId());
        });
    }


    /**
     * 任务拾取 + 处理
     */
    @Test
    public void claimAndComplete() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.createTaskQuery().taskCandidateOrAssigned("王五").list().forEach(task -> {
            taskService.claim(task.getId(), "王五");
            taskService.complete(task.getId());
        });
    }




    /**
     * 流程删除
     */
    @Test
    public void deleteProcessDefinition() {
        // 流程部署id
        String deploymentId = "50001";

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
//        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        repositoryService.deleteDeployment(deploymentId, true);
    }


    @Test
    public void findAll() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();

        List<HistoricActivityInstance> historyList =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId("55001")
                        .orderByHistoricActivityInstanceStartTime().asc()
                        .list();

        for (HistoricActivityInstance hai : historyList) {
            System.out.println("节点：" + hai.getActivityName()
                    + "，开始时间：" + hai.getStartTime()
                    + "，结束时间：" + hai.getEndTime());
        }

    }


}
