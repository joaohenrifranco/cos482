package com.cos482.ingressa.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.cos482.ingressa.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MovieTicketTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MovieTicket.class);
        MovieTicket movieTicket1 = new MovieTicket();
        movieTicket1.setId(1L);
        MovieTicket movieTicket2 = new MovieTicket();
        movieTicket2.setId(movieTicket1.getId());
        assertThat(movieTicket1).isEqualTo(movieTicket2);
        movieTicket2.setId(2L);
        assertThat(movieTicket1).isNotEqualTo(movieTicket2);
        movieTicket1.setId(null);
        assertThat(movieTicket1).isNotEqualTo(movieTicket2);
    }
}
