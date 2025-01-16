package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Stocklocations.
 */
@Entity
@Table(name = "stocklocations")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Stocklocations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationid")
    private Long id;

    @Column(name = "locationname")
    private String locationname;

    @Column(name = "locationcode")
    private String locationcode;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Stocklocations id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationname() {
        return this.locationname;
    }

    public Stocklocations locationname(String locationname) {
        this.setLocationname(locationname);
        return this;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public String getLocationcode() {
        return this.locationcode;
    }

    public Stocklocations locationcode(String locationcode) {
        this.setLocationcode(locationcode);
        return this;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public String getDescription() {
        return this.description;
    }

    public Stocklocations description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stocklocations)) {
            return false;
        }
        return getId() != null && getId().equals(((Stocklocations) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stocklocations{" +
            "id=" + getId() +
            ", locationname='" + getLocationname() + "'" +
            ", locationcode='" + getLocationcode() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
