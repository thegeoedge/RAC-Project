package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Paymentterm.
 */
@Entity
@Table(name = "paymentterm")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Paymentterm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "paymenttype")
    private String paymenttype;

    @Column(name = "forvoucher")
    private Boolean forvoucher;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Paymentterm id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymenttype() {
        return this.paymenttype;
    }

    public Paymentterm paymenttype(String paymenttype) {
        this.setPaymenttype(paymenttype);
        return this;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Boolean getForvoucher() {
        return this.forvoucher;
    }

    public Paymentterm forvoucher(Boolean forvoucher) {
        this.setForvoucher(forvoucher);
        return this;
    }

    public void setForvoucher(Boolean forvoucher) {
        this.forvoucher = forvoucher;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paymentterm)) {
            return false;
        }
        return getId() != null && getId().equals(((Paymentterm) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Paymentterm{" +
            "id=" + getId() +
            ", paymenttype='" + getPaymenttype() + "'" +
            ", forvoucher='" + getForvoucher() + "'" +
            "}";
    }
}
