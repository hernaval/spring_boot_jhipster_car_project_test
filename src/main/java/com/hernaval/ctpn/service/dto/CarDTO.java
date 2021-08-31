package com.hernaval.ctpn.service.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.hernaval.ctpn.domain.Commenter;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.hernaval.ctpn.domain.Car} entity.
 * An object Filter {@link com.hernaval.ctpn.service.response_filter.CommentFilter} for Http Response
 */
@JsonFilter("commentFilterOnAnonymousClient")
public class CarDTO implements Serializable {

    private Long id;

    private String mark;

    private String description;

    private Set<Commenter> commenters = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Commenter> getCommenters() {
        return commenters;
    }

    public void setCommenters(Set<Commenter> commenters) {
        this.commenters = commenters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarDTO)) {
            return false;
        }

        CarDTO carDTO = (CarDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, carDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CarDTO{" +
            "id=" + getId() +
            ", mark='" + getMark() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
