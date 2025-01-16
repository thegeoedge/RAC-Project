package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Commonserviceoption.
 */
@Entity
@Table(name = "commonserviceoption")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Commonserviceoption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mainid")
    private Integer mainid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private Float value;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private Integer lmu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Commonserviceoption id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMainid() {
        return this.mainid;
    }

    public Commonserviceoption mainid(Integer mainid) {
        this.setMainid(mainid);
        return this;
    }

    public void setMainid(Integer mainid) {
        this.mainid = mainid;
    }

    public String getCode() {
        return this.code;
    }

    public Commonserviceoption code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Commonserviceoption name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Commonserviceoption description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getValue() {
        return this.value;
    }

    public Commonserviceoption value(Float value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Commonserviceoption isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Commonserviceoption lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Commonserviceoption lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commonserviceoption)) {
            return false;
        }
        return getId() != null && getId().equals(((Commonserviceoption) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commonserviceoption{" +
            "id=" + getId() +
            ", mainid=" + getMainid() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", value=" + getValue() +
            ", isactive='" + getIsactive() + "'" +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            "}";
    }
}
