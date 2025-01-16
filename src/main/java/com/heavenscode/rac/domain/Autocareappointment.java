package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autocareappointment.
 */
@Entity
@Table(name = "autocareappointment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocareappointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "appointmenttype")
    private Integer appointmenttype;

    @Column(name = "appointmentdate")
    private Instant appointmentdate;

    @Column(name = "addeddate")
    private Instant addeddate;

    @Column(name = "conformdate")
    private Instant conformdate;

    @Column(name = "appointmentnumber")
    private Integer appointmentnumber;

    @Column(name = "vehiclenumber")
    private String vehiclenumber;

    @Column(name = "appointmenttime")
    private Instant appointmenttime;

    @Column(name = "isconformed")
    private Boolean isconformed;

    @Column(name = "conformedby")
    private Integer conformedby;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "contactnumber")
    private String contactnumber;

    @Column(name = "customername")
    private String customername;

    @Column(name = "issued")
    private Boolean issued;

    @Column(name = "hoistid")
    private Integer hoistid;

    @Column(name = "isarrived")
    private Boolean isarrived;

    @Column(name = "iscancel")
    private Boolean iscancel;

    @Column(name = "isnoanswer")
    private Boolean isnoanswer;

    @Column(name = "missedappointmentcall")
    private String missedappointmentcall;

    @Column(name = "customermobileid")
    private Integer customermobileid;

    @Column(name = "customermobilevehicleid")
    private Integer customermobilevehicleid;

    @Column(name = "vehicleid")
    private Integer vehicleid;

    @Column(name = "ismobileappointment")
    private Boolean ismobileappointment;

    @Column(name = "advancepayment")
    private Float advancepayment;

    @Column(name = "jobid")
    private Integer jobid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocareappointment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAppointmenttype() {
        return this.appointmenttype;
    }

    public Autocareappointment appointmenttype(Integer appointmenttype) {
        this.setAppointmenttype(appointmenttype);
        return this;
    }

    public void setAppointmenttype(Integer appointmenttype) {
        this.appointmenttype = appointmenttype;
    }

    public Instant getAppointmentdate() {
        return this.appointmentdate;
    }

    public Autocareappointment appointmentdate(Instant appointmentdate) {
        this.setAppointmentdate(appointmentdate);
        return this;
    }

    public void setAppointmentdate(Instant appointmentdate) {
        this.appointmentdate = appointmentdate;
    }

    public Instant getAddeddate() {
        return this.addeddate;
    }

    public Autocareappointment addeddate(Instant addeddate) {
        this.setAddeddate(addeddate);
        return this;
    }

    public void setAddeddate(Instant addeddate) {
        this.addeddate = addeddate;
    }

    public Instant getConformdate() {
        return this.conformdate;
    }

    public Autocareappointment conformdate(Instant conformdate) {
        this.setConformdate(conformdate);
        return this;
    }

    public void setConformdate(Instant conformdate) {
        this.conformdate = conformdate;
    }

    public Integer getAppointmentnumber() {
        return this.appointmentnumber;
    }

    public Autocareappointment appointmentnumber(Integer appointmentnumber) {
        this.setAppointmentnumber(appointmentnumber);
        return this;
    }

    public void setAppointmentnumber(Integer appointmentnumber) {
        this.appointmentnumber = appointmentnumber;
    }

    public String getVehiclenumber() {
        return this.vehiclenumber;
    }

    public Autocareappointment vehiclenumber(String vehiclenumber) {
        this.setVehiclenumber(vehiclenumber);
        return this;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public Instant getAppointmenttime() {
        return this.appointmenttime;
    }

    public Autocareappointment appointmenttime(Instant appointmenttime) {
        this.setAppointmenttime(appointmenttime);
        return this;
    }

    public void setAppointmenttime(Instant appointmenttime) {
        this.appointmenttime = appointmenttime;
    }

    public Boolean getIsconformed() {
        return this.isconformed;
    }

    public Autocareappointment isconformed(Boolean isconformed) {
        this.setIsconformed(isconformed);
        return this;
    }

    public void setIsconformed(Boolean isconformed) {
        this.isconformed = isconformed;
    }

    public Integer getConformedby() {
        return this.conformedby;
    }

    public Autocareappointment conformedby(Integer conformedby) {
        this.setConformedby(conformedby);
        return this;
    }

    public void setConformedby(Integer conformedby) {
        this.conformedby = conformedby;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autocareappointment lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autocareappointment lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Autocareappointment customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getContactnumber() {
        return this.contactnumber;
    }

    public Autocareappointment contactnumber(String contactnumber) {
        this.setContactnumber(contactnumber);
        return this;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Autocareappointment customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Boolean getIssued() {
        return this.issued;
    }

    public Autocareappointment issued(Boolean issued) {
        this.setIssued(issued);
        return this;
    }

    public void setIssued(Boolean issued) {
        this.issued = issued;
    }

    public Integer getHoistid() {
        return this.hoistid;
    }

    public Autocareappointment hoistid(Integer hoistid) {
        this.setHoistid(hoistid);
        return this;
    }

    public void setHoistid(Integer hoistid) {
        this.hoistid = hoistid;
    }

    public Boolean getIsarrived() {
        return this.isarrived;
    }

    public Autocareappointment isarrived(Boolean isarrived) {
        this.setIsarrived(isarrived);
        return this;
    }

    public void setIsarrived(Boolean isarrived) {
        this.isarrived = isarrived;
    }

    public Boolean getIscancel() {
        return this.iscancel;
    }

    public Autocareappointment iscancel(Boolean iscancel) {
        this.setIscancel(iscancel);
        return this;
    }

    public void setIscancel(Boolean iscancel) {
        this.iscancel = iscancel;
    }

    public Boolean getIsnoanswer() {
        return this.isnoanswer;
    }

    public Autocareappointment isnoanswer(Boolean isnoanswer) {
        this.setIsnoanswer(isnoanswer);
        return this;
    }

    public void setIsnoanswer(Boolean isnoanswer) {
        this.isnoanswer = isnoanswer;
    }

    public String getMissedappointmentcall() {
        return this.missedappointmentcall;
    }

    public Autocareappointment missedappointmentcall(String missedappointmentcall) {
        this.setMissedappointmentcall(missedappointmentcall);
        return this;
    }

    public void setMissedappointmentcall(String missedappointmentcall) {
        this.missedappointmentcall = missedappointmentcall;
    }

    public Integer getCustomermobileid() {
        return this.customermobileid;
    }

    public Autocareappointment customermobileid(Integer customermobileid) {
        this.setCustomermobileid(customermobileid);
        return this;
    }

    public void setCustomermobileid(Integer customermobileid) {
        this.customermobileid = customermobileid;
    }

    public Integer getCustomermobilevehicleid() {
        return this.customermobilevehicleid;
    }

    public Autocareappointment customermobilevehicleid(Integer customermobilevehicleid) {
        this.setCustomermobilevehicleid(customermobilevehicleid);
        return this;
    }

    public void setCustomermobilevehicleid(Integer customermobilevehicleid) {
        this.customermobilevehicleid = customermobilevehicleid;
    }

    public Integer getVehicleid() {
        return this.vehicleid;
    }

    public Autocareappointment vehicleid(Integer vehicleid) {
        this.setVehicleid(vehicleid);
        return this;
    }

    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    public Boolean getIsmobileappointment() {
        return this.ismobileappointment;
    }

    public Autocareappointment ismobileappointment(Boolean ismobileappointment) {
        this.setIsmobileappointment(ismobileappointment);
        return this;
    }

    public void setIsmobileappointment(Boolean ismobileappointment) {
        this.ismobileappointment = ismobileappointment;
    }

    public Float getAdvancepayment() {
        return this.advancepayment;
    }

    public Autocareappointment advancepayment(Float advancepayment) {
        this.setAdvancepayment(advancepayment);
        return this;
    }

    public void setAdvancepayment(Float advancepayment) {
        this.advancepayment = advancepayment;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public Autocareappointment jobid(Integer jobid) {
        this.setJobid(jobid);
        return this;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocareappointment)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocareappointment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocareappointment{" +
            "id=" + getId() +
            ", appointmenttype=" + getAppointmenttype() +
            ", appointmentdate='" + getAppointmentdate() + "'" +
            ", addeddate='" + getAddeddate() + "'" +
            ", conformdate='" + getConformdate() + "'" +
            ", appointmentnumber=" + getAppointmentnumber() +
            ", vehiclenumber='" + getVehiclenumber() + "'" +
            ", appointmenttime='" + getAppointmenttime() + "'" +
            ", isconformed='" + getIsconformed() + "'" +
            ", conformedby=" + getConformedby() +
            ", lmd='" + getLmd() + "'" +
            ", lmu=" + getLmu() +
            ", customerid=" + getCustomerid() +
            ", contactnumber='" + getContactnumber() + "'" +
            ", customername='" + getCustomername() + "'" +
            ", issued='" + getIssued() + "'" +
            ", hoistid=" + getHoistid() +
            ", isarrived='" + getIsarrived() + "'" +
            ", iscancel='" + getIscancel() + "'" +
            ", isnoanswer='" + getIsnoanswer() + "'" +
            ", missedappointmentcall='" + getMissedappointmentcall() + "'" +
            ", customermobileid=" + getCustomermobileid() +
            ", customermobilevehicleid=" + getCustomermobilevehicleid() +
            ", vehicleid=" + getVehicleid() +
            ", ismobileappointment='" + getIsmobileappointment() + "'" +
            ", advancepayment=" + getAdvancepayment() +
            ", jobid=" + getJobid() +
            "}";
    }
}
