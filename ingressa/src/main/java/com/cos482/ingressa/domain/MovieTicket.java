package com.cos482.ingressa.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MovieTicket.
 */
@Entity
@Table(name = "movie_ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MovieTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "session_date")
    private String sessionDate;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieTicket id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public MovieTicket name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public MovieTicket movieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSessionDate() {
        return this.sessionDate;
    }

    public MovieTicket sessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
        return this;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public MovieTicket seatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public MovieTicket creditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieTicket)) {
            return false;
        }
        return id != null && id.equals(((MovieTicket) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieTicket{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", movieName='" + getMovieName() + "'" +
            ", sessionDate='" + getSessionDate() + "'" +
            ", seatNumber='" + getSeatNumber() + "'" +
            ", creditCardNumber='" + getCreditCardNumber() + "'" +
            "}";
    }
}
