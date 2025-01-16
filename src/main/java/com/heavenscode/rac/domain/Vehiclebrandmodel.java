package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Vehiclebrandmodel.
 */
@Entity
@Table(name = "vehiclebrandmodel")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vehiclebrandmodel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brandid")
    private Integer brandid;

    @Column(name = "brandname")
    private String brandname;

    @Column(name = "model")
    private String model;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vehiclebrandmodel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrandid() {
        return this.brandid;
    }

    public Vehiclebrandmodel brandid(Integer brandid) {
        this.setBrandid(brandid);
        return this;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public String getBrandname() {
        return this.brandname;
    }

    public Vehiclebrandmodel brandname(String brandname) {
        this.setBrandname(brandname);
        return this;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getModel() {
        return this.model;
    }

    public Vehiclebrandmodel model(String model) {
        this.setModel(model);
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Vehiclebrandmodel lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Vehiclebrandmodel lmd(Instant lmd) {
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
        if (!(o instanceof Vehiclebrandmodel)) {
            return false;
        }
        return getId() != null && getId().equals(((Vehiclebrandmodel) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehiclebrandmodel{" +
            "id=" + getId() +
            ", brandid=" + getBrandid() +
            ", brandname='" + getBrandname() + "'" +
            ", model='" + getModel() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
