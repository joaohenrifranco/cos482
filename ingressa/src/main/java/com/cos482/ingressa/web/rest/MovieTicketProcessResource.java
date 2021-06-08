package com.cos482.ingressa.web.rest;

import com.cos482.ingressa.service.MovieTicketProcessService;
import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import com.cos482.ingressa.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cos482.ingressa.domain.MovieTicketProcess}.
 */
@RestController
@RequestMapping("/api")
public class MovieTicketProcessResource {

    private final Logger log = LoggerFactory.getLogger(MovieTicketProcessResource.class);

    private static final String ENTITY_NAME = "movieTicketProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MovieTicketProcessService movieTicketProcessService;

    public MovieTicketProcessResource(MovieTicketProcessService movieTicketProcessService) {
        this.movieTicketProcessService = movieTicketProcessService;
    }

    /**
     * {@code POST  /movie-ticket-processes} : Create a new movieTicketProcess.
     *
     * @param movieTicketProcessDTO the movieTicketProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movieTicketProcessDTO, or with status {@code 400 (Bad Request)} if the movieTicketProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movie-ticket-processes")
    public ResponseEntity<MovieTicketProcessDTO> create(@RequestBody MovieTicketProcessDTO movieTicketProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save MovieTicketProcess : {}", movieTicketProcessDTO);
        if (movieTicketProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new movieTicketProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovieTicketProcessDTO result = movieTicketProcessService.create(movieTicketProcessDTO);
        return ResponseEntity
            .created(new URI("/api/movie-ticket-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /movie-ticket-processes} : get all the movieTicketProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movieTicketProcesss in body.
     */
    @GetMapping("/movie-ticket-processes")
    public List<MovieTicketProcessDTO> getAllMovieTicketProcesss() {
        log.debug("REST request to get all MovieTicketProcesss");
        return movieTicketProcessService.findAll();
    }

    /**
     * {@code GET  /movie-ticket-processes/:id} : get the "id" movieTicketProcess.
     *
     * @param processInstanceId the id of the movieTicketProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movieTicketProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movie-ticket-processes/{processInstanceId}")
    public ResponseEntity<MovieTicketProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get MovieTicketProcess by processInstanceId : {}", processInstanceId);
        Optional<MovieTicketProcessDTO> movieTicketProcessDTO = movieTicketProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(movieTicketProcessDTO);
    }
}
