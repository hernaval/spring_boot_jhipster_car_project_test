package com.hernaval.ctpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "publish_date")
    private Instant publishDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "roles" }, allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments" }, allowSetters = true)
    private Car car;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment id(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Comment message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getPublishDate() {
        return this.publishDate;
    }

    public Comment publishDate(Instant publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public void setPublishDate(Instant publishDate) {
        this.publishDate = publishDate;
    }

    public Client getClient() {
        return this.client;
    }

    public Comment client(Client client) {
        this.setClient(client);
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return this.car;
    }

    public Comment car(Car car) {
        this.setCar(car);
        return this;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        return id != null && id.equals(((Comment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", message='" + getMessage() + "'" +
            ", publishDate='" + getPublishDate() + "'" +
            "}";
    }
}
