package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Lastserviceinstructions.
 */
@Entity
@Table(name = "lastserviceinstructions")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Lastserviceinstructions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jobid")
    private Integer jobid;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "ignore")
    private Boolean ignore;

    @Column(name = "vehicleid")
    private Integer vehicleid;

    @Column(name = "vehicleno")
    private String vehicleno;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Lastserviceinstructions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public Lastserviceinstructions jobid(Integer jobid) {
        this.setJobid(jobid);
        return this;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public Lastserviceinstructions instruction(String instruction) {
        this.setInstruction(instruction);
        return this;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Lastserviceinstructions isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Lastserviceinstructions lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Lastserviceinstructions lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Boolean getIgnore() {
        return this.ignore;
    }

    public Lastserviceinstructions ignore(Boolean ignore) {
        this.setIgnore(ignore);
        return this;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }

    public Integer getVehicleid() {
        return this.vehicleid;
    }

    public Lastserviceinstructions vehicleid(Integer vehicleid) {
        this.setVehicleid(vehicleid);
        return this;
    }

    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getVehicleno() {
        return this.vehicleno;
    }

    public Lastserviceinstructions vehicleno(String vehicleno) {
        this.setVehicleno(vehicleno);
        return this;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lastserviceinstructions)) {
            return false;
        }
        return getId() != null && getId().equals(((Lastserviceinstructions) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lastserviceinstructions{" +
            "id=" + getId() +
            ", jobid=" + getJobid() +
            ", instruction='" + getInstruction() + "'" +
            ", isactive='" + getIsactive() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", ignore='" + getIgnore() + "'" +
            ", vehicleid=" + getVehicleid() +
            ", vehicleno='" + getVehicleno() + "'" +
            "}";
    }
}
