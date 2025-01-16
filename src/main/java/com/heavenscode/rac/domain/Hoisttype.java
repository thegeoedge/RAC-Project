package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Hoisttype.
 */
@Entity
@Table(name = "hoisttype")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Hoisttype implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "hoisttype")
    private String hoisttype;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Hoisttype id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoisttype() {
        return this.hoisttype;
    }

    public Hoisttype hoisttype(String hoisttype) {
        this.setHoisttype(hoisttype);
        return this;
    }

    public void setHoisttype(String hoisttype) {
        this.hoisttype = hoisttype;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Hoisttype lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Hoisttype lmd(Instant lmd) {
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
        if (!(o instanceof Hoisttype)) {
            return false;
        }
        return getId() != null && getId().equals(((Hoisttype) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hoisttype{" +
            "id=" + getId() +
            ", hoisttype='" + getHoisttype() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
