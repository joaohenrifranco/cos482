package com.cos482.ingressa.repository;

import com.cos482.ingressa.domain.MovieTicket;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MovieTicket entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovieTicketRepository extends JpaRepository<MovieTicket, Long> {}
