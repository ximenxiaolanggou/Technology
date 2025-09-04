package center.helloworld.activiti7;


import org.activiti.bpmn.model.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

/**
 * @note: 审批操作
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Case_02_Approval {

    /**
     * 部署
     */
    @Test
    public void deployment() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
        Deployment deployment = deploymentBuilder
                .addClasspathResource("bpmn/evection.bpmn")
                .addClasspathResource("bpmn/evection.png")
                .name("出差申请流程")
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
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("Process_0xmk2zi");

        // 输出相关信息
        System.out.println("流程定义id：" + instance.getProcessDefinitionId());
        System.out.println("流程实例id：" + instance.getId());
        System.out.println("当前活动Id：" + instance.getActivityId());
    }

    /**
     * 任务查询
     */
    @Test
    public void taskQuery() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService
                .createTaskQuery()
                .taskAssignee("张三")
                .list();

        for (Task task : tasks) {
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务负责人：" + task.getAssignee());
            System.out.println("任务名称：" + task.getName());
        }
    }

    /**
     * 流程处理
     */
    @Test
    public void processHandler() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService
                .createTaskQuery()
                .taskAssignee("张三")
                .singleResult();
        taskService.complete(task.getId());
    }


    /**
     * 流程定义信息查询
     */
    @Test
    public void queryProcessDefinition() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // repositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 得到ProcessDefinitionQuery 对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 查询出当前所有的流程定义
        // 条件：processDefinitionKey =evection
        // orderByProcessDefinitionVersion 按照版本排序
        // desc倒叙
        // list 返回集合

        List<ProcessDefinition> definitions = processDefinitionQuery
                .processDefinitionKey("Process_0xmk2zi")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();


        // 输出流程定义信息
        for (ProcessDefinition processDefinition : definitions) {
            System.out.println("流程定义 id="+processDefinition.getId());
            System.out.println("流程定义 name="+processDefinition.getName());
            System.out.println("流程定义 key="+processDefinition.getKey());
            System.out.println("流程定义 Version="+processDefinition.getVersion());
            System.out.println("流程部署ID ="+processDefinition.getDeploymentId());
        }

    }

    /**
     * 流程删除
     */
    @Test
    public void deleteProcessDefinition() {
        // 流程部署id
        String deploymentId = "132501";

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 通过流程引擎获取repositoryService
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
//        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        repositoryService.deleteDeployment(deploymentId, true);
    }

    /**
     * 历史消息查询
     */
    @Test
    public void queryHistory() {
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取HistoryService
        HistoryService historyService = processEngine.getHistoryService();
        // 获取 actinst表的查询对象
        HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
        // 查询 actinst表，条件：根据 InstanceId 查询
        // instanceQuery.processInstanceId("2501");
        // 查询 actinst表，条件：根据 DefinitionId 查询
        instanceQuery.processDefinitionId("Process_0xmk2zi:1:4");
        // 增加排序操作,orderByHistoricActivityInstanceStartTime 根据开始时间排序 asc 升序
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();
        // 查询所有内容
        List<HistoricActivityInstance> activityInstanceList = instanceQuery.list();
        // 输出
        for (HistoricActivityInstance hi : activityInstanceList) {
            System.out.println(hi.getActivityId());
            System.out.println(hi.getActivityName());
            System.out.println(hi.getProcessDefinitionId());
            System.out.println(hi.getProcessInstanceId());
            System.out.println("<==========================>");
        }
    }


    /**
     * 部署
     */
    @Test
    public void query() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        HistoryService historyService = processEngine.getHistoryService();

//        // 根据流程定义ID获取 BpmnModel
//        String processDefinitionId = "Process_0xmk2zi:1:132504"; // 示例：流程定义ID
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
//
//        Collection<FlowElement> flowElements = bpmnModel
//                .getMainProcess()
//                .getFlowElements();
//
//        for (FlowElement e : flowElements) {
////            System.out.println("元素ID: " + e.getId() + "元素名称: " + e.getName() + "元素类型: " + e.getClass().getSimpleName());
//
//            if (e instanceof UserTask) {
//                UserTask task = (UserTask) e;
//                System.out.println("用户任务: " + task.getName() + "，办理人: " + task.getAssignee());
//            } else if (e instanceof StartEvent) {
//                System.out.println("开始事件: " + e.getName());
//            } else if (e instanceof EndEvent) {
//                System.out.println("结束事件: " + e.getName());
//            } else if (e instanceof ExclusiveGateway) {
//                System.out.println("排他网关: " + e.getName());
//            }
//        }

        System.out.println("===============================");

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee("张三")
                .finished().list();
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println(historicTaskInstance.getId() + " " +  historicTaskInstance.getName() + " " + historicTaskInstance.getAssignee());
        }

    }


}
