package com.ingressa.camunda;

import com.ingressa.domain.ProcessDefinition;
import com.ingressa.domain.enumeration.StatusTaskInstance;
import com.ingressa.repository.ProcessDefinitionRepository;
import com.ingressa.service.TaskInstanceService;
import com.ingressa.service.dto.ProcessInstanceDTO;
import com.ingressa.service.dto.TaskInstanceDTO;
import com.ingressa.service.mapper.ProcessDefinitionMapper;
import java.time.Instant;
import java.util.Optional;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

@Component
public class CamundaTaskCreateListener implements TaskListener {

    private final TaskInstanceService taskInstanceService;

    private final ProcessDefinitionRepository processDefinitionRepository;

    private final ProcessDefinitionMapper processDefinitionMapper;

    public CamundaTaskCreateListener(
        TaskInstanceService taskInstanceService,
        ProcessDefinitionRepository processDefinitionRepository,
        ProcessDefinitionMapper processDefinitionMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.processDefinitionRepository = processDefinitionRepository;
        this.processDefinitionMapper = processDefinitionMapper;
    }

    public void notify(DelegateTask delegateTask) {
        TaskInstanceDTO taskInstanceDTO = delegateTaskToTaskInstanceDTO(delegateTask);
        taskInstanceService.save(taskInstanceDTO);
    }

    private TaskInstanceDTO delegateTaskToTaskInstanceDTO(DelegateTask delegateTask) {
        TaskInstanceDTO taskInstanceDTO = new TaskInstanceDTO();
        taskInstanceDTO.setTaskId(delegateTask.getId());
        taskInstanceDTO.setName(delegateTask.getName());
        taskInstanceDTO.setStatus(StatusTaskInstance.NEW);
        taskInstanceDTO.setDescription(delegateTask.getDescription());
        taskInstanceDTO.setCreateDate(Instant.now());
        taskInstanceDTO.setCreateTime(Instant.now());
        taskInstanceDTO.setAssignee(delegateTask.getAssignee());
        taskInstanceDTO.setExecutionId(delegateTask.getExecutionId());
        taskInstanceDTO.setTaskDefinitionKey(delegateTask.getTaskDefinitionKey());
        //taskInstanceDTO.setSuspended(delegateTask.getCaseExecution().isSuspended());
        taskInstanceDTO.setPriority(delegateTask.getPriority());

        ProcessInstanceDTO processInstance = new ProcessInstanceDTO();
        processInstance.setId(1L);
        processInstance.setCamundaProcessInstanceId(delegateTask.getProcessInstanceId());
        taskInstanceDTO.setProcessInstance(processInstance);

        Optional<ProcessDefinition> optionalProcessDefinition = processDefinitionRepository.findByBpmnProcessDefinitionId(
            delegateTask.getProcessDefinitionId().split(":")[0]
        );
        if (optionalProcessDefinition.isPresent()) {
            taskInstanceDTO.setProcessDefinition(processDefinitionMapper.toDto(optionalProcessDefinition.get()));
        }

        return taskInstanceDTO;
    }
}
