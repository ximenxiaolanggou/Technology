package center.helloworld.base;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 *
 * 部署操作
 * @author zhishun.cai
 * @date 2025/3/7
 */
public class Base_02_DeploymentOperation {


  /**
   * 部署方式1
   */
  @Test
  public void deloyment1() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();

    Deployment deployment = repositoryService.createDeployment()
            .addClasspathResource("bpmn/evection.bpmn")
            .addClasspathResource("bpmn/evection.png")
            .name("出差申请流程")
            .deploy();


    // 输出部署信息
    System.out.println("流程部署id：" + deployment.getId());
    System.out.println("流程部署名称：" + deployment.getName());

  }

  /**
   * 部署方式2 - 压缩包方式
   */
  @Test
  public void deloyment2() {
    // 定义zip输入流
    InputStream inputStream = this
            .getClass()
            .getClassLoader()
            .getResourceAsStream(
                    "bpmn/evection.zip");
    ZipInputStream zipInputStream = new ZipInputStream(inputStream);

    // 获取repositoryService
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine
            .getRepositoryService();
    // 流程部署
    Deployment deployment = repositoryService.createDeployment()
            .addZipInputStream(zipInputStream)
            .deploy();
    System.out.println("流程部署id：" + deployment.getId());
    System.out.println("流程部署名称：" + deployment.getName());

  }

  /**
   * 启动流程实例
   */
  @Test
  public void startProcess() {
    // 1、创建ProcessEngine
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    // 2、创建RuntimeService
    RuntimeService runtimeService = processEngine.getRuntimeService();
    // 3、根据流程定义Id启动流程
    ProcessInstance instance = runtimeService.startProcessInstanceByKey("myEvection");

    // 输出相关信息
    System.out.println("流程定义id：" + instance.getProcessDefinitionId());
    System.out.println("流程实例id：" + instance.getId());
    System.out.println("当前活动Id：" + instance.getActivityId());
  }

  /**
   * 任务查询
   */
  @Test
  public void findPersonalTaskList() {
    // 任务负责人 - zhangsan
    String assigner = "zhangsan";
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    // 创建TaskService
    TaskService taskService = processEngine.getTaskService();

    // 根据流程定义key和指派人查询任务
    List<Task> tasks = taskService
            .createTaskQuery()
            .processDefinitionKey("myEvection")
            .taskAssignee(assigner)
            .list();

    for (Task task : tasks) {
      System.out.println("流程实例id：" + task.getProcessInstanceId());
      System.out.println("任务id：" + task.getId());
      System.out.println("任务负责人：" + task.getAssignee());
      System.out.println("任务名称：" + task.getName());
    }
  }

  /**
   * 完成任务
   */
  @Test
  public void completTask() {
    // 获取引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    // 获取taskService
    TaskService taskService = processEngine.getTaskService();

    // 根据流程key 和 任务的负责人 查询任务
    // 返回一个任务对象
    Task task = taskService.createTaskQuery()
            .processDefinitionKey("myEvection") //流程Key
            .taskAssignee("rose")  //要查询的负责人
            .singleResult();
    // 完成任务
    taskService.complete(task.getId());
  }


  /**
   * 删除流程
   */
  @Test
  public void deleteDeployment() {
    // 流程部署id
    String deploymentId = "1";

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    // 通过流程引擎获取repositoryService
    RepositoryService repositoryService = processEngine
            .getRepositoryService();
    //删除流程定义，如果该流程定义已有流程实例启动则删除时出错
    repositoryService.deleteDeployment(deploymentId);
    //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
    //repositoryService.deleteDeployment(deploymentId, true);
  }

  /**
   * 下载文件
   */
  public void  queryBpmnFile() throws IOException, IOException {
    //1、得到引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //2、获取repositoryService
    RepositoryService repositoryService = processEngine.getRepositoryService();
    //3、得到查询器：ProcessDefinitionQuery，设置查询条件,得到想要的流程定义
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
            .processDefinitionKey("myEvection")
            .singleResult();
    //4、通过流程定义信息，得到部署ID
    String deploymentId = processDefinition.getDeploymentId();
    //5、通过repositoryService的方法，实现读取图片信息和bpmn信息
    //png图片的流
    InputStream pngInput = repositoryService.getResourceAsStream(deploymentId, processDefinition.getDiagramResourceName());
    //bpmn文件的流
    InputStream bpmnInput = repositoryService.getResourceAsStream(deploymentId, processDefinition.getResourceName());
    //6、构造OutputStream流
    File file_png = new File("d:/evectionflow01.png");
    File file_bpmn = new File("d:/evectionflow01.bpmn");
    FileOutputStream bpmnOut = new FileOutputStream(file_bpmn);
    FileOutputStream pngOut = new FileOutputStream(file_png);
    //7、输入流，输出流的转换
    IOUtils.copy(pngInput,pngOut);
    IOUtils.copy(bpmnInput,bpmnOut);
    //8、关闭流
    pngOut.close();
    bpmnOut.close();
    pngInput.close();
    bpmnInput.close();
  }

  /**
   * 历史信息查询
   */
  @Test
  public void findHistoryInfo() {
    // 获取引擎
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    // 获取HistoryService
    HistoryService historyService = processEngine.getHistoryService();
    // 获取 actinst表的查询对象
    HistoricActivityInstanceQuery instanceQuery = historyService.createHistoricActivityInstanceQuery();
    // 查询 actinst表，条件：根据 InstanceId 查询
    // instanceQuery.processInstanceId("2501");
    // 查询 actinst表，条件：根据 DefinitionId 查询
    instanceQuery.processDefinitionId("myEvection:1:4");
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
}
