package center.helloworld.advanced;

import lombok.var;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 关联BusinessKey
 * @author zhishun.cai
 * @date 2025/3/7
 */
public class Base_01_BusinessKey {

    /**
     * 关联BusinessKey
     */
    @Test
    public void addBusinessKey() {

        // 创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 创建RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 创建ProcessInstance并关联BusinessKey
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("myEvection", "1001");

        // 输出信息
        System.out.println("业务id=="+processInstance.getBusinessKey());
    }

    /**
     * 查询运行实例 - 携带BusinessKey
     */
    @Test
    public void getProcessInstance() {
        // 流程定义key
        String processDefinitionKey = "myEvection";
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> list = runtimeService
                .createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .list();

        for (ProcessInstance processInstance : list) {
            System.out.println("----------------------------");
            System.out.println("流程实例id："
                    + processInstance.getProcessInstanceId());
            System.out.println("所属流程定义id："
                    + processInstance.getProcessDefinitionId());
            System.out.println("是否执行完成：" + processInstance.isEnded());
            System.out.println("是否暂停：" + processInstance.isSuspended());
            System.out.println("当前活动标识：" + processInstance.getActivityId());
            System.out.println("BusinessKey：" + processInstance.getBusinessKey());
        }
    }
}
