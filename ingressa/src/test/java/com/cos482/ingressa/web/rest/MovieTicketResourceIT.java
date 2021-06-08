package com.cos482.ingressa.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cos482.ingressa.IntegrationTest;
import com.cos482.ingressa.domain.MovieTicket;
import com.cos482.ingressa.repository.MovieTicketRepository;
import com.cos482.ingressa.service.dto.MovieTicketDTO;
import com.cos482.ingressa.service.mapper.MovieTicketMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MovieTicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MovieTicketResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOVIE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SEAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SEAT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_CARD_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_CARD_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/movie-tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MovieTicketRepository movieTicketRepository;

    @Autowired
    private MovieTicketMapper movieTicketMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMovieTicketMockMvc;

    private MovieTicket movieTicket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovieTicket createEntity(EntityManager em) {
        MovieTicket movieTicket = new MovieTicket()
            .name(DEFAULT_NAME)
            .movieName(DEFAULT_MOVIE_NAME)
            .sessionDate(DEFAULT_SESSION_DATE)
            .seatNumber(DEFAULT_SEAT_NUMBER)
            .creditCardNumber(DEFAULT_CREDIT_CARD_NUMBER);
        return movieTicket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MovieTicket createUpdatedEntity(EntityManager em) {
        MovieTicket movieTicket = new MovieTicket()
            .name(UPDATED_NAME)
            .movieName(UPDATED_MOVIE_NAME)
            .sessionDate(UPDATED_SESSION_DATE)
            .seatNumber(UPDATED_SEAT_NUMBER)
            .creditCardNumber(UPDATED_CREDIT_CARD_NUMBER);
        return movieTicket;
    }

    @BeforeEach
    public void initTest() {
        movieTicket = createEntity(em);
    }

    @Test
    @Transactional
    void getAllMovieTickets() throws Exception {
        // Initialize the database
        movieTicketRepository.saveAndFlush(movieTicket);

        // Get all the movieTicketList
        restMovieTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(movieTicket.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].movieName").value(hasItem(DEFAULT_MOVIE_NAME)))
            .andExpect(jsonPath("$.[*].sessionDate").value(hasItem(DEFAULT_SESSION_DATE)))
            .andExpect(jsonPath("$.[*].seatNumber").value(hasItem(DEFAULT_SEAT_NUMBER)))
            .andExpect(jsonPath("$.[*].creditCardNumber").value(hasItem(DEFAULT_CREDIT_CARD_NUMBER)));
    }

    @Test
    @Transactional
    void getMovieTicket() throws Exception {
        // Initialize the database
        movieTicketRepository.saveAndFlush(movieTicket);

        // Get the movieTicket
        restMovieTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, movieTicket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(movieTicket.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.movieName").value(DEFAULT_MOVIE_NAME))
            .andExpect(jsonPath("$.sessionDate").value(DEFAULT_SESSION_DATE))
            .andExpect(jsonPath("$.seatNumber").value(DEFAULT_SEAT_NUMBER))
            .andExpect(jsonPath("$.creditCardNumber").value(DEFAULT_CREDIT_CARD_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingMovieTicket() throws Exception {
        // Get the movieTicket
        restMovieTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
