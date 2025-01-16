package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Vehiclebrandname.
 */
@Entity
@Table(name = "vehiclebrandname")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vehiclebrandname implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brandname")
    private String brandname;

    @Column(name = "description")
    private String description;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "companyid")
    private Integer companyid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vehiclebrandname id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandname() {
        return this.brandname;
    }

    public Vehiclebrandname brandname(String brandname) {
        this.setBrandname(brandname);
        return this;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getDescription() {
        return this.description;
    }

    public Vehiclebrandname description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Vehiclebrandname lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Vehiclebrandname lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getCompanyid() {
        return this.companyid;
    }

    public Vehiclebrandname companyid(Integer companyid) {
        this.setCompanyid(companyid);
        return this;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehiclebrandname)) {
            return false;
        }
        return getId() != null && getId().equals(((Vehiclebrandname) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehiclebrandname{" +
            "id=" + getId() +
            ", brandname='" + getBrandname() + "'" +
            ", description='" + getDescription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", companyid=" + getCompanyid() +
            "}";
    }
}
