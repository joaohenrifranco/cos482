package com.cos482.ingressa.web.rest;

import com.cos482.ingressa.repository.MovieTicketRepository;
import com.cos482.ingressa.service.MovieTicketService;
import com.cos482.ingressa.service.dto.MovieTicketDTO;
import com.cos482.ingressa.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cos482.ingressa.domain.MovieTicket}.
 */
@RestController
@RequestMapping("/api")
public class MovieTicketResource {

    private final Logger log = LoggerFactory.getLogger(MovieTicketResource.class);

    private final MovieTicketService movieTicketService;

    private final MovieTicketRepository movieTicketRepository;

    public MovieTicketResource(MovieTicketService movieTicketService, MovieTicketRepository movieTicketRepository) {
        this.movieTicketService = movieTicketService;
        this.movieTicketRepository = movieTicketRepository;
    }

    /**
     * {@code GET  /movie-tickets} : get all the movieTickets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movieTickets in body.
     */
    @GetMapping("/movie-tickets")
    public List<MovieTicketDTO> getAllMovieTickets() {
        log.debug("REST request to get all MovieTickets");
        return movieTicketService.findAll();
    }

    /**
     * {@code GET  /movie-tickets/:id} : get the "id" movieTicket.
     *
     * @param id the id of the movieTicketDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movieTicketDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movie-tickets/{id}")
    public ResponseEntity<MovieTicketDTO> getMovieTicket(@PathVariable Long id) {
        log.debug("REST request to get MovieTicket : {}", id);
        Optional<MovieTicketDTO> movieTicketDTO = movieTicketService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movieTicketDTO);
    }
}
