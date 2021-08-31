package com.hernaval.ctpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hernaval.ctpn.domain.enumeration.ERole;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Role.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties(value = { "commenters", "roles" }, allowSetters = true)
    private Set<Client> clients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role id(Long id) {
        this.id = id;
        return this;
    }

    public ERole getName() {
        return this.name;
    }

    public Role name(ERole name) {
        this.name = name;
        return this;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public Set<Client> getClients() {
        return this.clients;
    }

    public Role clients(Set<Client> clients) {
        this.setClients(clients);
        return this;
    }

    public Role addClient(Client client) {
        this.clients.add(client);
        client.getRoles().add(this);
        return this;
    }

    public Role removeClient(Client client) {
        this.clients.remove(client);
        client.getRoles().remove(this);
        return this;
    }

    public void setClients(Set<Client> clients) {
        if (this.clients != null) {
            this.clients.forEach(i -> i.removeRole(this));
        }
        if (clients != null) {
            clients.forEach(i -> i.addRole(this));
        }
        this.clients = clients;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return id != null && id.equals(((Role) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Role{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
