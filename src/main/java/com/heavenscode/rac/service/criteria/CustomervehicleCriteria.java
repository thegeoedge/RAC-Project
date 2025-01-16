package com.heavenscode.rac.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.heavenscode.rac.domain.Customervehicle} entity. This class is used
 * in {@link com.heavenscode.rac.web.rest.CustomervehicleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /customervehicles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomervehicleCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter customerid;

    private StringFilter vehiclenumber;

    private IntegerFilter categoryid;

    private StringFilter categoryname;

    private IntegerFilter typeid;

    private StringFilter typename;

    private IntegerFilter makeid;

    private StringFilter makename;

    private StringFilter model;

    private StringFilter yom;

    private StringFilter customercode;

    private StringFilter remarks;

    private IntegerFilter servicecount;

    private StringFilter engNo;

    private StringFilter chaNo;

    private StringFilter milage;

    private LocalDateFilter lastservicedate;

    private LocalDateFilter nextservicedate;

    private IntegerFilter lmu;

    private InstantFilter lmd;

    private StringFilter nextgearoilmilage;

    private StringFilter nextmilage;

    private IntegerFilter serviceperiod;

    private Boolean distinct;

    public CustomervehicleCriteria() {}

    public CustomervehicleCriteria(CustomervehicleCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.customerid = other.optionalCustomerid().map(IntegerFilter::copy).orElse(null);
        this.vehiclenumber = other.optionalVehiclenumber().map(StringFilter::copy).orElse(null);
        this.categoryid = other.optionalCategoryid().map(IntegerFilter::copy).orElse(null);
        this.categoryname = other.optionalCategoryname().map(StringFilter::copy).orElse(null);
        this.typeid = other.optionalTypeid().map(IntegerFilter::copy).orElse(null);
        this.typename = other.optionalTypename().map(StringFilter::copy).orElse(null);
        this.makeid = other.optionalMakeid().map(IntegerFilter::copy).orElse(null);
        this.makename = other.optionalMakename().map(StringFilter::copy).orElse(null);
        this.model = other.optionalModel().map(StringFilter::copy).orElse(null);
        this.yom = other.optionalYom().map(StringFilter::copy).orElse(null);
        this.customercode = other.optionalCustomercode().map(StringFilter::copy).orElse(null);
        this.remarks = other.optionalRemarks().map(StringFilter::copy).orElse(null);
        this.servicecount = other.optionalServicecount().map(IntegerFilter::copy).orElse(null);
        this.engNo = other.optionalEngNo().map(StringFilter::copy).orElse(null);
        this.chaNo = other.optionalChaNo().map(StringFilter::copy).orElse(null);
        this.milage = other.optionalMilage().map(StringFilter::copy).orElse(null);
        this.lastservicedate = other.optionalLastservicedate().map(LocalDateFilter::copy).orElse(null);
        this.nextservicedate = other.optionalNextservicedate().map(LocalDateFilter::copy).orElse(null);
        this.lmu = other.optionalLmu().map(IntegerFilter::copy).orElse(null);
        this.lmd = other.optionalLmd().map(InstantFilter::copy).orElse(null);
        this.nextgearoilmilage = other.optionalNextgearoilmilage().map(StringFilter::copy).orElse(null);
        this.nextmilage = other.optionalNextmilage().map(StringFilter::copy).orElse(null);
        this.serviceperiod = other.optionalServiceperiod().map(IntegerFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CustomervehicleCriteria copy() {
        return new CustomervehicleCriteria(this);
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

    public IntegerFilter getCategoryid() {
        return categoryid;
    }

    public Optional<IntegerFilter> optionalCategoryid() {
        return Optional.ofNullable(categoryid);
    }

    public IntegerFilter categoryid() {
        if (categoryid == null) {
            setCategoryid(new IntegerFilter());
        }
        return categoryid;
    }

    public void setCategoryid(IntegerFilter categoryid) {
        this.categoryid = categoryid;
    }

    public StringFilter getCategoryname() {
        return categoryname;
    }

    public Optional<StringFilter> optionalCategoryname() {
        return Optional.ofNullable(categoryname);
    }

    public StringFilter categoryname() {
        if (categoryname == null) {
            setCategoryname(new StringFilter());
        }
        return categoryname;
    }

    public void setCategoryname(StringFilter categoryname) {
        this.categoryname = categoryname;
    }

    public IntegerFilter getTypeid() {
        return typeid;
    }

    public Optional<IntegerFilter> optionalTypeid() {
        return Optional.ofNullable(typeid);
    }

    public IntegerFilter typeid() {
        if (typeid == null) {
            setTypeid(new IntegerFilter());
        }
        return typeid;
    }

    public void setTypeid(IntegerFilter typeid) {
        this.typeid = typeid;
    }

    public StringFilter getTypename() {
        return typename;
    }

    public Optional<StringFilter> optionalTypename() {
        return Optional.ofNullable(typename);
    }

    public StringFilter typename() {
        if (typename == null) {
            setTypename(new StringFilter());
        }
        return typename;
    }

    public void setTypename(StringFilter typename) {
        this.typename = typename;
    }

    public IntegerFilter getMakeid() {
        return makeid;
    }

    public Optional<IntegerFilter> optionalMakeid() {
        return Optional.ofNullable(makeid);
    }

    public IntegerFilter makeid() {
        if (makeid == null) {
            setMakeid(new IntegerFilter());
        }
        return makeid;
    }

    public void setMakeid(IntegerFilter makeid) {
        this.makeid = makeid;
    }

    public StringFilter getMakename() {
        return makename;
    }

    public Optional<StringFilter> optionalMakename() {
        return Optional.ofNullable(makename);
    }

    public StringFilter makename() {
        if (makename == null) {
            setMakename(new StringFilter());
        }
        return makename;
    }

    public void setMakename(StringFilter makename) {
        this.makename = makename;
    }

    public StringFilter getModel() {
        return model;
    }

    public Optional<StringFilter> optionalModel() {
        return Optional.ofNullable(model);
    }

    public StringFilter model() {
        if (model == null) {
            setModel(new StringFilter());
        }
        return model;
    }

    public void setModel(StringFilter model) {
        this.model = model;
    }

    public StringFilter getYom() {
        return yom;
    }

    public Optional<StringFilter> optionalYom() {
        return Optional.ofNullable(yom);
    }

    public StringFilter yom() {
        if (yom == null) {
            setYom(new StringFilter());
        }
        return yom;
    }

    public void setYom(StringFilter yom) {
        this.yom = yom;
    }

    public StringFilter getCustomercode() {
        return customercode;
    }

    public Optional<StringFilter> optionalCustomercode() {
        return Optional.ofNullable(customercode);
    }

    public StringFilter customercode() {
        if (customercode == null) {
            setCustomercode(new StringFilter());
        }
        return customercode;
    }

    public void setCustomercode(StringFilter customercode) {
        this.customercode = customercode;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public Optional<StringFilter> optionalRemarks() {
        return Optional.ofNullable(remarks);
    }

    public StringFilter remarks() {
        if (remarks == null) {
            setRemarks(new StringFilter());
        }
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    public IntegerFilter getServicecount() {
        return servicecount;
    }

    public Optional<IntegerFilter> optionalServicecount() {
        return Optional.ofNullable(servicecount);
    }

    public IntegerFilter servicecount() {
        if (servicecount == null) {
            setServicecount(new IntegerFilter());
        }
        return servicecount;
    }

    public void setServicecount(IntegerFilter servicecount) {
        this.servicecount = servicecount;
    }

    public StringFilter getEngNo() {
        return engNo;
    }

    public Optional<StringFilter> optionalEngNo() {
        return Optional.ofNullable(engNo);
    }

    public StringFilter engNo() {
        if (engNo == null) {
            setEngNo(new StringFilter());
        }
        return engNo;
    }

    public void setEngNo(StringFilter engNo) {
        this.engNo = engNo;
    }

    public StringFilter getChaNo() {
        return chaNo;
    }

    public Optional<StringFilter> optionalChaNo() {
        return Optional.ofNullable(chaNo);
    }

    public StringFilter chaNo() {
        if (chaNo == null) {
            setChaNo(new StringFilter());
        }
        return chaNo;
    }

    public void setChaNo(StringFilter chaNo) {
        this.chaNo = chaNo;
    }

    public StringFilter getMilage() {
        return milage;
    }

    public Optional<StringFilter> optionalMilage() {
        return Optional.ofNullable(milage);
    }

    public StringFilter milage() {
        if (milage == null) {
            setMilage(new StringFilter());
        }
        return milage;
    }

    public void setMilage(StringFilter milage) {
        this.milage = milage;
    }

    public LocalDateFilter getLastservicedate() {
        return lastservicedate;
    }

    public Optional<LocalDateFilter> optionalLastservicedate() {
        return Optional.ofNullable(lastservicedate);
    }

    public LocalDateFilter lastservicedate() {
        if (lastservicedate == null) {
            setLastservicedate(new LocalDateFilter());
        }
        return lastservicedate;
    }

    public void setLastservicedate(LocalDateFilter lastservicedate) {
        this.lastservicedate = lastservicedate;
    }

    public LocalDateFilter getNextservicedate() {
        return nextservicedate;
    }

    public Optional<LocalDateFilter> optionalNextservicedate() {
        return Optional.ofNullable(nextservicedate);
    }

    public LocalDateFilter nextservicedate() {
        if (nextservicedate == null) {
            setNextservicedate(new LocalDateFilter());
        }
        return nextservicedate;
    }

    public void setNextservicedate(LocalDateFilter nextservicedate) {
        this.nextservicedate = nextservicedate;
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

    public StringFilter getNextgearoilmilage() {
        return nextgearoilmilage;
    }

    public Optional<StringFilter> optionalNextgearoilmilage() {
        return Optional.ofNullable(nextgearoilmilage);
    }

    public StringFilter nextgearoilmilage() {
        if (nextgearoilmilage == null) {
            setNextgearoilmilage(new StringFilter());
        }
        return nextgearoilmilage;
    }

    public void setNextgearoilmilage(StringFilter nextgearoilmilage) {
        this.nextgearoilmilage = nextgearoilmilage;
    }

    public StringFilter getNextmilage() {
        return nextmilage;
    }

    public Optional<StringFilter> optionalNextmilage() {
        return Optional.ofNullable(nextmilage);
    }

    public StringFilter nextmilage() {
        if (nextmilage == null) {
            setNextmilage(new StringFilter());
        }
        return nextmilage;
    }

    public void setNextmilage(StringFilter nextmilage) {
        this.nextmilage = nextmilage;
    }

    public IntegerFilter getServiceperiod() {
        return serviceperiod;
    }

    public Optional<IntegerFilter> optionalServiceperiod() {
        return Optional.ofNullable(serviceperiod);
    }

    public IntegerFilter serviceperiod() {
        if (serviceperiod == null) {
            setServiceperiod(new IntegerFilter());
        }
        return serviceperiod;
    }

    public void setServiceperiod(IntegerFilter serviceperiod) {
        this.serviceperiod = serviceperiod;
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
        final CustomervehicleCriteria that = (CustomervehicleCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(customerid, that.customerid) &&
            Objects.equals(vehiclenumber, that.vehiclenumber) &&
            Objects.equals(categoryid, that.categoryid) &&
            Objects.equals(categoryname, that.categoryname) &&
            Objects.equals(typeid, that.typeid) &&
            Objects.equals(typename, that.typename) &&
            Objects.equals(makeid, that.makeid) &&
            Objects.equals(makename, that.makename) &&
            Objects.equals(model, that.model) &&
            Objects.equals(yom, that.yom) &&
            Objects.equals(customercode, that.customercode) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(servicecount, that.servicecount) &&
            Objects.equals(engNo, that.engNo) &&
            Objects.equals(chaNo, that.chaNo) &&
            Objects.equals(milage, that.milage) &&
            Objects.equals(lastservicedate, that.lastservicedate) &&
            Objects.equals(nextservicedate, that.nextservicedate) &&
            Objects.equals(lmu, that.lmu) &&
            Objects.equals(lmd, that.lmd) &&
            Objects.equals(nextgearoilmilage, that.nextgearoilmilage) &&
            Objects.equals(nextmilage, that.nextmilage) &&
            Objects.equals(serviceperiod, that.serviceperiod) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            customerid,
            vehiclenumber,
            categoryid,
            categoryname,
            typeid,
            typename,
            makeid,
            makename,
            model,
            yom,
            customercode,
            remarks,
            servicecount,
            engNo,
            chaNo,
            milage,
            lastservicedate,
            nextservicedate,
            lmu,
            lmd,
            nextgearoilmilage,
            nextmilage,
            serviceperiod,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomervehicleCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalCustomerid().map(f -> "customerid=" + f + ", ").orElse("") +
            optionalVehiclenumber().map(f -> "vehiclenumber=" + f + ", ").orElse("") +
            optionalCategoryid().map(f -> "categoryid=" + f + ", ").orElse("") +
            optionalCategoryname().map(f -> "categoryname=" + f + ", ").orElse("") +
            optionalTypeid().map(f -> "typeid=" + f + ", ").orElse("") +
            optionalTypename().map(f -> "typename=" + f + ", ").orElse("") +
            optionalMakeid().map(f -> "makeid=" + f + ", ").orElse("") +
            optionalMakename().map(f -> "makename=" + f + ", ").orElse("") +
            optionalModel().map(f -> "model=" + f + ", ").orElse("") +
            optionalYom().map(f -> "yom=" + f + ", ").orElse("") +
            optionalCustomercode().map(f -> "customercode=" + f + ", ").orElse("") +
            optionalRemarks().map(f -> "remarks=" + f + ", ").orElse("") +
            optionalServicecount().map(f -> "servicecount=" + f + ", ").orElse("") +
            optionalEngNo().map(f -> "engNo=" + f + ", ").orElse("") +
            optionalChaNo().map(f -> "chaNo=" + f + ", ").orElse("") +
            optionalMilage().map(f -> "milage=" + f + ", ").orElse("") +
            optionalLastservicedate().map(f -> "lastservicedate=" + f + ", ").orElse("") +
            optionalNextservicedate().map(f -> "nextservicedate=" + f + ", ").orElse("") +
            optionalLmu().map(f -> "lmu=" + f + ", ").orElse("") +
            optionalLmd().map(f -> "lmd=" + f + ", ").orElse("") +
            optionalNextgearoilmilage().map(f -> "nextgearoilmilage=" + f + ", ").orElse("") +
            optionalNextmilage().map(f -> "nextmilage=" + f + ", ").orElse("") +
            optionalServiceperiod().map(f -> "serviceperiod=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
