package com.heavenscode.rac.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.heavenscode.rac.domain.Autocareappointment} entity. This class is used
 * in {@link com.heavenscode.rac.web.rest.AutocareappointmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /autocareappointments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AutocareappointmentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter appointmenttype;

    private InstantFilter appointmentdate;

    private InstantFilter addeddate;

    private InstantFilter conformdate;

    private IntegerFilter appointmentnumber;

    private StringFilter vehiclenumber;

    private InstantFilter appointmenttime;

    private BooleanFilter isconformed;

    private IntegerFilter conformedby;

    private InstantFilter lmd;

    private IntegerFilter lmu;

    private IntegerFilter customerid;

    private StringFilter contactnumber;

    private StringFilter customername;

    private BooleanFilter issued;

    private IntegerFilter hoistid;

    private BooleanFilter isarrived;

    private BooleanFilter iscancel;

    private BooleanFilter isnoanswer;

    private StringFilter missedappointmentcall;

    private IntegerFilter customermobileid;

    private IntegerFilter customermobilevehicleid;

    private IntegerFilter vehicleid;

    private BooleanFilter ismobileappointment;

    private FloatFilter advancepayment;

    private IntegerFilter jobid;

    private Boolean distinct;

    public AutocareappointmentCriteria() {}

    public AutocareappointmentCriteria(AutocareappointmentCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.appointmenttype = other.optionalAppointmenttype().map(IntegerFilter::copy).orElse(null);
        this.appointmentdate = other.optionalAppointmentdate().map(InstantFilter::copy).orElse(null);
        this.addeddate = other.optionalAddeddate().map(InstantFilter::copy).orElse(null);
        this.conformdate = other.optionalConformdate().map(InstantFilter::copy).orElse(null);
        this.appointmentnumber = other.optionalAppointmentnumber().map(IntegerFilter::copy).orElse(null);
        this.vehiclenumber = other.optionalVehiclenumber().map(StringFilter::copy).orElse(null);
        this.appointmenttime = other.optionalAppointmenttime().map(InstantFilter::copy).orElse(null);
        this.isconformed = other.optionalIsconformed().map(BooleanFilter::copy).orElse(null);
        this.conformedby = other.optionalConformedby().map(IntegerFilter::copy).orElse(null);
        this.lmd = other.optionalLmd().map(InstantFilter::copy).orElse(null);
        this.lmu = other.optionalLmu().map(IntegerFilter::copy).orElse(null);
        this.customerid = other.optionalCustomerid().map(IntegerFilter::copy).orElse(null);
        this.contactnumber = other.optionalContactnumber().map(StringFilter::copy).orElse(null);
        this.customername = other.optionalCustomername().map(StringFilter::copy).orElse(null);
        this.issued = other.optionalIssued().map(BooleanFilter::copy).orElse(null);
        this.hoistid = other.optionalHoistid().map(IntegerFilter::copy).orElse(null);
        this.isarrived = other.optionalIsarrived().map(BooleanFilter::copy).orElse(null);
        this.iscancel = other.optionalIscancel().map(BooleanFilter::copy).orElse(null);
        this.isnoanswer = other.optionalIsnoanswer().map(BooleanFilter::copy).orElse(null);
        this.missedappointmentcall = other.optionalMissedappointmentcall().map(StringFilter::copy).orElse(null);
        this.customermobileid = other.optionalCustomermobileid().map(IntegerFilter::copy).orElse(null);
        this.customermobilevehicleid = other.optionalCustomermobilevehicleid().map(IntegerFilter::copy).orElse(null);
        this.vehicleid = other.optionalVehicleid().map(IntegerFilter::copy).orElse(null);
        this.ismobileappointment = other.optionalIsmobileappointment().map(BooleanFilter::copy).orElse(null);
        this.advancepayment = other.optionalAdvancepayment().map(FloatFilter::copy).orElse(null);
        this.jobid = other.optionalJobid().map(IntegerFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public AutocareappointmentCriteria copy() {
        return new AutocareappointmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getAppointmenttype() {
        return appointmenttype;
    }

    public Optional<IntegerFilter> optionalAppointmenttype() {
        return Optional.ofNullable(appointmenttype);
    }

    public IntegerFilter appointmenttype() {
        if (appointmenttype == null) {
            setAppointmenttype(new IntegerFilter());
        }
        return appointmenttype;
    }

    public void setAppointmenttype(IntegerFilter appointmenttype) {
        this.appointmenttype = appointmenttype;
    }

    public InstantFilter getAppointmentdate() {
        return appointmentdate;
    }

    public Optional<InstantFilter> optionalAppointmentdate() {
        return Optional.ofNullable(appointmentdate);
    }

    public InstantFilter appointmentdate() {
        if (appointmentdate == null) {
            setAppointmentdate(new InstantFilter());
        }
        return appointmentdate;
    }

    public void setAppointmentdate(InstantFilter appointmentdate) {
        this.appointmentdate = appointmentdate;
    }

    public InstantFilter getAddeddate() {
        return addeddate;
    }

    public Optional<InstantFilter> optionalAddeddate() {
        return Optional.ofNullable(addeddate);
    }

    public InstantFilter addeddate() {
        if (addeddate == null) {
            setAddeddate(new InstantFilter());
        }
        return addeddate;
    }

    public void setAddeddate(InstantFilter addeddate) {
        this.addeddate = addeddate;
    }

    public InstantFilter getConformdate() {
        return conformdate;
    }

    public Optional<InstantFilter> optionalConformdate() {
        return Optional.ofNullable(conformdate);
    }

    public InstantFilter conformdate() {
        if (conformdate == null) {
            setConformdate(new InstantFilter());
        }
        return conformdate;
    }

    public void setConformdate(InstantFilter conformdate) {
        this.conformdate = conformdate;
    }

    public IntegerFilter getAppointmentnumber() {
        return appointmentnumber;
    }

    public Optional<IntegerFilter> optionalAppointmentnumber() {
        return Optional.ofNullable(appointmentnumber);
    }

    public IntegerFilter appointmentnumber() {
        if (appointmentnumber == null) {
            setAppointmentnumber(new IntegerFilter());
        }
        return appointmentnumber;
    }

    public void setAppointmentnumber(IntegerFilter appointmentnumber) {
        this.appointmentnumber = appointmentnumber;
    }

    public StringFilter getVehiclenumber() {
        return vehiclenumber;
    }

    public Optional<StringFilter> optionalVehiclenumber() {
        return Optional.ofNullable(vehiclenumber);
    }

    public StringFilter vehiclenumber() {
        if (vehiclenumber == null) {
            setVehiclenumber(new StringFilter());
        }
        return vehiclenumber;
    }

    public void setVehiclenumber(StringFilter vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public InstantFilter getAppointmenttime() {
        return appointmenttime;
    }

    public Optional<InstantFilter> optionalAppointmenttime() {
        return Optional.ofNullable(appointmenttime);
    }

    public InstantFilter appointmenttime() {
        if (appointmenttime == null) {
            setAppointmenttime(new InstantFilter());
        }
        return appointmenttime;
    }

    public void setAppointmenttime(InstantFilter appointmenttime) {
        this.appointmenttime = appointmenttime;
    }

    public BooleanFilter getIsconformed() {
        return isconformed;
    }

    public Optional<BooleanFilter> optionalIsconformed() {
        return Optional.ofNullable(isconformed);
    }

    public BooleanFilter isconformed() {
        if (isconformed == null) {
            setIsconformed(new BooleanFilter());
        }
        return isconformed;
    }

    public void setIsconformed(BooleanFilter isconformed) {
        this.isconformed = isconformed;
    }

    public IntegerFilter getConformedby() {
        return conformedby;
    }

    public Optional<IntegerFilter> optionalConformedby() {
        return Optional.ofNullable(conformedby);
    }

    public IntegerFilter conformedby() {
        if (conformedby == null) {
            setConformedby(new IntegerFilter());
        }
        return conformedby;
    }

    public void setConformedby(IntegerFilter conformedby) {
        this.conformedby = conformedby;
    }

    public InstantFilter getLmd() {
        return lmd;
    }

    public Optional<InstantFilter> optionalLmd() {
        return Optional.ofNullable(lmd);
    }

    public InstantFilter lmd() {
        if (lmd == null) {
            setLmd(new InstantFilter());
        }
        return lmd;
    }

    public void setLmd(InstantFilter lmd) {
        this.lmd = lmd;
    }

    public IntegerFilter getLmu() {
        return lmu;
    }

    public Optional<IntegerFilter> optionalLmu() {
        return Optional.ofNullable(lmu);
    }

    public IntegerFilter lmu() {
        if (lmu == null) {
            setLmu(new IntegerFilter());
        }
        return lmu;
    }

    public void setLmu(IntegerFilter lmu) {
        this.lmu = lmu;
    }

    public IntegerFilter getCustomerid() {
        return customerid;
    }

    public Optional<IntegerFilter> optionalCustomerid() {
        return Optional.ofNullable(customerid);
    }

    public IntegerFilter customerid() {
        if (customerid == null) {
            setCustomerid(new IntegerFilter());
        }
        return customerid;
    }

    public void setCustomerid(IntegerFilter customerid) {
        this.customerid = customerid;
    }

    public StringFilter getContactnumber() {
        return contactnumber;
    }

    public Optional<StringFilter> optionalContactnumber() {
        return Optional.ofNullable(contactnumber);
    }

    public StringFilter contactnumber() {
        if (contactnumber == null) {
            setContactnumber(new StringFilter());
        }
        return contactnumber;
    }

    public void setContactnumber(StringFilter contactnumber) {
        this.contactnumber = contactnumber;
    }

    public StringFilter getCustomername() {
        return customername;
    }

    public Optional<StringFilter> optionalCustomername() {
        return Optional.ofNullable(customername);
    }

    public StringFilter customername() {
        if (customername == null) {
            setCustomername(new StringFilter());
        }
        return customername;
    }

    public void setCustomername(StringFilter customername) {
        this.customername = customername;
    }

    public BooleanFilter getIssued() {
        return issued;
    }

    public Optional<BooleanFilter> optionalIssued() {
        return Optional.ofNullable(issued);
    }

    public BooleanFilter issued() {
        if (issued == null) {
            setIssued(new BooleanFilter());
        }
        return issued;
    }

    public void setIssued(BooleanFilter issued) {
        this.issued = issued;
    }

    public IntegerFilter getHoistid() {
        return hoistid;
    }

    public Optional<IntegerFilter> optionalHoistid() {
        return Optional.ofNullable(hoistid);
    }

    public IntegerFilter hoistid() {
        if (hoistid == null) {
            setHoistid(new IntegerFilter());
        }
        return hoistid;
    }

    public void setHoistid(IntegerFilter hoistid) {
        this.hoistid = hoistid;
    }

    public BooleanFilter getIsarrived() {
        return isarrived;
    }

    public Optional<BooleanFilter> optionalIsarrived() {
        return Optional.ofNullable(isarrived);
    }

    public BooleanFilter isarrived() {
        if (isarrived == null) {
            setIsarrived(new BooleanFilter());
        }
        return isarrived;
    }

    public void setIsarrived(BooleanFilter isarrived) {
        this.isarrived = isarrived;
    }

    public BooleanFilter getIscancel() {
        return iscancel;
    }

    public Optional<BooleanFilter> optionalIscancel() {
        return Optional.ofNullable(iscancel);
    }

    public BooleanFilter iscancel() {
        if (iscancel == null) {
            setIscancel(new BooleanFilter());
        }
        return iscancel;
    }

    public void setIscancel(BooleanFilter iscancel) {
        this.iscancel = iscancel;
    }

    public BooleanFilter getIsnoanswer() {
        return isnoanswer;
    }

    public Optional<BooleanFilter> optionalIsnoanswer() {
        return Optional.ofNullable(isnoanswer);
    }

    public BooleanFilter isnoanswer() {
        if (isnoanswer == null) {
            setIsnoanswer(new BooleanFilter());
        }
        return isnoanswer;
    }

    public void setIsnoanswer(BooleanFilter isnoanswer) {
        this.isnoanswer = isnoanswer;
    }

    public StringFilter getMissedappointmentcall() {
        return missedappointmentcall;
    }

    public Optional<StringFilter> optionalMissedappointmentcall() {
        return Optional.ofNullable(missedappointmentcall);
    }

    public StringFilter missedappointmentcall() {
        if (missedappointmentcall == null) {
            setMissedappointmentcall(new StringFilter());
        }
        return missedappointmentcall;
    }

    public void setMissedappointmentcall(StringFilter missedappointmentcall) {
        this.missedappointmentcall = missedappointmentcall;
    }

    public IntegerFilter getCustomermobileid() {
        return customermobileid;
    }

    public Optional<IntegerFilter> optionalCustomermobileid() {
        return Optional.ofNullable(customermobileid);
    }

    public IntegerFilter customermobileid() {
        if (customermobileid == null) {
            setCustomermobileid(new IntegerFilter());
        }
        return customermobileid;
    }

    public void setCustomermobileid(IntegerFilter customermobileid) {
        this.customermobileid = customermobileid;
    }

    public IntegerFilter getCustomermobilevehicleid() {
        return customermobilevehicleid;
    }

    public Optional<IntegerFilter> optionalCustomermobilevehicleid() {
        return Optional.ofNullable(customermobilevehicleid);
    }

    public IntegerFilter customermobilevehicleid() {
        if (customermobilevehicleid == null) {
            setCustomermobilevehicleid(new IntegerFilter());
        }
        return customermobilevehicleid;
    }

    public void setCustomermobilevehicleid(IntegerFilter customermobilevehicleid) {
        this.customermobilevehicleid = customermobilevehicleid;
    }

    public IntegerFilter getVehicleid() {
        return vehicleid;
    }

    public Optional<IntegerFilter> optionalVehicleid() {
        return Optional.ofNullable(vehicleid);
    }

    public IntegerFilter vehicleid() {
        if (vehicleid == null) {
            setVehicleid(new IntegerFilter());
        }
        return vehicleid;
    }

    public void setVehicleid(IntegerFilter vehicleid) {
        this.vehicleid = vehicleid;
    }

    public BooleanFilter getIsmobileappointment() {
        return ismobileappointment;
    }

    public Optional<BooleanFilter> optionalIsmobileappointment() {
        return Optional.ofNullable(ismobileappointment);
    }

    public BooleanFilter ismobileappointment() {
        if (ismobileappointment == null) {
            setIsmobileappointment(new BooleanFilter());
        }
        return ismobileappointment;
    }

    public void setIsmobileappointment(BooleanFilter ismobileappointment) {
        this.ismobileappointment = ismobileappointment;
    }

    public FloatFilter getAdvancepayment() {
        return advancepayment;
    }

    public Optional<FloatFilter> optionalAdvancepayment() {
        return Optional.ofNullable(advancepayment);
    }

    public FloatFilter advancepayment() {
        if (advancepayment == null) {
            setAdvancepayment(new FloatFilter());
        }
        return advancepayment;
    }

    public void setAdvancepayment(FloatFilter advancepayment) {
        this.advancepayment = advancepayment;
    }

    public IntegerFilter getJobid() {
        return jobid;
    }

    public Optional<IntegerFilter> optionalJobid() {
        return Optional.ofNullable(jobid);
    }

    public IntegerFilter jobid() {
        if (jobid == null) {
            setJobid(new IntegerFilter());
        }
        return jobid;
    }

    public void setJobid(IntegerFilter jobid) {
        this.jobid = jobid;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AutocareappointmentCriteria that = (AutocareappointmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(appointmenttype, that.appointmenttype) &&
            Objects.equals(appointmentdate, that.appointmentdate) &&
            Objects.equals(addeddate, that.addeddate) &&
            Objects.equals(conformdate, that.conformdate) &&
            Objects.equals(appointmentnumber, that.appointmentnumber) &&
            Objects.equals(vehiclenumber, that.vehiclenumber) &&
            Objects.equals(appointmenttime, that.appointmenttime) &&
            Objects.equals(isconformed, that.isconformed) &&
            Objects.equals(conformedby, that.conformedby) &&
            Objects.equals(lmd, that.lmd) &&
            Objects.equals(lmu, that.lmu) &&
            Objects.equals(customerid, that.customerid) &&
            Objects.equals(contactnumber, that.contactnumber) &&
            Objects.equals(customername, that.customername) &&
            Objects.equals(issued, that.issued) &&
            Objects.equals(hoistid, that.hoistid) &&
            Objects.equals(isarrived, that.isarrived) &&
            Objects.equals(iscancel, that.iscancel) &&
            Objects.equals(isnoanswer, that.isnoanswer) &&
            Objects.equals(missedappointmentcall, that.missedappointmentcall) &&
            Objects.equals(customermobileid, that.customermobileid) &&
            Objects.equals(customermobilevehicleid, that.customermobilevehicleid) &&
            Objects.equals(vehicleid, that.vehicleid) &&
            Objects.equals(ismobileappointment, that.ismobileappointment) &&
            Objects.equals(advancepayment, that.advancepayment) &&
            Objects.equals(jobid, that.jobid) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            appointmenttype,
            appointmentdate,
            addeddate,
            conformdate,
            appointmentnumber,
            vehiclenumber,
            appointmenttime,
            isconformed,
            conformedby,
            lmd,
            lmu,
            customerid,
            contactnumber,
            customername,
            issued,
            hoistid,
            isarrived,
            iscancel,
            isnoanswer,
            missedappointmentcall,
            customermobileid,
            customermobilevehicleid,
            vehicleid,
            ismobileappointment,
            advancepayment,
            jobid,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AutocareappointmentCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalAppointmenttype().map(f -> "appointmenttype=" + f + ", ").orElse("") +
            optionalAppointmentdate().map(f -> "appointmentdate=" + f + ", ").orElse("") +
            optionalAddeddate().map(f -> "addeddate=" + f + ", ").orElse("") +
            optionalConformdate().map(f -> "conformdate=" + f + ", ").orElse("") +
            optionalAppointmentnumber().map(f -> "appointmentnumber=" + f + ", ").orElse("") +
            optionalVehiclenumber().map(f -> "vehiclenumber=" + f + ", ").orElse("") +
            optionalAppointmenttime().map(f -> "appointmenttime=" + f + ", ").orElse("") +
            optionalIsconformed().map(f -> "isconformed=" + f + ", ").orElse("") +
            optionalConformedby().map(f -> "conformedby=" + f + ", ").orElse("") +
            optionalLmd().map(f -> "lmd=" + f + ", ").orElse("") +
            optionalLmu().map(f -> "lmu=" + f + ", ").orElse("") +
            optionalCustomerid().map(f -> "customerid=" + f + ", ").orElse("") +
            optionalContactnumber().map(f -> "contactnumber=" + f + ", ").orElse("") +
            optionalCustomername().map(f -> "customername=" + f + ", ").orElse("") +
            optionalIssued().map(f -> "issued=" + f + ", ").orElse("") +
            optionalHoistid().map(f -> "hoistid=" + f + ", ").orElse("") +
            optionalIsarrived().map(f -> "isarrived=" + f + ", ").orElse("") +
            optionalIscancel().map(f -> "iscancel=" + f + ", ").orElse("") +
            optionalIsnoanswer().map(f -> "isnoanswer=" + f + ", ").orElse("") +
            optionalMissedappointmentcall().map(f -> "missedappointmentcall=" + f + ", ").orElse("") +
            optionalCustomermobileid().map(f -> "customermobileid=" + f + ", ").orElse("") +
            optionalCustomermobilevehicleid().map(f -> "customermobilevehicleid=" + f + ", ").orElse("") +
            optionalVehicleid().map(f -> "vehicleid=" + f + ", ").orElse("") +
            optionalIsmobileappointment().map(f -> "ismobileappointment=" + f + ", ").orElse("") +
            optionalAdvancepayment().map(f -> "advancepayment=" + f + ", ").orElse("") +
            optionalJobid().map(f -> "jobid=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
