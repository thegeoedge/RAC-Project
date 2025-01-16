package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Autocareservicemillages.
 */
@Entity
@Table(name = "autocareservicemillages")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocareservicemillages implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "millage")
    private Integer millage;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocareservicemillages id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMillage() {
        return this.millage;
    }

    public Autocareservicemillages millage(Integer millage) {
        this.setMillage(millage);
        return this;
    }

    public void setMillage(Integer millage) {
        this.millage = millage;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocareservicemillages)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocareservicemillages) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocareservicemillages{" +
            "id=" + getId() +
            ", millage=" + getMillage() +
            "}";
    }
}
