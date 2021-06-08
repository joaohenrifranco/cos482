package com.cos482.ingressa.repository;

import com.cos482.ingressa.domain.MovieTicketProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MovieTicketProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovieTicketProcessRepository extends JpaRepository<MovieTicketProcess, Long> {
    Optional<MovieTicketProcess> findByProcessInstanceId(Long processInstanceId);
}
