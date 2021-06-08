package com.cos482.ingressa.service;

import com.cos482.ingressa.domain.MovieTicketProcess;
import com.cos482.ingressa.domain.ProcessInstance;
import com.cos482.ingressa.repository.MovieTicketProcessRepository;
import com.cos482.ingressa.repository.MovieTicketRepository;
import com.cos482.ingressa.service.dto.MovieTicketProcessDTO;
import com.cos482.ingressa.service.mapper.MovieTicketProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MovieTicketProcess}.
 */
@Service
@Transactional
public class MovieTicketProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "MovieTicketProcess";

    private final Logger log = LoggerFactory.getLogger(MovieTicketProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final MovieTicketRepository movieTicketRepository;

    private final MovieTicketProcessRepository movieTicketProcessRepository;

    private final MovieTicketProcessMapper movieTicketProcessMapper;

    public MovieTicketProcessService(
        ProcessInstanceService processInstanceService,
        MovieTicketRepository movieTicketRepository,
        MovieTicketProcessRepository movieTicketProcessRepository,
        MovieTicketProcessMapper movieTicketProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.movieTicketRepository = movieTicketRepository;
        this.movieTicketProcessRepository = movieTicketProcessRepository;
        this.movieTicketProcessMapper = movieTicketProcessMapper;
    }

    /**
     * Save a movieTicketProcess.
     *
     * @param movieTicketProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public MovieTicketProcessDTO create(MovieTicketProcessDTO movieTicketProcessDTO) {
        log.debug("Request to save MovieTicketProcess : {}", movieTicketProcessDTO);

        MovieTicketProcess movieTicketProcess = movieTicketProcessMapper.toEntity(movieTicketProcessDTO);

        //Saving the domainEntity
        movieTicketRepository.save(movieTicketProcess.getMovieTicket());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "MovieTicket#" + movieTicketProcess.getMovieTicket().getId(),
            movieTicketProcess
        );
        movieTicketProcess.setProcessInstance(processInstance);

        //Saving the process entity
        movieTicketProcess = movieTicketProcessRepository.save(movieTicketProcess);
        return movieTicketProcessMapper.toDto(movieTicketProcess);
    }

    /**
     * Get all the movieTicketProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MovieTicketProcessDTO> findAll() {
        log.debug("Request to get all MovieTicketProcesss");
        return movieTicketProcessRepository
            .findAll()
            .stream()
            .map(movieTicketProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one movieTicketProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MovieTicketProcessDTO> findOne(Long id) {
        log.debug("Request to get MovieTicketProcess : {}", id);
        return movieTicketProcessRepository.findById(id).map(movieTicketProcessMapper::toDto);
    }

    /**
     * Get one movieTicketProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MovieTicketProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get MovieTicketProcess by  processInstanceId: {}", processInstanceId);
        return movieTicketProcessRepository.findByProcessInstanceId(processInstanceId).map(movieTicketProcessMapper::toDto);
    }
}
