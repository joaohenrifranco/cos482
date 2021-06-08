package com.cos482.ingressa.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cos482.ingressa.domain.MovieTicketProcess} entity.
 */
public class MovieTicketProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private MovieTicketDTO movieTicket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public MovieTicketDTO getMovieTicket() {
        return movieTicket;
    }

    public void setMovieTicket(MovieTicketDTO movieTicket) {
        this.movieTicket = movieTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieTicketProcessDTO)) {
            return false;
        }

        MovieTicketProcessDTO movieTicketProcessDTO = (MovieTicketProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, movieTicketProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieTicketProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", movieTicket=" + getMovieTicket() +
            "}";
    }
}
