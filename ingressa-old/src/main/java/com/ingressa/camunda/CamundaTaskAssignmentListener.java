package com.ingressa.camunda;

import com.ingressa.domain.TaskInstance;
import com.ingressa.domain.enumeration.StatusTaskInstance;
import com.ingressa.repository.TaskInstanceRepository;
import java.time.Instant;
import java.util.Optional;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class CamundaTaskAssignmentListener implements TaskListener {

    private final TaskInstanceRepository taskInstanceRepository;

    public CamundaTaskAssignmentListener(TaskInstanceRepository taskInstanceRepository) {
        this.taskInstanceRepository = taskInstanceRepository;
    }

    public void notify(DelegateTask delegateTask) {
        Optional<TaskInstance> optionalTaskInstance = taskInstanceRepository.findByTaskId(delegateTask.getId());

        if (optionalTaskInstance.isPresent()) {
            TaskInstance taskInstance = optionalTaskInstance.get();
            taskInstance.setStatus(StatusTaskInstance.ASSIGNED);
            taskInstance.setAssignee(delegateTask.getAssignee());
            taskInstanceRepository.save(taskInstance);
        }
    }
}
