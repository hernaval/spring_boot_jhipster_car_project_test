package com.hernaval.ctpn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties(value = { "client", "car" }, allowSetters = true)
    private Set<Commenter> commenters = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "rel_client__role", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnoreProperties(value = { "clients" }, allowSetters = true)
    private Set<Role> roles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client id(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public Client firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Client lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public Client email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public Client username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public Client password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Commenter> getCommenters() {
        return this.commenters;
    }

    public Client commenters(Set<Commenter> commenters) {
        this.setCommenters(commenters);
        return this;
    }

    public Client addCommenter(Commenter commenter) {
        this.commenters.add(commenter);
        commenter.setClient(this);
        return this;
    }

    public Client removeCommenter(Commenter commenter) {
        this.commenters.remove(commenter);
        commenter.setClient(null);
        return this;
    }

    public void setCommenters(Set<Commenter> commenters) {
        if (this.commenters != null) {
            this.commenters.forEach(i -> i.setClient(null));
        }
        if (commenters != null) {
            commenters.forEach(i -> i.setClient(this));
        }
        this.commenters = commenters;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public Client roles(Set<Role> roles) {
        this.setRoles(roles);
        return this;
    }

    public Client addRole(Role role) {
        this.roles.add(role);
        role.getClients().add(this);
        return this;
    }

    public Client removeRole(Role role) {
        this.roles.remove(role);
        role.getClients().remove(this);
        return this;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
}
