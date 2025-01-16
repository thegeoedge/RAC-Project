package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Workshopworklist.
 */
@Entity
@Table(name = "workshopworklist")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Workshopworklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "workshopwork")
    private String workshopwork;

    @Column(name = "workshopworkdescription")
    private String workshopworkdescription;

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

    public Workshopworklist id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkshopwork() {
        return this.workshopwork;
    }

    public Workshopworklist workshopwork(String workshopwork) {
        this.setWorkshopwork(workshopwork);
        return this;
    }

    public void setWorkshopwork(String workshopwork) {
        this.workshopwork = workshopwork;
    }

    public String getWorkshopworkdescription() {
        return this.workshopworkdescription;
    }

    public Workshopworklist workshopworkdescription(String workshopworkdescription) {
        this.setWorkshopworkdescription(workshopworkdescription);
        return this;
    }

    public void setWorkshopworkdescription(String workshopworkdescription) {
        this.workshopworkdescription = workshopworkdescription;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Workshopworklist isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Workshopworklist lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Workshopworklist lmu(Integer lmu) {
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
        if (!(o instanceof Workshopworklist)) {
            return false;
        }
        return getId() != null && getId().equals(((Workshopworklist) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Workshopworklist{" +
            "id=" + getId() +
            ", workshopwork='" + getWorkshopwork() + "'" +
            ", workshopworkdescription='" + getWorkshopworkdescription() + "'" +
            ", isactive='" + getIsactive() + "'" +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            "}";
    }
}
