package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Accounts.
 */
@Entity
@Table(name = "accounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private Instant date;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private Integer type;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "hasbatches")
    private Boolean hasbatches;

    @Column(name = "accountvalue")
    private Float accountvalue;

    @Column(name = "accountlevel")
    private Integer accountlevel;

    @Column(name = "accountsnumberingsystem")
    private Long accountsnumberingsystem;

    @Column(name = "subparentid")
    private Integer subparentid;

    @Column(name = "canedit")
    private Boolean canedit;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "creditamount")
    private Float creditamount;

    @Column(name = "debitamount")
    private Float debitamount;

    @Column(name = "debitorcredit")
    private String debitorcredit;

    @Column(name = "reporttype")
    private Integer reporttype;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Accounts id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Accounts code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getDate() {
        return this.date;
    }

    public Accounts date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public Accounts name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Accounts description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return this.type;
    }

    public Accounts type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParent() {
        return this.parent;
    }

    public Accounts parent(Integer parent) {
        this.setParent(parent);
        return this;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Float getBalance() {
        return this.balance;
    }

    public Accounts balance(Float balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Accounts lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Accounts lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Boolean getHasbatches() {
        return this.hasbatches;
    }

    public Accounts hasbatches(Boolean hasbatches) {
        this.setHasbatches(hasbatches);
        return this;
    }

    public void setHasbatches(Boolean hasbatches) {
        this.hasbatches = hasbatches;
    }

    public Float getAccountvalue() {
        return this.accountvalue;
    }

    public Accounts accountvalue(Float accountvalue) {
        this.setAccountvalue(accountvalue);
        return this;
    }

    public void setAccountvalue(Float accountvalue) {
        this.accountvalue = accountvalue;
    }

    public Integer getAccountlevel() {
        return this.accountlevel;
    }

    public Accounts accountlevel(Integer accountlevel) {
        this.setAccountlevel(accountlevel);
        return this;
    }

    public void setAccountlevel(Integer accountlevel) {
        this.accountlevel = accountlevel;
    }

    public Long getAccountsnumberingsystem() {
        return this.accountsnumberingsystem;
    }

    public Accounts accountsnumberingsystem(Long accountsnumberingsystem) {
        this.setAccountsnumberingsystem(accountsnumberingsystem);
        return this;
    }

    public void setAccountsnumberingsystem(Long accountsnumberingsystem) {
        this.accountsnumberingsystem = accountsnumberingsystem;
    }

    public Integer getSubparentid() {
        return this.subparentid;
    }

    public Accounts subparentid(Integer subparentid) {
        this.setSubparentid(subparentid);
        return this;
    }

    public void setSubparentid(Integer subparentid) {
        this.subparentid = subparentid;
    }

    public Boolean getCanedit() {
        return this.canedit;
    }

    public Accounts canedit(Boolean canedit) {
        this.setCanedit(canedit);
        return this;
    }

    public void setCanedit(Boolean canedit) {
        this.canedit = canedit;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Accounts amount(Float amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getCreditamount() {
        return this.creditamount;
    }

    public Accounts creditamount(Float creditamount) {
        this.setCreditamount(creditamount);
        return this;
    }

    public void setCreditamount(Float creditamount) {
        this.creditamount = creditamount;
    }

    public Float getDebitamount() {
        return this.debitamount;
    }

    public Accounts debitamount(Float debitamount) {
        this.setDebitamount(debitamount);
        return this;
    }

    public void setDebitamount(Float debitamount) {
        this.debitamount = debitamount;
    }

    public String getDebitorcredit() {
        return this.debitorcredit;
    }

    public Accounts debitorcredit(String debitorcredit) {
        this.setDebitorcredit(debitorcredit);
        return this;
    }

    public void setDebitorcredit(String debitorcredit) {
        this.debitorcredit = debitorcredit;
    }

    public Integer getReporttype() {
        return this.reporttype;
    }

    public Accounts reporttype(Integer reporttype) {
        this.setReporttype(reporttype);
        return this;
    }

    public void setReporttype(Integer reporttype) {
        this.reporttype = reporttype;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accounts)) {
            return false;
        }
        return getId() != null && getId().equals(((Accounts) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Accounts{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", date='" + getDate() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type=" + getType() +
            ", parent=" + getParent() +
            ", balance=" + getBalance() +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", hasbatches='" + getHasbatches() + "'" +
            ", accountvalue=" + getAccountvalue() +
            ", accountlevel=" + getAccountlevel() +
            ", accountsnumberingsystem=" + getAccountsnumberingsystem() +
            ", subparentid=" + getSubparentid() +
            ", canedit='" + getCanedit() + "'" +
            ", amount=" + getAmount() +
            ", creditamount=" + getCreditamount() +
            ", debitamount=" + getDebitamount() +
            ", debitorcredit='" + getDebitorcredit() + "'" +
            ", reporttype=" + getReporttype() +
            "}";
    }
}
