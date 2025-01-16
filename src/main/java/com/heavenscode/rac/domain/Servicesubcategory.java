package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Servicesubcategory.
 */
@Entity
@Table(name = "servicesubcategory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Servicesubcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "mainid")
    private Integer mainid;

    @Column(name = "mainname")
    private String mainname;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "isactive")
    private Boolean isactive;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Servicesubcategory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Servicesubcategory name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Servicesubcategory description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMainid() {
        return this.mainid;
    }

    public Servicesubcategory mainid(Integer mainid) {
        this.setMainid(mainid);
        return this;
    }

    public void setMainid(Integer mainid) {
        this.mainid = mainid;
    }

    public String getMainname() {
        return this.mainname;
    }

    public Servicesubcategory mainname(String mainname) {
        this.setMainname(mainname);
        return this;
    }

    public void setMainname(String mainname) {
        this.mainname = mainname;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Servicesubcategory lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Servicesubcategory lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Servicesubcategory isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Servicesubcategory)) {
            return false;
        }
        return getId() != null && getId().equals(((Servicesubcategory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Servicesubcategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", mainid=" + getMainid() +
            ", mainname='" + getMainname() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", isactive='" + getIsactive() + "'" +
            "}";
    }
}
