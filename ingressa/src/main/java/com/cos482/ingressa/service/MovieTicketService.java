package com.cos482.ingressa.service;

import com.cos482.ingressa.domain.MovieTicket;
import com.cos482.ingressa.repository.MovieTicketRepository;
import com.cos482.ingressa.service.dto.MovieTicketDTO;
import com.cos482.ingressa.service.mapper.MovieTicketMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MovieTicket}.
 */
@Service
@Transactional
public class MovieTicketService {

    private final Logger log = LoggerFactory.getLogger(MovieTicketService.class);

    private final MovieTicketRepository movieTicketRepository;

    private final MovieTicketMapper movieTicketMapper;

    public MovieTicketService(MovieTicketRepository movieTicketRepository, MovieTicketMapper movieTicketMapper) {
        this.movieTicketRepository = movieTicketRepository;
        this.movieTicketMapper = movieTicketMapper;
    }

    /**
     * Save a movieTicket.
     *
     * @param movieTicketDTO the entity to save.
     * @return the persisted entity.
     */
    public MovieTicketDTO save(MovieTicketDTO movieTicketDTO) {
        log.debug("Request to save MovieTicket : {}", movieTicketDTO);
        MovieTicket movieTicket = movieTicketMapper.toEntity(movieTicketDTO);
        movieTicket = movieTicketRepository.save(movieTicket);
        return movieTicketMapper.toDto(movieTicket);
    }

    /**
     * Partially update a movieTicket.
     *
     * @param movieTicketDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MovieTicketDTO> partialUpdate(MovieTicketDTO movieTicketDTO) {
        log.debug("Request to partially update MovieTicket : {}", movieTicketDTO);

        return movieTicketRepository
            .findById(movieTicketDTO.getId())
            .map(
                existingMovieTicket -> {
                    movieTicketMapper.partialUpdate(existingMovieTicket, movieTicketDTO);
                    return existingMovieTicket;
                }
            )
            .map(movieTicketRepository::save)
            .map(movieTicketMapper::toDto);
    }

    /**
     * Get all the movieTickets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MovieTicketDTO> findAll() {
        log.debug("Request to get all MovieTickets");
        return movieTicketRepository.findAll().stream().map(movieTicketMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one movieTicket by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MovieTicketDTO> findOne(Long id) {
        log.debug("Request to get MovieTicket : {}", id);
        return movieTicketRepository.findById(id).map(movieTicketMapper::toDto);
    }

    /**
     * Delete the movieTicket by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MovieTicket : {}", id);
        movieTicketRepository.deleteById(id);
    }
}
