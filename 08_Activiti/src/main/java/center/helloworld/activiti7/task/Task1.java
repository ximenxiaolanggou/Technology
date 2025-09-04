package center.helloworld.activiti7.task;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;

/**
 * @note:
 * @author: zhishun.cai
 * @date: 2025/9/1
 */

public class Task1 implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {
        if(EVENTNAME_CREATE.equals(delegateTask.getEventName())){
            // 表示是Task的创建事件被触发了
            // 指定当前Task节点的处理人
            delegateTask.setAssignee("zhansan");
        }
    }
}
