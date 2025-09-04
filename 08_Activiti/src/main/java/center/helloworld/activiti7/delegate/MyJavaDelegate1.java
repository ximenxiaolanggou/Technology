package center.helloworld.activiti7.delegate;


import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

/**
 * @note:
 * @author: zhishun.cai
 * @date: 2025/9/2
 */

public class MyJavaDelegate1 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("MyJavaDelegate1: " + LocalDateTime.now());
        // 抛出错误 触发 子流程中的错误开始事件
        throw new BpmnError("error01");
    }
}
