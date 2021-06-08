package com.cos482.ingressa.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.cos482.ingressa.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MovieTicketDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovieTicketDTO.class);
        MovieTicketDTO movieTicketDTO1 = new MovieTicketDTO();
        movieTicketDTO1.setId(1L);
        MovieTicketDTO movieTicketDTO2 = new MovieTicketDTO();
        assertThat(movieTicketDTO1).isNotEqualTo(movieTicketDTO2);
        movieTicketDTO2.setId(movieTicketDTO1.getId());
        assertThat(movieTicketDTO1).isEqualTo(movieTicketDTO2);
        movieTicketDTO2.setId(2L);
        assertThat(movieTicketDTO1).isNotEqualTo(movieTicketDTO2);
        movieTicketDTO1.setId(null);
        assertThat(movieTicketDTO1).isNotEqualTo(movieTicketDTO2);
    }
}
