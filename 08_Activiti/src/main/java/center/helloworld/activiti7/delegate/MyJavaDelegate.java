package center.helloworld.activiti7.delegate;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;

/**
 * @note:
 * @author: zhishun.cai
 * @date: 2025/9/2
 */

public class MyJavaDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("MyJavaDelegate: " + LocalDateTime.now());
    }
}
