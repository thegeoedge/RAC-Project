package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Vehicletype.
 */
@Entity
@Table(name = "vehicletype")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vehicletype implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicletype")
    private String vehicletype;

    @Column(name = "vehicletypediscription")
    private String vehicletypediscription;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "catid")
    private Integer catid;

    @Column(name = "catname")
    private String catname;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vehicletype id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicletype() {
        return this.vehicletype;
    }

    public Vehicletype vehicletype(String vehicletype) {
        this.setVehicletype(vehicletype);
        return this;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehicletypediscription() {
        return this.vehicletypediscription;
    }

    public Vehicletype vehicletypediscription(String vehicletypediscription) {
        this.setVehicletypediscription(vehicletypediscription);
        return this;
    }

    public void setVehicletypediscription(String vehicletypediscription) {
        this.vehicletypediscription = vehicletypediscription;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Vehicletype lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Vehicletype lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getCatid() {
        return this.catid;
    }

    public Vehicletype catid(Integer catid) {
        this.setCatid(catid);
        return this;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return this.catname;
    }

    public Vehicletype catname(String catname) {
        this.setCatname(catname);
        return this;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicletype)) {
            return false;
        }
        return getId() != null && getId().equals(((Vehicletype) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehicletype{" +
            "id=" + getId() +
            ", vehicletype='" + getVehicletype() + "'" +
            ", vehicletypediscription='" + getVehicletypediscription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", catid=" + getCatid() +
            ", catname='" + getCatname() + "'" +
            "}";
    }
}
