package com.cos482.ingressa.repository;

import com.cos482.ingressa.domain.ProcessInstance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProcessInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcessInstanceRepository extends JpaRepository<ProcessInstance, Long> {
    Optional<ProcessInstance> findByCamundaProcessInstanceId(String camundaProcessInstanceId);

    List<ProcessInstance> findByProcessDefinitionId(Long processDefinitionId);
}
