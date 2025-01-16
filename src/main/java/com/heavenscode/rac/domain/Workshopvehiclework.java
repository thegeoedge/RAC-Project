package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Workshopvehiclework.
 */
@Entity
@Table(name = "workshopvehiclework")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Workshopvehiclework implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jobid")
    private Integer jobid;

    @Column(name = "vehicleid")
    private Integer vehicleid;

    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "customername")
    private String customername;

    @Column(name = "contactno")
    private String contactno;

    @Column(name = "vehicleno")
    private String vehicleno;

    @Column(name = "vehiclebrand")
    private String vehiclebrand;

    @Column(name = "vehiclemodel")
    private String vehiclemodel;

    @Column(name = "mileage")
    private String mileage;

    @Column(name = "addeddate")
    private Instant addeddate;

    @Column(name = "iscalltocustomer")
    private Boolean iscalltocustomer;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "calldate")
    private Instant calldate;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Workshopvehiclework id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public Workshopvehiclework jobid(Integer jobid) {
        this.setJobid(jobid);
        return this;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getVehicleid() {
        return this.vehicleid;
    }

    public Workshopvehiclework vehicleid(Integer vehicleid) {
        this.setVehicleid(vehicleid);
        return this;
    }

    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Workshopvehiclework customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Workshopvehiclework customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getContactno() {
        return this.contactno;
    }

    public Workshopvehiclework contactno(String contactno) {
        this.setContactno(contactno);
        return this;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getVehicleno() {
        return this.vehicleno;
    }

    public Workshopvehiclework vehicleno(String vehicleno) {
        this.setVehicleno(vehicleno);
        return this;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getVehiclebrand() {
        return this.vehiclebrand;
    }

    public Workshopvehiclework vehiclebrand(String vehiclebrand) {
        this.setVehiclebrand(vehiclebrand);
        return this;
    }

    public void setVehiclebrand(String vehiclebrand) {
        this.vehiclebrand = vehiclebrand;
    }

    public String getVehiclemodel() {
        return this.vehiclemodel;
    }

    public Workshopvehiclework vehiclemodel(String vehiclemodel) {
        this.setVehiclemodel(vehiclemodel);
        return this;
    }

    public void setVehiclemodel(String vehiclemodel) {
        this.vehiclemodel = vehiclemodel;
    }

    public String getMileage() {
        return this.mileage;
    }

    public Workshopvehiclework mileage(String mileage) {
        this.setMileage(mileage);
        return this;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public Instant getAddeddate() {
        return this.addeddate;
    }

    public Workshopvehiclework addeddate(Instant addeddate) {
        this.setAddeddate(addeddate);
        return this;
    }

    public void setAddeddate(Instant addeddate) {
        this.addeddate = addeddate;
    }

    public Boolean getIscalltocustomer() {
        return this.iscalltocustomer;
    }

    public Workshopvehiclework iscalltocustomer(Boolean iscalltocustomer) {
        this.setIscalltocustomer(iscalltocustomer);
        return this;
    }

    public void setIscalltocustomer(Boolean iscalltocustomer) {
        this.iscalltocustomer = iscalltocustomer;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Workshopvehiclework remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCalldate() {
        return this.calldate;
    }

    public Workshopvehiclework calldate(Instant calldate) {
        this.setCalldate(calldate);
        return this;
    }

    public void setCalldate(Instant calldate) {
        this.calldate = calldate;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Workshopvehiclework lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Workshopvehiclework lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workshopvehiclework)) {
            return false;
        }
        return getId() != null && getId().equals(((Workshopvehiclework) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Workshopvehiclework{" +
            "id=" + getId() +
            ", jobid=" + getJobid() +
            ", vehicleid=" + getVehicleid() +
            ", customerid=" + getCustomerid() +
            ", customername='" + getCustomername() + "'" +
            ", contactno='" + getContactno() + "'" +
            ", vehicleno='" + getVehicleno() + "'" +
            ", vehiclebrand='" + getVehiclebrand() + "'" +
            ", vehiclemodel='" + getVehiclemodel() + "'" +
            ", mileage='" + getMileage() + "'" +
            ", addeddate='" + getAddeddate() + "'" +
            ", iscalltocustomer='" + getIscalltocustomer() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", calldate='" + getCalldate() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
