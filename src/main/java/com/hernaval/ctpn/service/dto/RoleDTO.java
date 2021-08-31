package com.hernaval.ctpn.service.dto;

import com.hernaval.ctpn.domain.enumeration.ERole;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.hernaval.ctpn.domain.Role} entity.
 */
public class RoleDTO implements Serializable {

    private Long id;

    private ERole name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDTO)) {
            return false;
        }

        RoleDTO roleDTO = (RoleDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, roleDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
