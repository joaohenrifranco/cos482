package com.ingressa.repository;

import com.ingressa.domain.TaskInstance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskInstanceRepository extends JpaRepository<TaskInstance, Long> {
    Optional<TaskInstance> findByTaskId(String taskId);

    @Query("from TaskInstance where processDefinition.id = ?1 or processDefinition.bpmnProcessDefinitionId = ?1")
    List<TaskInstance> findByProcessDefinitionIdOrBpmnProcessDefinitionId(String idOrBpmnProcessDefinitionId);

    List<TaskInstance> findByProcessInstanceId(Long processInstanceId);
}
