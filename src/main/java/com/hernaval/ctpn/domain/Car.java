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

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "client", "car" }, allowSetters = true)
    private Set<Commenter> commenters = new HashSet<>();

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

    public Set<Commenter> getCommenters() {
        return this.commenters;
    }

    public Car commenters(Set<Commenter> commenters) {
        this.setCommenters(commenters);
        return this;
    }

    public Car addCommenter(Commenter commenter) {
        this.commenters.add(commenter);
        commenter.setCar(this);
        return this;
    }

    public Car removeCommenter(Commenter commenter) {
        this.commenters.remove(commenter);
        commenter.setCar(null);
        return this;
    }

    public void setCommenters(Set<Commenter> commenters) {
        if (this.commenters != null) {
            this.commenters.forEach(i -> i.setCar(null));
        }
        if (commenters != null) {
            commenters.forEach(i -> i.setCar(this));
        }
        this.commenters = commenters;
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
