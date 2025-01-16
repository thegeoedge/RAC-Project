package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Companybankaccount.
 */
@Entity
@Table(name = "companybankaccount")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Companybankaccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "companyid")
    private Integer companyid;

    @Column(name = "accountnumber")
    private String accountnumber;

    @Column(name = "accountname")
    private String accountname;

    @Column(name = "bankname")
    private String bankname;

    @Column(name = "bankid")
    private Integer bankid;

    @Column(name = "branchname")
    private String branchname;

    @Column(name = "branchid")
    private Integer branchid;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "accountid")
    private Integer accountid;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private String lmu;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "accounttypeid")
    private Integer accounttypeid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Companybankaccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompanyid() {
        return this.companyid;
    }

    public Companybankaccount companyid(Integer companyid) {
        this.setCompanyid(companyid);
        return this;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getAccountnumber() {
        return this.accountnumber;
    }

    public Companybankaccount accountnumber(String accountnumber) {
        this.setAccountnumber(accountnumber);
        return this;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAccountname() {
        return this.accountname;
    }

    public Companybankaccount accountname(String accountname) {
        this.setAccountname(accountname);
        return this;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getBankname() {
        return this.bankname;
    }

    public Companybankaccount bankname(String bankname) {
        this.setBankname(bankname);
        return this;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Integer getBankid() {
        return this.bankid;
    }

    public Companybankaccount bankid(Integer bankid) {
        this.setBankid(bankid);
        return this;
    }

    public void setBankid(Integer bankid) {
        this.bankid = bankid;
    }

    public String getBranchname() {
        return this.branchname;
    }

    public Companybankaccount branchname(String branchname) {
        this.setBranchname(branchname);
        return this;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public Integer getBranchid() {
        return this.branchid;
    }

    public Companybankaccount branchid(Integer branchid) {
        this.setBranchid(branchid);
        return this;
    }

    public void setBranchid(Integer branchid) {
        this.branchid = branchid;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Companybankaccount amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getAccountcode() {
        return this.accountcode;
    }

    public Companybankaccount accountcode(String accountcode) {
        this.setAccountcode(accountcode);
        return this;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public Integer getAccountid() {
        return this.accountid;
    }

    public Companybankaccount accountid(Integer accountid) {
        this.setAccountid(accountid);
        return this;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Companybankaccount lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public String getLmu() {
        return this.lmu;
    }

    public Companybankaccount lmu(String lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(String lmu) {
        this.lmu = lmu;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Companybankaccount isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Integer getAccounttypeid() {
        return this.accounttypeid;
    }

    public Companybankaccount accounttypeid(Integer accounttypeid) {
        this.setAccounttypeid(accounttypeid);
        return this;
    }

    public void setAccounttypeid(Integer accounttypeid) {
        this.accounttypeid = accounttypeid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Companybankaccount)) {
            return false;
        }
        return getId() != null && getId().equals(((Companybankaccount) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Companybankaccount{" +
            "id=" + getId() +
            ", companyid=" + getCompanyid() +
            ", accountnumber='" + getAccountnumber() + "'" +
            ", accountname='" + getAccountname() + "'" +
            ", bankname='" + getBankname() + "'" +
            ", bankid=" + getBankid() +
            ", branchname='" + getBranchname() + "'" +
            ", branchid=" + getBranchid() +
            ", amount=" + getAmount() +
            ", accountcode='" + getAccountcode() + "'" +
            ", accountid=" + getAccountid() +
            ", lmd='" + getLmd() + "'" +
            ", lmu='" + getLmu() + "'" +
            ", isactive='" + getIsactive() + "'" +
            ", accounttypeid=" + getAccounttypeid() +
            "}";
    }
}
