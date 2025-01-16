package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Billingserviceoptionvalues.
 */
@Entity
@Table(name = "billingserviceoptionvalues")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Billingserviceoptionvalues implements Serializable {

    // private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;

    @Id
    @Column(name = "vehicletypeid")
    private Long vehicletypeid;

    @Column(name = "billingserviceoptionid")
    private Long billingserviceoptionid;

    @Column(name = "value")
    private Float value;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private Integer lmu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    // public Long getId() {
    //     return this.id;
    // }

    // public Billingserviceoptionvalues id(Long id) {
    //     this.setId(id);
    //     return this;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public Long getVehicletypeid() {
        return this.vehicletypeid;
    }

    public Billingserviceoptionvalues vehicletypeid(Long vehicletypeid) {
        this.setVehicletypeid(vehicletypeid);
        return this;
    }

    public void setVehicletypeid(Long vehicletypeid) {
        this.vehicletypeid = vehicletypeid;
    }

    public Long getBillingserviceoptionid() {
        return this.billingserviceoptionid;
    }

    public Billingserviceoptionvalues billingserviceoptionid(Long billingserviceoptionid) {
        this.setBillingserviceoptionid(billingserviceoptionid);
        return this;
    }

    public void setBillingserviceoptionid(Long billingserviceoptionid) {
        this.billingserviceoptionid = billingserviceoptionid;
    }

    public Float getValue() {
        return this.value;
    }

    public Billingserviceoptionvalues value(Float value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Billingserviceoptionvalues lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Billingserviceoptionvalues lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) {
    //         return true;
    //     }
    //     if (!(o instanceof Billingserviceoptionvalues)) {
    //         return false;
    //     }
    //     return getId() != null && getId().equals(((Billingserviceoptionvalues) o).getId());
    // }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Billingserviceoptionvalues{" +
            // "id=" + getId() +
            ", vehicletypeid=" + getVehicletypeid() +
            ", billingserviceoptionid=" + getBillingserviceoptionid() +
            ", value=" + getValue() +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            "}";
    }
}
