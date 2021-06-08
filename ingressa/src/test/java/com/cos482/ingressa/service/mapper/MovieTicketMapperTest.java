package com.cos482.ingressa.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieTicketMapperTest {

    private MovieTicketMapper movieTicketMapper;

    @BeforeEach
    public void setUp() {
        movieTicketMapper = new MovieTicketMapperImpl();
    }
}
