package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Receipt.
 */
@Entity
@Table(name = "receipt")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "receiptdate")
    private Instant receiptdate;

    @Column(name = "customername")
    private String customername;

    @Column(name = "customeraddress")
    private String customeraddress;

    @Column(name = "totalamount")
    private Float totalamount;

    @Column(name = "totalamountinword")
    private String totalamountinword;

    @Column(name = "comments")
    private String comments;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "termid")
    private Integer termid;

    @Column(name = "term")
    private String term;

    @Column(name = "date")
    private Instant date;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "checkdate")
    private Instant checkdate;

    @Column(name = "checkno")
    private String checkno;

    @Column(name = "bank")
    private String bank;

    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "deposited")
    private Boolean deposited;

    @Column(name = "createdby")
    private Integer createdby;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Receipt id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Receipt code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getReceiptdate() {
        return this.receiptdate;
    }

    public Receipt receiptdate(Instant receiptdate) {
        this.setReceiptdate(receiptdate);
        return this;
    }

    public void setReceiptdate(Instant receiptdate) {
        this.receiptdate = receiptdate;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Receipt customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddress() {
        return this.customeraddress;
    }

    public Receipt customeraddress(String customeraddress) {
        this.setCustomeraddress(customeraddress);
        return this;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public Float getTotalamount() {
        return this.totalamount;
    }

    public Receipt totalamount(Float totalamount) {
        this.setTotalamount(totalamount);
        return this;
    }

    public void setTotalamount(Float totalamount) {
        this.totalamount = totalamount;
    }

    public String getTotalamountinword() {
        return this.totalamountinword;
    }

    public Receipt totalamountinword(String totalamountinword) {
        this.setTotalamountinword(totalamountinword);
        return this;
    }

    public void setTotalamountinword(String totalamountinword) {
        this.totalamountinword = totalamountinword;
    }

    public String getComments() {
        return this.comments;
    }

    public Receipt comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Receipt lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Receipt lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getTermid() {
        return this.termid;
    }

    public Receipt termid(Integer termid) {
        this.setTermid(termid);
        return this;
    }

    public void setTermid(Integer termid) {
        this.termid = termid;
    }

    public String getTerm() {
        return this.term;
    }

    public Receipt term(String term) {
        this.setTerm(term);
        return this;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Instant getDate() {
        return this.date;
    }

    public Receipt date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Receipt amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Instant getCheckdate() {
        return this.checkdate;
    }

    public Receipt checkdate(Instant checkdate) {
        this.setCheckdate(checkdate);
        return this;
    }

    public void setCheckdate(Instant checkdate) {
        this.checkdate = checkdate;
    }

    public String getCheckno() {
        return this.checkno;
    }

    public Receipt checkno(String checkno) {
        this.setCheckno(checkno);
        return this;
    }

    public void setCheckno(String checkno) {
        this.checkno = checkno;
    }

    public String getBank() {
        return this.bank;
    }

    public Receipt bank(String bank) {
        this.setBank(bank);
        return this;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Receipt customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Receipt isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Boolean getDeposited() {
        return this.deposited;
    }

    public Receipt deposited(Boolean deposited) {
        this.setDeposited(deposited);
        return this;
    }

    public void setDeposited(Boolean deposited) {
        this.deposited = deposited;
    }

    public Integer getCreatedby() {
        return this.createdby;
    }

    public Receipt createdby(Integer createdby) {
        this.setCreatedby(createdby);
        return this;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Receipt)) {
            return false;
        }
        return getId() != null && getId().equals(((Receipt) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Receipt{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", receiptdate='" + getReceiptdate() + "'" +
            ", customername='" + getCustomername() + "'" +
            ", customeraddress='" + getCustomeraddress() + "'" +
            ", totalamount=" + getTotalamount() +
            ", totalamountinword='" + getTotalamountinword() + "'" +
            ", comments='" + getComments() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", termid=" + getTermid() +
            ", term='" + getTerm() + "'" +
            ", date='" + getDate() + "'" +
            ", amount=" + getAmount() +
            ", checkdate='" + getCheckdate() + "'" +
            ", checkno='" + getCheckno() + "'" +
            ", bank='" + getBank() + "'" +
            ", customerid=" + getCustomerid() +
            ", isactive='" + getIsactive() + "'" +
            ", deposited='" + getDeposited() + "'" +
            ", createdby=" + getCreatedby() +
            "}";
    }
}
