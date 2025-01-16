package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Bankbranch.
 */
@Entity
@Table(name = "bankbranch")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Bankbranch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bankcode")
    private String bankcode;

    @Column(name = "branchcode")
    private String branchcode;

    @Column(name = "branchname")
    private String branchname;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Bankbranch id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankcode() {
        return this.bankcode;
    }

    public Bankbranch bankcode(String bankcode) {
        this.setBankcode(bankcode);
        return this;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBranchcode() {
        return this.branchcode;
    }

    public Bankbranch branchcode(String branchcode) {
        this.setBranchcode(branchcode);
        return this;
    }

    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    public String getBranchname() {
        return this.branchname;
    }

    public Bankbranch branchname(String branchname) {
        this.setBranchname(branchname);
        return this;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bankbranch)) {
            return false;
        }
        return getId() != null && getId().equals(((Bankbranch) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bankbranch{" +
            "id=" + getId() +
            ", bankcode='" + getBankcode() + "'" +
            ", branchcode='" + getBranchcode() + "'" +
            ", branchname='" + getBranchname() + "'" +
            "}";
    }
}
