package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Servicecategory.
 */
@Entity
@Table(name = "servicecategory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Servicecategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "showsecurity")
    private Boolean showsecurity;

    @Column(name = "sortorder")
    private Integer sortorder;

    @Column(name = "isactive")
    private Boolean isactive;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Servicecategory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Servicecategory name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Servicecategory description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Servicecategory lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Servicecategory lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Boolean getShowsecurity() {
        return this.showsecurity;
    }

    public Servicecategory showsecurity(Boolean showsecurity) {
        this.setShowsecurity(showsecurity);
        return this;
    }

    public void setShowsecurity(Boolean showsecurity) {
        this.showsecurity = showsecurity;
    }

    public Integer getSortorder() {
        return this.sortorder;
    }

    public Servicecategory sortorder(Integer sortorder) {
        this.setSortorder(sortorder);
        return this;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Servicecategory isactive(Boolean isactive) {
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
        if (!(o instanceof Servicecategory)) {
            return false;
        }
        return getId() != null && getId().equals(((Servicecategory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Servicecategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", showsecurity='" + getShowsecurity() + "'" +
            ", sortorder=" + getSortorder() +
            ", isactive='" + getIsactive() + "'" +
            "}";
    }
}
