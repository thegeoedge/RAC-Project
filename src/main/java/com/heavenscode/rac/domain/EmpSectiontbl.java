package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A EmpSectiontbl.
 */
@Entity
@Table(name = "emp_sectiontbl")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmpSectiontbl implements Serializable {

    // private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;

    @Id
    @Column(name = "empid")
    private Long empid;

    @Column(name = "sectionid")
    private Integer sectionid;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private Integer lmu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    // public Long getId() {
    //     return this.id;
    // }

    // public EmpSectiontbl id(Long id) {
    //     this.setId(id);
    //     return this;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public Long getEmpid() {
        return this.empid;
    }

    public EmpSectiontbl empid(Long empid) {
        this.setEmpid(empid);
        return this;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public Integer getSectionid() {
        return this.sectionid;
    }

    public EmpSectiontbl sectionid(Integer sectionid) {
        this.setSectionid(sectionid);
        return this;
    }

    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public EmpSectiontbl lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public EmpSectiontbl lmu(Integer lmu) {
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
    //     if (!(o instanceof EmpSectiontbl)) {
    //         return false;
    //     }
    //     return getId() != null && getId().equals(((EmpSectiontbl) o).getId());
    // }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmpSectiontbl{" +
            // "id=" + getId() +
            ", empid=" + getEmpid() +
            ", sectionid=" + getSectionid() +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            "}";
    }
}
