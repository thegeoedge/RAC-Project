package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autocarejobcategory.
 */
@Entity
@Table(name = "autocarejobcategory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocarejobcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "displayorder")
    private Integer displayorder;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocarejobcategory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Autocarejobcategory code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Autocarejobcategory name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Autocarejobcategory description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autocarejobcategory lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autocarejobcategory lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getDisplayorder() {
        return this.displayorder;
    }

    public Autocarejobcategory displayorder(Integer displayorder) {
        this.setDisplayorder(displayorder);
        return this;
    }

    public void setDisplayorder(Integer displayorder) {
        this.displayorder = displayorder;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocarejobcategory)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocarejobcategory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocarejobcategory{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", displayorder=" + getDisplayorder() +
            "}";
    }
}
