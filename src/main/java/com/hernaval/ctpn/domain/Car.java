package com.hernaval.ctpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Car.
 */
@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "mark")
    private String mark;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "car")
    @JsonIgnoreProperties(value = { "client", "car" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car id(Long id) {
        this.id = id;
        return this;
    }

    public String getMark() {
        return this.mark;
    }

    public Car mark(String mark) {
        this.mark = mark;
        return this;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return this.description;
    }

    public Car description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public Car comments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public Car addComment(Comment comment) {
        this.comments.add(comment);
        comment.setCar(this);
        return this;
    }

    public Car removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setCar(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setCar(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setCar(this));
        }
        this.comments = comments;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        return id != null && id.equals(((Car) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Car{" +
            "id=" + getId() +
            ", mark='" + getMark() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
