package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Autocarecancelitemopt.
 */
@Entity
@Table(name = "autocarecancelitemopt")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocarecancelitemopt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "canceloption")
    private String canceloption;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocarecancelitemopt id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCanceloption() {
        return this.canceloption;
    }

    public Autocarecancelitemopt canceloption(String canceloption) {
        this.setCanceloption(canceloption);
        return this;
    }

    public void setCanceloption(String canceloption) {
        this.canceloption = canceloption;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocarecancelitemopt)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocarecancelitemopt) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocarecancelitemopt{" +
            "id=" + getId() +
            ", canceloption='" + getCanceloption() + "'" +
            "}";
    }
}
