package com.cos482.ingressa.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cos482.ingressa.domain.MovieTicket} entity.
 */
public class MovieTicketDTO implements Serializable {

    private Long id;

    private String name;

    private String movieName;

    private String sessionDate;

    private String seatNumber;

    private String creditCardNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieTicketDTO)) {
            return false;
        }

        MovieTicketDTO movieTicketDTO = (MovieTicketDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, movieTicketDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieTicketDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", movieName='" + getMovieName() + "'" +
            ", sessionDate='" + getSessionDate() + "'" +
            ", seatNumber='" + getSeatNumber() + "'" +
            ", creditCardNumber='" + getCreditCardNumber() + "'" +
            "}";
    }
}
