package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Autocareappointmenttype.
 */
@Entity
@Table(name = "autocareappointmenttype")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocareappointmenttype implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "typename")
    private String typename;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocareappointmenttype id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypename() {
        return this.typename;
    }

    public Autocareappointmenttype typename(String typename) {
        this.setTypename(typename);
        return this;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocareappointmenttype)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocareappointmenttype) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocareappointmenttype{" +
            "id=" + getId() +
            ", typename='" + getTypename() + "'" +
            "}";
    }
}
