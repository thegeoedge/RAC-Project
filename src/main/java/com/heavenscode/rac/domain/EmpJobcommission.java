package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A EmpJobcommission.
 */
@Entity
@Table(name = "emp_jobcommission")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmpJobcommission implements Serializable {

    // private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;

    @Id
    @NotNull
    @Column(name = "vehcatid", nullable = false)
    private Long vehcatid;

    @NotNull
    @Column(name = "autojobcatid", nullable = false)
    private Long autojobcatid;

    @Column(name = "firstcom")
    private Float firstcom;

    @Column(name = "secondcom")
    private Float secondcom;

    @Column(name = "thirdcom")
    private Float thirdcom;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private Integer lmu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    // public Long getId() {
    //     return this.id;
    // }

    // public EmpJobcommission id(Long id) {
    //     this.setId(id);
    //     return this;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public Long getVehcatid() {
        return this.vehcatid;
    }

    public EmpJobcommission vehcatid(Long vehcatid) {
        this.setVehcatid(vehcatid);
        return this;
    }

    public void setVehcatid(Long vehcatid) {
        this.vehcatid = vehcatid;
    }

    public Long getAutojobcatid() {
        return this.autojobcatid;
    }

    public EmpJobcommission autojobcatid(Long autojobcatid) {
        this.setAutojobcatid(autojobcatid);
        return this;
    }

    public void setAutojobcatid(Long autojobcatid) {
        this.autojobcatid = autojobcatid;
    }

    public Float getFirstcom() {
        return this.firstcom;
    }

    public EmpJobcommission firstcom(Float firstcom) {
        this.setFirstcom(firstcom);
        return this;
    }

    public void setFirstcom(Float firstcom) {
        this.firstcom = firstcom;
    }

    public Float getSecondcom() {
        return this.secondcom;
    }

    public EmpJobcommission secondcom(Float secondcom) {
        this.setSecondcom(secondcom);
        return this;
    }

    public void setSecondcom(Float secondcom) {
        this.secondcom = secondcom;
    }

    public Float getThirdcom() {
        return this.thirdcom;
    }

    public EmpJobcommission thirdcom(Float thirdcom) {
        this.setThirdcom(thirdcom);
        return this;
    }

    public void setThirdcom(Float thirdcom) {
        this.thirdcom = thirdcom;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public EmpJobcommission lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public EmpJobcommission lmu(Integer lmu) {
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
    //     if (!(o instanceof EmpJobcommission)) {
    //         return false;
    //     }
    //     return getId() != null && getId().equals(((EmpJobcommission) o).getId());
    // }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmpJobcommission{" +
            // "id=" + getId() +
            ", vehcatid=" + getVehcatid() +
            ", autojobcatid=" + getAutojobcatid() +
            ", firstcom=" + getFirstcom() +
            ", secondcom=" + getSecondcom() +
            ", thirdcom=" + getThirdcom() +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            "}";
    }
}
