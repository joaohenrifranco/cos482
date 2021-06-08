package com.cos482.ingressa.camunda;

import com.cos482.ingressa.domain.TaskInstance;
import com.cos482.ingressa.domain.enumeration.StatusTaskInstance;
import com.cos482.ingressa.repository.TaskInstanceRepository;
import java.time.Instant;
import java.util.Optional;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class CamundaTaskCompleteListener implements TaskListener {

    private final TaskInstanceRepository taskInstanceRepository;

    public CamundaTaskCompleteListener(TaskInstanceRepository taskInstanceRepository) {
        this.taskInstanceRepository = taskInstanceRepository;
    }

    public void notify(DelegateTask delegateTask) {
        Optional<TaskInstance> optionalTaskInstance = taskInstanceRepository.findByTaskId(delegateTask.getId());

        if (optionalTaskInstance.isPresent()) {
            TaskInstance taskInstance = optionalTaskInstance.get();
            taskInstance.setStatus(StatusTaskInstance.COMPLETED);
            taskInstance.endTime(Instant.now());
            taskInstanceRepository.save(taskInstance);
        }
    }
}
