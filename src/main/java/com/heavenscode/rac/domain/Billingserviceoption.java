package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Billingserviceoption.
 */
@Entity
@Table(name = "billingserviceoption")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Billingserviceoption implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "servicename")
    private String servicename;

    @Column(name = "servicediscription")
    private String servicediscription;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "orderby")
    private Integer orderby;

    @Column(name = "billtocustomer")
    private Boolean billtocustomer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Billingserviceoption id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServicename() {
        return this.servicename;
    }

    public Billingserviceoption servicename(String servicename) {
        this.setServicename(servicename);
        return this;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicediscription() {
        return this.servicediscription;
    }

    public Billingserviceoption servicediscription(String servicediscription) {
        this.setServicediscription(servicediscription);
        return this;
    }

    public void setServicediscription(String servicediscription) {
        this.servicediscription = servicediscription;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Billingserviceoption isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Billingserviceoption lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Billingserviceoption lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Integer getOrderby() {
        return this.orderby;
    }

    public Billingserviceoption orderby(Integer orderby) {
        this.setOrderby(orderby);
        return this;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Boolean getBilltocustomer() {
        return this.billtocustomer;
    }

    public Billingserviceoption billtocustomer(Boolean billtocustomer) {
        this.setBilltocustomer(billtocustomer);
        return this;
    }

    public void setBilltocustomer(Boolean billtocustomer) {
        this.billtocustomer = billtocustomer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Billingserviceoption)) {
            return false;
        }
        return getId() != null && getId().equals(((Billingserviceoption) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Billingserviceoption{" +
            "id=" + getId() +
            ", servicename='" + getServicename() + "'" +
            ", servicediscription='" + getServicediscription() + "'" +
            ", isactive='" + getIsactive() + "'" +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            ", orderby=" + getOrderby() +
            ", billtocustomer='" + getBilltocustomer() + "'" +
            "}";
    }
}
