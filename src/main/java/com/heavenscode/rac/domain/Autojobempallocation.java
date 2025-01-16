package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autojobempallocation.
 */
@Entity
@Table(name = "autojobempallocation")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autojobempallocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jobid")
    private Integer jobid;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "empposition")
    private Integer empposition;

    @Column(name = "empid")
    private Integer empid;

    @Column(name = "empname")
    private String empname;

    @Column(name = "allocatetime")
    private Instant allocatetime;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "jobdate")
    private Instant jobdate;

    @Column(name = "commission")
    private Float commission;

    @Column(name = "iscommissionpaid")
    private Boolean iscommissionpaid;

    @Column(name = "starttime")
    private Instant starttime;

    @Column(name = "endtime")
    private Instant endtime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autojobempallocation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public Autojobempallocation jobid(Integer jobid) {
        this.setJobid(jobid);
        return this;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }

    public Autojobempallocation categoryid(Integer categoryid) {
        this.setCategoryid(categoryid);
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getEmpposition() {
        return this.empposition;
    }

    public Autojobempallocation empposition(Integer empposition) {
        this.setEmpposition(empposition);
        return this;
    }

    public void setEmpposition(Integer empposition) {
        this.empposition = empposition;
    }

    public Integer getEmpid() {
        return this.empid;
    }

    public Autojobempallocation empid(Integer empid) {
        this.setEmpid(empid);
        return this;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return this.empname;
    }

    public Autojobempallocation empname(String empname) {
        this.setEmpname(empname);
        return this;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Instant getAllocatetime() {
        return this.allocatetime;
    }

    public Autojobempallocation allocatetime(Instant allocatetime) {
        this.setAllocatetime(allocatetime);
        return this;
    }

    public void setAllocatetime(Instant allocatetime) {
        this.allocatetime = allocatetime;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autojobempallocation lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autojobempallocation lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Instant getJobdate() {
        return this.jobdate;
    }

    public Autojobempallocation jobdate(Instant jobdate) {
        this.setJobdate(jobdate);
        return this;
    }

    public void setJobdate(Instant jobdate) {
        this.jobdate = jobdate;
    }

    public Float getCommission() {
        return this.commission;
    }

    public Autojobempallocation commission(Float commission) {
        this.setCommission(commission);
        return this;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public Boolean getIscommissionpaid() {
        return this.iscommissionpaid;
    }

    public Autojobempallocation iscommissionpaid(Boolean iscommissionpaid) {
        this.setIscommissionpaid(iscommissionpaid);
        return this;
    }

    public void setIscommissionpaid(Boolean iscommissionpaid) {
        this.iscommissionpaid = iscommissionpaid;
    }

    public Instant getStarttime() {
        return this.starttime;
    }

    public Autojobempallocation starttime(Instant starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(Instant starttime) {
        this.starttime = starttime;
    }

    public Instant getEndtime() {
        return this.endtime;
    }

    public Autojobempallocation endtime(Instant endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(Instant endtime) {
        this.endtime = endtime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autojobempallocation)) {
            return false;
        }
        return getId() != null && getId().equals(((Autojobempallocation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autojobempallocation{" +
            "id=" + getId() +
            ", jobid=" + getJobid() +
            ", categoryid=" + getCategoryid() +
            ", empposition=" + getEmpposition() +
            ", empid=" + getEmpid() +
            ", empname='" + getEmpname() + "'" +
            ", allocatetime='" + getAllocatetime() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", jobdate='" + getJobdate() + "'" +
            ", commission=" + getCommission() +
            ", iscommissionpaid='" + getIscommissionpaid() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            "}";
    }
}
