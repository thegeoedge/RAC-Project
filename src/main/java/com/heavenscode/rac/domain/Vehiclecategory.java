package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Vehiclecategory.
 */
@Entity
@Table(name = "vehiclecategory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vehiclecategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehiclecategory")
    private String vehiclecategory;

    @Column(name = "vehiclecategorydiscription")
    private String vehiclecategorydiscription;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vehiclecategory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehiclecategory() {
        return this.vehiclecategory;
    }

    public Vehiclecategory vehiclecategory(String vehiclecategory) {
        this.setVehiclecategory(vehiclecategory);
        return this;
    }

    public void setVehiclecategory(String vehiclecategory) {
        this.vehiclecategory = vehiclecategory;
    }

    public String getVehiclecategorydiscription() {
        return this.vehiclecategorydiscription;
    }

    public Vehiclecategory vehiclecategorydiscription(String vehiclecategorydiscription) {
        this.setVehiclecategorydiscription(vehiclecategorydiscription);
        return this;
    }

    public void setVehiclecategorydiscription(String vehiclecategorydiscription) {
        this.vehiclecategorydiscription = vehiclecategorydiscription;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Vehiclecategory lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Vehiclecategory lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehiclecategory)) {
            return false;
        }
        return getId() != null && getId().equals(((Vehiclecategory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehiclecategory{" +
            "id=" + getId() +
            ", vehiclecategory='" + getVehiclecategory() + "'" +
            ", vehiclecategorydiscription='" + getVehiclecategorydiscription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
