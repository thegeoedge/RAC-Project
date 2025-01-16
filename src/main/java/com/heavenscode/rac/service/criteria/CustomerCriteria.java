package com.heavenscode.rac.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.heavenscode.rac.domain.Customer} entity. This class is used
 * in {@link com.heavenscode.rac.web.rest.CustomerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /customers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter customertype;

    private StringFilter code;

    private StringFilter title;

    private StringFilter namewithinitials;

    private StringFilter fullname;

    private StringFilter callingname;

    private StringFilter nicno;

    private LocalDateFilter nicissueddate;

    private LocalDateFilter dateofbirth;

    private StringFilter bloodgroup;

    private StringFilter gender;

    private StringFilter maritalstatus;

    private LocalDateFilter marrieddate;

    private IntegerFilter nationality;

    private StringFilter territory;

    private IntegerFilter religion;

    private StringFilter team;

    private StringFilter businessname;

    private LocalDateFilter businessregdate;

    private StringFilter businessregno;

    private StringFilter profilepicturepath;

    private StringFilter residencehouseno;

    private StringFilter residenceaddress;

    private StringFilter residencecity;

    private StringFilter residencephone;

    private StringFilter businesslocationno;

    private StringFilter businessaddress;

    private StringFilter businesscity;

    private StringFilter businessphone1;

    private StringFilter businessphone2;

    private StringFilter businessmobile;

    private StringFilter businessfax;

    private StringFilter businessemail;

    private IntegerFilter businessprovinceid;

    private IntegerFilter businessdistrictid;

    private StringFilter contactpersonname;

    private StringFilter contactpersonphone;

    private StringFilter contactpersonmobile;

    private StringFilter contactpersonemail;

    private StringFilter rootmappath;

    private StringFilter website;

    private InstantFilter registrationdate;

    private IntegerFilter isactive;

    private IntegerFilter isinternalcustomer;

    private StringFilter description;

    private IntegerFilter lmu;

    private InstantFilter lmd;

    private FloatFilter maximumdiscount;

    private FloatFilter creditlimit;

    private BooleanFilter hassecuritydeposit;

    private FloatFilter securitydepositamount;

    private BooleanFilter paybycash;

    private BooleanFilter cashpaymentbeforeload;

    private BooleanFilter cashlastinvoicebeforeload;

    private BooleanFilter paybycredit;

    private BooleanFilter creditoneweekcheck;

    private IntegerFilter creditpaymentbydays;

    private BooleanFilter haspurchasingdeposit;

    private BooleanFilter hassecuritydepositbond;

    private BooleanFilter hasassestsdeposit;

    private StringFilter customerrootmappath;

    private StringFilter employername;

    private StringFilter employeraddress;

    private StringFilter employerphone;

    private StringFilter employerdesignation;

    private StringFilter previousemployername;

    private StringFilter previousemployeraddress;

    private StringFilter previousindustry;

    private StringFilter previousperiod;

    private StringFilter previouspositions;

    private StringFilter previousresionforleaving;

    private BooleanFilter hascreaditlimit;

    private IntegerFilter accountid;

    private StringFilter accountcode;

    private BooleanFilter isregistered;

    private StringFilter vatregnumber;

    private StringFilter tinnumber;

    private StringFilter lat;

    private StringFilter lon;

    private IntegerFilter creditperiod;

    private Boolean distinct;

    public CustomerCriteria() {}

    public CustomerCriteria(CustomerCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.customertype = other.optionalCustomertype().map(IntegerFilter::copy).orElse(null);
        this.code = other.optionalCode().map(StringFilter::copy).orElse(null);
        this.title = other.optionalTitle().map(StringFilter::copy).orElse(null);
        this.namewithinitials = other.optionalNamewithinitials().map(StringFilter::copy).orElse(null);
        this.fullname = other.optionalFullname().map(StringFilter::copy).orElse(null);
        this.callingname = other.optionalCallingname().map(StringFilter::copy).orElse(null);
        this.nicno = other.optionalNicno().map(StringFilter::copy).orElse(null);
        this.nicissueddate = other.optionalNicissueddate().map(LocalDateFilter::copy).orElse(null);
        this.dateofbirth = other.optionalDateofbirth().map(LocalDateFilter::copy).orElse(null);
        this.bloodgroup = other.optionalBloodgroup().map(StringFilter::copy).orElse(null);
        this.gender = other.optionalGender().map(StringFilter::copy).orElse(null);
        this.maritalstatus = other.optionalMaritalstatus().map(StringFilter::copy).orElse(null);
        this.marrieddate = other.optionalMarrieddate().map(LocalDateFilter::copy).orElse(null);
        this.nationality = other.optionalNationality().map(IntegerFilter::copy).orElse(null);
        this.territory = other.optionalTerritory().map(StringFilter::copy).orElse(null);
        this.religion = other.optionalReligion().map(IntegerFilter::copy).orElse(null);
        this.team = other.optionalTeam().map(StringFilter::copy).orElse(null);
        this.businessname = other.optionalBusinessname().map(StringFilter::copy).orElse(null);
        this.businessregdate = other.optionalBusinessregdate().map(LocalDateFilter::copy).orElse(null);
        this.businessregno = other.optionalBusinessregno().map(StringFilter::copy).orElse(null);
        this.profilepicturepath = other.optionalProfilepicturepath().map(StringFilter::copy).orElse(null);
        this.residencehouseno = other.optionalResidencehouseno().map(StringFilter::copy).orElse(null);
        this.residenceaddress = other.optionalResidenceaddress().map(StringFilter::copy).orElse(null);
        this.residencecity = other.optionalResidencecity().map(StringFilter::copy).orElse(null);
        this.residencephone = other.optionalResidencephone().map(StringFilter::copy).orElse(null);
        this.businesslocationno = other.optionalBusinesslocationno().map(StringFilter::copy).orElse(null);
        this.businessaddress = other.optionalBusinessaddress().map(StringFilter::copy).orElse(null);
        this.businesscity = other.optionalBusinesscity().map(StringFilter::copy).orElse(null);
        this.businessphone1 = other.optionalBusinessphone1().map(StringFilter::copy).orElse(null);
        this.businessphone2 = other.optionalBusinessphone2().map(StringFilter::copy).orElse(null);
        this.businessmobile = other.optionalBusinessmobile().map(StringFilter::copy).orElse(null);
        this.businessfax = other.optionalBusinessfax().map(StringFilter::copy).orElse(null);
        this.businessemail = other.optionalBusinessemail().map(StringFilter::copy).orElse(null);
        this.businessprovinceid = other.optionalBusinessprovinceid().map(IntegerFilter::copy).orElse(null);
        this.businessdistrictid = other.optionalBusinessdistrictid().map(IntegerFilter::copy).orElse(null);
        this.contactpersonname = other.optionalContactpersonname().map(StringFilter::copy).orElse(null);
        this.contactpersonphone = other.optionalContactpersonphone().map(StringFilter::copy).orElse(null);
        this.contactpersonmobile = other.optionalContactpersonmobile().map(StringFilter::copy).orElse(null);
        this.contactpersonemail = other.optionalContactpersonemail().map(StringFilter::copy).orElse(null);
        this.rootmappath = other.optionalRootmappath().map(StringFilter::copy).orElse(null);
        this.website = other.optionalWebsite().map(StringFilter::copy).orElse(null);
        this.registrationdate = other.optionalRegistrationdate().map(InstantFilter::copy).orElse(null);
        this.isactive = other.optionalIsactive().map(IntegerFilter::copy).orElse(null);
        this.isinternalcustomer = other.optionalIsinternalcustomer().map(IntegerFilter::copy).orElse(null);
        this.description = other.optionalDescription().map(StringFilter::copy).orElse(null);
        this.lmu = other.optionalLmu().map(IntegerFilter::copy).orElse(null);
        this.lmd = other.optionalLmd().map(InstantFilter::copy).orElse(null);
        this.maximumdiscount = other.optionalMaximumdiscount().map(FloatFilter::copy).orElse(null);
        this.creditlimit = other.optionalCreditlimit().map(FloatFilter::copy).orElse(null);
        this.hassecuritydeposit = other.optionalHassecuritydeposit().map(BooleanFilter::copy).orElse(null);
        this.securitydepositamount = other.optionalSecuritydepositamount().map(FloatFilter::copy).orElse(null);
        this.paybycash = other.optionalPaybycash().map(BooleanFilter::copy).orElse(null);
        this.cashpaymentbeforeload = other.optionalCashpaymentbeforeload().map(BooleanFilter::copy).orElse(null);
        this.cashlastinvoicebeforeload = other.optionalCashlastinvoicebeforeload().map(BooleanFilter::copy).orElse(null);
        this.paybycredit = other.optionalPaybycredit().map(BooleanFilter::copy).orElse(null);
        this.creditoneweekcheck = other.optionalCreditoneweekcheck().map(BooleanFilter::copy).orElse(null);
        this.creditpaymentbydays = other.optionalCreditpaymentbydays().map(IntegerFilter::copy).orElse(null);
        this.haspurchasingdeposit = other.optionalHaspurchasingdeposit().map(BooleanFilter::copy).orElse(null);
        this.hassecuritydepositbond = other.optionalHassecuritydepositbond().map(BooleanFilter::copy).orElse(null);
        this.hasassestsdeposit = other.optionalHasassestsdeposit().map(BooleanFilter::copy).orElse(null);
        this.customerrootmappath = other.optionalCustomerrootmappath().map(StringFilter::copy).orElse(null);
        this.employername = other.optionalEmployername().map(StringFilter::copy).orElse(null);
        this.employeraddress = other.optionalEmployeraddress().map(StringFilter::copy).orElse(null);
        this.employerphone = other.optionalEmployerphone().map(StringFilter::copy).orElse(null);
        this.employerdesignation = other.optionalEmployerdesignation().map(StringFilter::copy).orElse(null);
        this.previousemployername = other.optionalPreviousemployername().map(StringFilter::copy).orElse(null);
        this.previousemployeraddress = other.optionalPreviousemployeraddress().map(StringFilter::copy).orElse(null);
        this.previousindustry = other.optionalPreviousindustry().map(StringFilter::copy).orElse(null);
        this.previousperiod = other.optionalPreviousperiod().map(StringFilter::copy).orElse(null);
        this.previouspositions = other.optionalPreviouspositions().map(StringFilter::copy).orElse(null);
        this.previousresionforleaving = other.optionalPreviousresionforleaving().map(StringFilter::copy).orElse(null);
        this.hascreaditlimit = other.optionalHascreaditlimit().map(BooleanFilter::copy).orElse(null);
        this.accountid = other.optionalAccountid().map(IntegerFilter::copy).orElse(null);
        this.accountcode = other.optionalAccountcode().map(StringFilter::copy).orElse(null);
        this.isregistered = other.optionalIsregistered().map(BooleanFilter::copy).orElse(null);
        this.vatregnumber = other.optionalVatregnumber().map(StringFilter::copy).orElse(null);
        this.tinnumber = other.optionalTinnumber().map(StringFilter::copy).orElse(null);
        this.lat = other.optionalLat().map(StringFilter::copy).orElse(null);
        this.lon = other.optionalLon().map(StringFilter::copy).orElse(null);
        this.creditperiod = other.optionalCreditperiod().map(IntegerFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CustomerCriteria copy() {
        return new CustomerCriteria(this);
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

    public IntegerFilter getCustomertype() {
        return customertype;
    }

    public Optional<IntegerFilter> optionalCustomertype() {
        return Optional.ofNullable(customertype);
    }

    public IntegerFilter customertype() {
        if (customertype == null) {
            setCustomertype(new IntegerFilter());
        }
        return customertype;
    }

    public void setCustomertype(IntegerFilter customertype) {
        this.customertype = customertype;
    }

    public StringFilter getCode() {
        return code;
    }

    public Optional<StringFilter> optionalCode() {
        return Optional.ofNullable(code);
    }

    public StringFilter code() {
        if (code == null) {
            setCode(new StringFilter());
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getTitle() {
        return title;
    }

    public Optional<StringFilter> optionalTitle() {
        return Optional.ofNullable(title);
    }

    public StringFilter title() {
        if (title == null) {
            setTitle(new StringFilter());
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getNamewithinitials() {
        return namewithinitials;
    }

    public Optional<StringFilter> optionalNamewithinitials() {
        return Optional.ofNullable(namewithinitials);
    }

    public StringFilter namewithinitials() {
        if (namewithinitials == null) {
            setNamewithinitials(new StringFilter());
        }
        return namewithinitials;
    }

    public void setNamewithinitials(StringFilter namewithinitials) {
        this.namewithinitials = namewithinitials;
    }

    public StringFilter getFullname() {
        return fullname;
    }

    public Optional<StringFilter> optionalFullname() {
        return Optional.ofNullable(fullname);
    }

    public StringFilter fullname() {
        if (fullname == null) {
            setFullname(new StringFilter());
        }
        return fullname;
    }

    public void setFullname(StringFilter fullname) {
        this.fullname = fullname;
    }

    public StringFilter getCallingname() {
        return callingname;
    }

    public Optional<StringFilter> optionalCallingname() {
        return Optional.ofNullable(callingname);
    }

    public StringFilter callingname() {
        if (callingname == null) {
            setCallingname(new StringFilter());
        }
        return callingname;
    }

    public void setCallingname(StringFilter callingname) {
        this.callingname = callingname;
    }

    public StringFilter getNicno() {
        return nicno;
    }

    public Optional<StringFilter> optionalNicno() {
        return Optional.ofNullable(nicno);
    }

    public StringFilter nicno() {
        if (nicno == null) {
            setNicno(new StringFilter());
        }
        return nicno;
    }

    public void setNicno(StringFilter nicno) {
        this.nicno = nicno;
    }

    public LocalDateFilter getNicissueddate() {
        return nicissueddate;
    }

    public Optional<LocalDateFilter> optionalNicissueddate() {
        return Optional.ofNullable(nicissueddate);
    }

    public LocalDateFilter nicissueddate() {
        if (nicissueddate == null) {
            setNicissueddate(new LocalDateFilter());
        }
        return nicissueddate;
    }

    public void setNicissueddate(LocalDateFilter nicissueddate) {
        this.nicissueddate = nicissueddate;
    }

    public LocalDateFilter getDateofbirth() {
        return dateofbirth;
    }

    public Optional<LocalDateFilter> optionalDateofbirth() {
        return Optional.ofNullable(dateofbirth);
    }

    public LocalDateFilter dateofbirth() {
        if (dateofbirth == null) {
            setDateofbirth(new LocalDateFilter());
        }
        return dateofbirth;
    }

    public void setDateofbirth(LocalDateFilter dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public StringFilter getBloodgroup() {
        return bloodgroup;
    }

    public Optional<StringFilter> optionalBloodgroup() {
        return Optional.ofNullable(bloodgroup);
    }

    public StringFilter bloodgroup() {
        if (bloodgroup == null) {
            setBloodgroup(new StringFilter());
        }
        return bloodgroup;
    }

    public void setBloodgroup(StringFilter bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public StringFilter getGender() {
        return gender;
    }

    public Optional<StringFilter> optionalGender() {
        return Optional.ofNullable(gender);
    }

    public StringFilter gender() {
        if (gender == null) {
            setGender(new StringFilter());
        }
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    public StringFilter getMaritalstatus() {
        return maritalstatus;
    }

    public Optional<StringFilter> optionalMaritalstatus() {
        return Optional.ofNullable(maritalstatus);
    }

    public StringFilter maritalstatus() {
        if (maritalstatus == null) {
            setMaritalstatus(new StringFilter());
        }
        return maritalstatus;
    }

    public void setMaritalstatus(StringFilter maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public LocalDateFilter getMarrieddate() {
        return marrieddate;
    }

    public Optional<LocalDateFilter> optionalMarrieddate() {
        return Optional.ofNullable(marrieddate);
    }

    public LocalDateFilter marrieddate() {
        if (marrieddate == null) {
            setMarrieddate(new LocalDateFilter());
        }
        return marrieddate;
    }

    public void setMarrieddate(LocalDateFilter marrieddate) {
        this.marrieddate = marrieddate;
    }

    public IntegerFilter getNationality() {
        return nationality;
    }

    public Optional<IntegerFilter> optionalNationality() {
        return Optional.ofNullable(nationality);
    }

    public IntegerFilter nationality() {
        if (nationality == null) {
            setNationality(new IntegerFilter());
        }
        return nationality;
    }

    public void setNationality(IntegerFilter nationality) {
        this.nationality = nationality;
    }

    public StringFilter getTerritory() {
        return territory;
    }

    public Optional<StringFilter> optionalTerritory() {
        return Optional.ofNullable(territory);
    }

    public StringFilter territory() {
        if (territory == null) {
            setTerritory(new StringFilter());
        }
        return territory;
    }

    public void setTerritory(StringFilter territory) {
        this.territory = territory;
    }

    public IntegerFilter getReligion() {
        return religion;
    }

    public Optional<IntegerFilter> optionalReligion() {
        return Optional.ofNullable(religion);
    }

    public IntegerFilter religion() {
        if (religion == null) {
            setReligion(new IntegerFilter());
        }
        return religion;
    }

    public void setReligion(IntegerFilter religion) {
        this.religion = religion;
    }

    public StringFilter getTeam() {
        return team;
    }

    public Optional<StringFilter> optionalTeam() {
        return Optional.ofNullable(team);
    }

    public StringFilter team() {
        if (team == null) {
            setTeam(new StringFilter());
        }
        return team;
    }

    public void setTeam(StringFilter team) {
        this.team = team;
    }

    public StringFilter getBusinessname() {
        return businessname;
    }

    public Optional<StringFilter> optionalBusinessname() {
        return Optional.ofNullable(businessname);
    }

    public StringFilter businessname() {
        if (businessname == null) {
            setBusinessname(new StringFilter());
        }
        return businessname;
    }

    public void setBusinessname(StringFilter businessname) {
        this.businessname = businessname;
    }

    public LocalDateFilter getBusinessregdate() {
        return businessregdate;
    }

    public Optional<LocalDateFilter> optionalBusinessregdate() {
        return Optional.ofNullable(businessregdate);
    }

    public LocalDateFilter businessregdate() {
        if (businessregdate == null) {
            setBusinessregdate(new LocalDateFilter());
        }
        return businessregdate;
    }

    public void setBusinessregdate(LocalDateFilter businessregdate) {
        this.businessregdate = businessregdate;
    }

    public StringFilter getBusinessregno() {
        return businessregno;
    }

    public Optional<StringFilter> optionalBusinessregno() {
        return Optional.ofNullable(businessregno);
    }

    public StringFilter businessregno() {
        if (businessregno == null) {
            setBusinessregno(new StringFilter());
        }
        return businessregno;
    }

    public void setBusinessregno(StringFilter businessregno) {
        this.businessregno = businessregno;
    }

    public StringFilter getProfilepicturepath() {
        return profilepicturepath;
    }

    public Optional<StringFilter> optionalProfilepicturepath() {
        return Optional.ofNullable(profilepicturepath);
    }

    public StringFilter profilepicturepath() {
        if (profilepicturepath == null) {
            setProfilepicturepath(new StringFilter());
        }
        return profilepicturepath;
    }

    public void setProfilepicturepath(StringFilter profilepicturepath) {
        this.profilepicturepath = profilepicturepath;
    }

    public StringFilter getResidencehouseno() {
        return residencehouseno;
    }

    public Optional<StringFilter> optionalResidencehouseno() {
        return Optional.ofNullable(residencehouseno);
    }

    public StringFilter residencehouseno() {
        if (residencehouseno == null) {
            setResidencehouseno(new StringFilter());
        }
        return residencehouseno;
    }

    public void setResidencehouseno(StringFilter residencehouseno) {
        this.residencehouseno = residencehouseno;
    }

    public StringFilter getResidenceaddress() {
        return residenceaddress;
    }

    public Optional<StringFilter> optionalResidenceaddress() {
        return Optional.ofNullable(residenceaddress);
    }

    public StringFilter residenceaddress() {
        if (residenceaddress == null) {
            setResidenceaddress(new StringFilter());
        }
        return residenceaddress;
    }

    public void setResidenceaddress(StringFilter residenceaddress) {
        this.residenceaddress = residenceaddress;
    }

    public StringFilter getResidencecity() {
        return residencecity;
    }

    public Optional<StringFilter> optionalResidencecity() {
        return Optional.ofNullable(residencecity);
    }

    public StringFilter residencecity() {
        if (residencecity == null) {
            setResidencecity(new StringFilter());
        }
        return residencecity;
    }

    public void setResidencecity(StringFilter residencecity) {
        this.residencecity = residencecity;
    }

    public StringFilter getResidencephone() {
        return residencephone;
    }

    public Optional<StringFilter> optionalResidencephone() {
        return Optional.ofNullable(residencephone);
    }

    public StringFilter residencephone() {
        if (residencephone == null) {
            setResidencephone(new StringFilter());
        }
        return residencephone;
    }

    public void setResidencephone(StringFilter residencephone) {
        this.residencephone = residencephone;
    }

    public StringFilter getBusinesslocationno() {
        return businesslocationno;
    }

    public Optional<StringFilter> optionalBusinesslocationno() {
        return Optional.ofNullable(businesslocationno);
    }

    public StringFilter businesslocationno() {
        if (businesslocationno == null) {
            setBusinesslocationno(new StringFilter());
        }
        return businesslocationno;
    }

    public void setBusinesslocationno(StringFilter businesslocationno) {
        this.businesslocationno = businesslocationno;
    }

    public StringFilter getBusinessaddress() {
        return businessaddress;
    }

    public Optional<StringFilter> optionalBusinessaddress() {
        return Optional.ofNullable(businessaddress);
    }

    public StringFilter businessaddress() {
        if (businessaddress == null) {
            setBusinessaddress(new StringFilter());
        }
        return businessaddress;
    }

    public void setBusinessaddress(StringFilter businessaddress) {
        this.businessaddress = businessaddress;
    }

    public StringFilter getBusinesscity() {
        return businesscity;
    }

    public Optional<StringFilter> optionalBusinesscity() {
        return Optional.ofNullable(businesscity);
    }

    public StringFilter businesscity() {
        if (businesscity == null) {
            setBusinesscity(new StringFilter());
        }
        return businesscity;
    }

    public void setBusinesscity(StringFilter businesscity) {
        this.businesscity = businesscity;
    }

    public StringFilter getBusinessphone1() {
        return businessphone1;
    }

    public Optional<StringFilter> optionalBusinessphone1() {
        return Optional.ofNullable(businessphone1);
    }

    public StringFilter businessphone1() {
        if (businessphone1 == null) {
            setBusinessphone1(new StringFilter());
        }
        return businessphone1;
    }

    public void setBusinessphone1(StringFilter businessphone1) {
        this.businessphone1 = businessphone1;
    }

    public StringFilter getBusinessphone2() {
        return businessphone2;
    }

    public Optional<StringFilter> optionalBusinessphone2() {
        return Optional.ofNullable(businessphone2);
    }

    public StringFilter businessphone2() {
        if (businessphone2 == null) {
            setBusinessphone2(new StringFilter());
        }
        return businessphone2;
    }

    public void setBusinessphone2(StringFilter businessphone2) {
        this.businessphone2 = businessphone2;
    }

    public StringFilter getBusinessmobile() {
        return businessmobile;
    }

    public Optional<StringFilter> optionalBusinessmobile() {
        return Optional.ofNullable(businessmobile);
    }

    public StringFilter businessmobile() {
        if (businessmobile == null) {
            setBusinessmobile(new StringFilter());
        }
        return businessmobile;
    }

    public void setBusinessmobile(StringFilter businessmobile) {
        this.businessmobile = businessmobile;
    }

    public StringFilter getBusinessfax() {
        return businessfax;
    }

    public Optional<StringFilter> optionalBusinessfax() {
        return Optional.ofNullable(businessfax);
    }

    public StringFilter businessfax() {
        if (businessfax == null) {
            setBusinessfax(new StringFilter());
        }
        return businessfax;
    }

    public void setBusinessfax(StringFilter businessfax) {
        this.businessfax = businessfax;
    }

    public StringFilter getBusinessemail() {
        return businessemail;
    }

    public Optional<StringFilter> optionalBusinessemail() {
        return Optional.ofNullable(businessemail);
    }

    public StringFilter businessemail() {
        if (businessemail == null) {
            setBusinessemail(new StringFilter());
        }
        return businessemail;
    }

    public void setBusinessemail(StringFilter businessemail) {
        this.businessemail = businessemail;
    }

    public IntegerFilter getBusinessprovinceid() {
        return businessprovinceid;
    }

    public Optional<IntegerFilter> optionalBusinessprovinceid() {
        return Optional.ofNullable(businessprovinceid);
    }

    public IntegerFilter businessprovinceid() {
        if (businessprovinceid == null) {
            setBusinessprovinceid(new IntegerFilter());
        }
        return businessprovinceid;
    }

    public void setBusinessprovinceid(IntegerFilter businessprovinceid) {
        this.businessprovinceid = businessprovinceid;
    }

    public IntegerFilter getBusinessdistrictid() {
        return businessdistrictid;
    }

    public Optional<IntegerFilter> optionalBusinessdistrictid() {
        return Optional.ofNullable(businessdistrictid);
    }

    public IntegerFilter businessdistrictid() {
        if (businessdistrictid == null) {
            setBusinessdistrictid(new IntegerFilter());
        }
        return businessdistrictid;
    }

    public void setBusinessdistrictid(IntegerFilter businessdistrictid) {
        this.businessdistrictid = businessdistrictid;
    }

    public StringFilter getContactpersonname() {
        return contactpersonname;
    }

    public Optional<StringFilter> optionalContactpersonname() {
        return Optional.ofNullable(contactpersonname);
    }

    public StringFilter contactpersonname() {
        if (contactpersonname == null) {
            setContactpersonname(new StringFilter());
        }
        return contactpersonname;
    }

    public void setContactpersonname(StringFilter contactpersonname) {
        this.contactpersonname = contactpersonname;
    }

    public StringFilter getContactpersonphone() {
        return contactpersonphone;
    }

    public Optional<StringFilter> optionalContactpersonphone() {
        return Optional.ofNullable(contactpersonphone);
    }

    public StringFilter contactpersonphone() {
        if (contactpersonphone == null) {
            setContactpersonphone(new StringFilter());
        }
        return contactpersonphone;
    }

    public void setContactpersonphone(StringFilter contactpersonphone) {
        this.contactpersonphone = contactpersonphone;
    }

    public StringFilter getContactpersonmobile() {
        return contactpersonmobile;
    }

    public Optional<StringFilter> optionalContactpersonmobile() {
        return Optional.ofNullable(contactpersonmobile);
    }

    public StringFilter contactpersonmobile() {
        if (contactpersonmobile == null) {
            setContactpersonmobile(new StringFilter());
        }
        return contactpersonmobile;
    }

    public void setContactpersonmobile(StringFilter contactpersonmobile) {
        this.contactpersonmobile = contactpersonmobile;
    }

    public StringFilter getContactpersonemail() {
        return contactpersonemail;
    }

    public Optional<StringFilter> optionalContactpersonemail() {
        return Optional.ofNullable(contactpersonemail);
    }

    public StringFilter contactpersonemail() {
        if (contactpersonemail == null) {
            setContactpersonemail(new StringFilter());
        }
        return contactpersonemail;
    }

    public void setContactpersonemail(StringFilter contactpersonemail) {
        this.contactpersonemail = contactpersonemail;
    }

    public StringFilter getRootmappath() {
        return rootmappath;
    }

    public Optional<StringFilter> optionalRootmappath() {
        return Optional.ofNullable(rootmappath);
    }

    public StringFilter rootmappath() {
        if (rootmappath == null) {
            setRootmappath(new StringFilter());
        }
        return rootmappath;
    }

    public void setRootmappath(StringFilter rootmappath) {
        this.rootmappath = rootmappath;
    }

    public StringFilter getWebsite() {
        return website;
    }

    public Optional<StringFilter> optionalWebsite() {
        return Optional.ofNullable(website);
    }

    public StringFilter website() {
        if (website == null) {
            setWebsite(new StringFilter());
        }
        return website;
    }

    public void setWebsite(StringFilter website) {
        this.website = website;
    }

    public InstantFilter getRegistrationdate() {
        return registrationdate;
    }

    public Optional<InstantFilter> optionalRegistrationdate() {
        return Optional.ofNullable(registrationdate);
    }

    public InstantFilter registrationdate() {
        if (registrationdate == null) {
            setRegistrationdate(new InstantFilter());
        }
        return registrationdate;
    }

    public void setRegistrationdate(InstantFilter registrationdate) {
        this.registrationdate = registrationdate;
    }

    public IntegerFilter getIsactive() {
        return isactive;
    }

    public Optional<IntegerFilter> optionalIsactive() {
        return Optional.ofNullable(isactive);
    }

    public IntegerFilter isactive() {
        if (isactive == null) {
            setIsactive(new IntegerFilter());
        }
        return isactive;
    }

    public void setIsactive(IntegerFilter isactive) {
        this.isactive = isactive;
    }

    public IntegerFilter getIsinternalcustomer() {
        return isinternalcustomer;
    }

    public Optional<IntegerFilter> optionalIsinternalcustomer() {
        return Optional.ofNullable(isinternalcustomer);
    }

    public IntegerFilter isinternalcustomer() {
        if (isinternalcustomer == null) {
            setIsinternalcustomer(new IntegerFilter());
        }
        return isinternalcustomer;
    }

    public void setIsinternalcustomer(IntegerFilter isinternalcustomer) {
        this.isinternalcustomer = isinternalcustomer;
    }

    public StringFilter getDescription() {
        return description;
    }

    public Optional<StringFilter> optionalDescription() {
        return Optional.ofNullable(description);
    }

    public StringFilter description() {
        if (description == null) {
            setDescription(new StringFilter());
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    public FloatFilter getMaximumdiscount() {
        return maximumdiscount;
    }

    public Optional<FloatFilter> optionalMaximumdiscount() {
        return Optional.ofNullable(maximumdiscount);
    }

    public FloatFilter maximumdiscount() {
        if (maximumdiscount == null) {
            setMaximumdiscount(new FloatFilter());
        }
        return maximumdiscount;
    }

    public void setMaximumdiscount(FloatFilter maximumdiscount) {
        this.maximumdiscount = maximumdiscount;
    }

    public FloatFilter getCreditlimit() {
        return creditlimit;
    }

    public Optional<FloatFilter> optionalCreditlimit() {
        return Optional.ofNullable(creditlimit);
    }

    public FloatFilter creditlimit() {
        if (creditlimit == null) {
            setCreditlimit(new FloatFilter());
        }
        return creditlimit;
    }

    public void setCreditlimit(FloatFilter creditlimit) {
        this.creditlimit = creditlimit;
    }

    public BooleanFilter getHassecuritydeposit() {
        return hassecuritydeposit;
    }

    public Optional<BooleanFilter> optionalHassecuritydeposit() {
        return Optional.ofNullable(hassecuritydeposit);
    }

    public BooleanFilter hassecuritydeposit() {
        if (hassecuritydeposit == null) {
            setHassecuritydeposit(new BooleanFilter());
        }
        return hassecuritydeposit;
    }

    public void setHassecuritydeposit(BooleanFilter hassecuritydeposit) {
        this.hassecuritydeposit = hassecuritydeposit;
    }

    public FloatFilter getSecuritydepositamount() {
        return securitydepositamount;
    }

    public Optional<FloatFilter> optionalSecuritydepositamount() {
        return Optional.ofNullable(securitydepositamount);
    }

    public FloatFilter securitydepositamount() {
        if (securitydepositamount == null) {
            setSecuritydepositamount(new FloatFilter());
        }
        return securitydepositamount;
    }

    public void setSecuritydepositamount(FloatFilter securitydepositamount) {
        this.securitydepositamount = securitydepositamount;
    }

    public BooleanFilter getPaybycash() {
        return paybycash;
    }

    public Optional<BooleanFilter> optionalPaybycash() {
        return Optional.ofNullable(paybycash);
    }

    public BooleanFilter paybycash() {
        if (paybycash == null) {
            setPaybycash(new BooleanFilter());
        }
        return paybycash;
    }

    public void setPaybycash(BooleanFilter paybycash) {
        this.paybycash = paybycash;
    }

    public BooleanFilter getCashpaymentbeforeload() {
        return cashpaymentbeforeload;
    }

    public Optional<BooleanFilter> optionalCashpaymentbeforeload() {
        return Optional.ofNullable(cashpaymentbeforeload);
    }

    public BooleanFilter cashpaymentbeforeload() {
        if (cashpaymentbeforeload == null) {
            setCashpaymentbeforeload(new BooleanFilter());
        }
        return cashpaymentbeforeload;
    }

    public void setCashpaymentbeforeload(BooleanFilter cashpaymentbeforeload) {
        this.cashpaymentbeforeload = cashpaymentbeforeload;
    }

    public BooleanFilter getCashlastinvoicebeforeload() {
        return cashlastinvoicebeforeload;
    }

    public Optional<BooleanFilter> optionalCashlastinvoicebeforeload() {
        return Optional.ofNullable(cashlastinvoicebeforeload);
    }

    public BooleanFilter cashlastinvoicebeforeload() {
        if (cashlastinvoicebeforeload == null) {
            setCashlastinvoicebeforeload(new BooleanFilter());
        }
        return cashlastinvoicebeforeload;
    }

    public void setCashlastinvoicebeforeload(BooleanFilter cashlastinvoicebeforeload) {
        this.cashlastinvoicebeforeload = cashlastinvoicebeforeload;
    }

    public BooleanFilter getPaybycredit() {
        return paybycredit;
    }

    public Optional<BooleanFilter> optionalPaybycredit() {
        return Optional.ofNullable(paybycredit);
    }

    public BooleanFilter paybycredit() {
        if (paybycredit == null) {
            setPaybycredit(new BooleanFilter());
        }
        return paybycredit;
    }

    public void setPaybycredit(BooleanFilter paybycredit) {
        this.paybycredit = paybycredit;
    }

    public BooleanFilter getCreditoneweekcheck() {
        return creditoneweekcheck;
    }

    public Optional<BooleanFilter> optionalCreditoneweekcheck() {
        return Optional.ofNullable(creditoneweekcheck);
    }

    public BooleanFilter creditoneweekcheck() {
        if (creditoneweekcheck == null) {
            setCreditoneweekcheck(new BooleanFilter());
        }
        return creditoneweekcheck;
    }

    public void setCreditoneweekcheck(BooleanFilter creditoneweekcheck) {
        this.creditoneweekcheck = creditoneweekcheck;
    }

    public IntegerFilter getCreditpaymentbydays() {
        return creditpaymentbydays;
    }

    public Optional<IntegerFilter> optionalCreditpaymentbydays() {
        return Optional.ofNullable(creditpaymentbydays);
    }

    public IntegerFilter creditpaymentbydays() {
        if (creditpaymentbydays == null) {
            setCreditpaymentbydays(new IntegerFilter());
        }
        return creditpaymentbydays;
    }

    public void setCreditpaymentbydays(IntegerFilter creditpaymentbydays) {
        this.creditpaymentbydays = creditpaymentbydays;
    }

    public BooleanFilter getHaspurchasingdeposit() {
        return haspurchasingdeposit;
    }

    public Optional<BooleanFilter> optionalHaspurchasingdeposit() {
        return Optional.ofNullable(haspurchasingdeposit);
    }

    public BooleanFilter haspurchasingdeposit() {
        if (haspurchasingdeposit == null) {
            setHaspurchasingdeposit(new BooleanFilter());
        }
        return haspurchasingdeposit;
    }

    public void setHaspurchasingdeposit(BooleanFilter haspurchasingdeposit) {
        this.haspurchasingdeposit = haspurchasingdeposit;
    }

    public BooleanFilter getHassecuritydepositbond() {
        return hassecuritydepositbond;
    }

    public Optional<BooleanFilter> optionalHassecuritydepositbond() {
        return Optional.ofNullable(hassecuritydepositbond);
    }

    public BooleanFilter hassecuritydepositbond() {
        if (hassecuritydepositbond == null) {
            setHassecuritydepositbond(new BooleanFilter());
        }
        return hassecuritydepositbond;
    }

    public void setHassecuritydepositbond(BooleanFilter hassecuritydepositbond) {
        this.hassecuritydepositbond = hassecuritydepositbond;
    }

    public BooleanFilter getHasassestsdeposit() {
        return hasassestsdeposit;
    }

    public Optional<BooleanFilter> optionalHasassestsdeposit() {
        return Optional.ofNullable(hasassestsdeposit);
    }

    public BooleanFilter hasassestsdeposit() {
        if (hasassestsdeposit == null) {
            setHasassestsdeposit(new BooleanFilter());
        }
        return hasassestsdeposit;
    }

    public void setHasassestsdeposit(BooleanFilter hasassestsdeposit) {
        this.hasassestsdeposit = hasassestsdeposit;
    }

    public StringFilter getCustomerrootmappath() {
        return customerrootmappath;
    }

    public Optional<StringFilter> optionalCustomerrootmappath() {
        return Optional.ofNullable(customerrootmappath);
    }

    public StringFilter customerrootmappath() {
        if (customerrootmappath == null) {
            setCustomerrootmappath(new StringFilter());
        }
        return customerrootmappath;
    }

    public void setCustomerrootmappath(StringFilter customerrootmappath) {
        this.customerrootmappath = customerrootmappath;
    }

    public StringFilter getEmployername() {
        return employername;
    }

    public Optional<StringFilter> optionalEmployername() {
        return Optional.ofNullable(employername);
    }

    public StringFilter employername() {
        if (employername == null) {
            setEmployername(new StringFilter());
        }
        return employername;
    }

    public void setEmployername(StringFilter employername) {
        this.employername = employername;
    }

    public StringFilter getEmployeraddress() {
        return employeraddress;
    }

    public Optional<StringFilter> optionalEmployeraddress() {
        return Optional.ofNullable(employeraddress);
    }

    public StringFilter employeraddress() {
        if (employeraddress == null) {
            setEmployeraddress(new StringFilter());
        }
        return employeraddress;
    }

    public void setEmployeraddress(StringFilter employeraddress) {
        this.employeraddress = employeraddress;
    }

    public StringFilter getEmployerphone() {
        return employerphone;
    }

    public Optional<StringFilter> optionalEmployerphone() {
        return Optional.ofNullable(employerphone);
    }

    public StringFilter employerphone() {
        if (employerphone == null) {
            setEmployerphone(new StringFilter());
        }
        return employerphone;
    }

    public void setEmployerphone(StringFilter employerphone) {
        this.employerphone = employerphone;
    }

    public StringFilter getEmployerdesignation() {
        return employerdesignation;
    }

    public Optional<StringFilter> optionalEmployerdesignation() {
        return Optional.ofNullable(employerdesignation);
    }

    public StringFilter employerdesignation() {
        if (employerdesignation == null) {
            setEmployerdesignation(new StringFilter());
        }
        return employerdesignation;
    }

    public void setEmployerdesignation(StringFilter employerdesignation) {
        this.employerdesignation = employerdesignation;
    }

    public StringFilter getPreviousemployername() {
        return previousemployername;
    }

    public Optional<StringFilter> optionalPreviousemployername() {
        return Optional.ofNullable(previousemployername);
    }

    public StringFilter previousemployername() {
        if (previousemployername == null) {
            setPreviousemployername(new StringFilter());
        }
        return previousemployername;
    }

    public void setPreviousemployername(StringFilter previousemployername) {
        this.previousemployername = previousemployername;
    }

    public StringFilter getPreviousemployeraddress() {
        return previousemployeraddress;
    }

    public Optional<StringFilter> optionalPreviousemployeraddress() {
        return Optional.ofNullable(previousemployeraddress);
    }

    public StringFilter previousemployeraddress() {
        if (previousemployeraddress == null) {
            setPreviousemployeraddress(new StringFilter());
        }
        return previousemployeraddress;
    }

    public void setPreviousemployeraddress(StringFilter previousemployeraddress) {
        this.previousemployeraddress = previousemployeraddress;
    }

    public StringFilter getPreviousindustry() {
        return previousindustry;
    }

    public Optional<StringFilter> optionalPreviousindustry() {
        return Optional.ofNullable(previousindustry);
    }

    public StringFilter previousindustry() {
        if (previousindustry == null) {
            setPreviousindustry(new StringFilter());
        }
        return previousindustry;
    }

    public void setPreviousindustry(StringFilter previousindustry) {
        this.previousindustry = previousindustry;
    }

    public StringFilter getPreviousperiod() {
        return previousperiod;
    }

    public Optional<StringFilter> optionalPreviousperiod() {
        return Optional.ofNullable(previousperiod);
    }

    public StringFilter previousperiod() {
        if (previousperiod == null) {
            setPreviousperiod(new StringFilter());
        }
        return previousperiod;
    }

    public void setPreviousperiod(StringFilter previousperiod) {
        this.previousperiod = previousperiod;
    }

    public StringFilter getPreviouspositions() {
        return previouspositions;
    }

    public Optional<StringFilter> optionalPreviouspositions() {
        return Optional.ofNullable(previouspositions);
    }

    public StringFilter previouspositions() {
        if (previouspositions == null) {
            setPreviouspositions(new StringFilter());
        }
        return previouspositions;
    }

    public void setPreviouspositions(StringFilter previouspositions) {
        this.previouspositions = previouspositions;
    }

    public StringFilter getPreviousresionforleaving() {
        return previousresionforleaving;
    }

    public Optional<StringFilter> optionalPreviousresionforleaving() {
        return Optional.ofNullable(previousresionforleaving);
    }

    public StringFilter previousresionforleaving() {
        if (previousresionforleaving == null) {
            setPreviousresionforleaving(new StringFilter());
        }
        return previousresionforleaving;
    }

    public void setPreviousresionforleaving(StringFilter previousresionforleaving) {
        this.previousresionforleaving = previousresionforleaving;
    }

    public BooleanFilter getHascreaditlimit() {
        return hascreaditlimit;
    }

    public Optional<BooleanFilter> optionalHascreaditlimit() {
        return Optional.ofNullable(hascreaditlimit);
    }

    public BooleanFilter hascreaditlimit() {
        if (hascreaditlimit == null) {
            setHascreaditlimit(new BooleanFilter());
        }
        return hascreaditlimit;
    }

    public void setHascreaditlimit(BooleanFilter hascreaditlimit) {
        this.hascreaditlimit = hascreaditlimit;
    }

    public IntegerFilter getAccountid() {
        return accountid;
    }

    public Optional<IntegerFilter> optionalAccountid() {
        return Optional.ofNullable(accountid);
    }

    public IntegerFilter accountid() {
        if (accountid == null) {
            setAccountid(new IntegerFilter());
        }
        return accountid;
    }

    public void setAccountid(IntegerFilter accountid) {
        this.accountid = accountid;
    }

    public StringFilter getAccountcode() {
        return accountcode;
    }

    public Optional<StringFilter> optionalAccountcode() {
        return Optional.ofNullable(accountcode);
    }

    public StringFilter accountcode() {
        if (accountcode == null) {
            setAccountcode(new StringFilter());
        }
        return accountcode;
    }

    public void setAccountcode(StringFilter accountcode) {
        this.accountcode = accountcode;
    }

    public BooleanFilter getIsregistered() {
        return isregistered;
    }

    public Optional<BooleanFilter> optionalIsregistered() {
        return Optional.ofNullable(isregistered);
    }

    public BooleanFilter isregistered() {
        if (isregistered == null) {
            setIsregistered(new BooleanFilter());
        }
        return isregistered;
    }

    public void setIsregistered(BooleanFilter isregistered) {
        this.isregistered = isregistered;
    }

    public StringFilter getVatregnumber() {
        return vatregnumber;
    }

    public Optional<StringFilter> optionalVatregnumber() {
        return Optional.ofNullable(vatregnumber);
    }

    public StringFilter vatregnumber() {
        if (vatregnumber == null) {
            setVatregnumber(new StringFilter());
        }
        return vatregnumber;
    }

    public void setVatregnumber(StringFilter vatregnumber) {
        this.vatregnumber = vatregnumber;
    }

    public StringFilter getTinnumber() {
        return tinnumber;
    }

    public Optional<StringFilter> optionalTinnumber() {
        return Optional.ofNullable(tinnumber);
    }

    public StringFilter tinnumber() {
        if (tinnumber == null) {
            setTinnumber(new StringFilter());
        }
        return tinnumber;
    }

    public void setTinnumber(StringFilter tinnumber) {
        this.tinnumber = tinnumber;
    }

    public StringFilter getLat() {
        return lat;
    }

    public Optional<StringFilter> optionalLat() {
        return Optional.ofNullable(lat);
    }

    public StringFilter lat() {
        if (lat == null) {
            setLat(new StringFilter());
        }
        return lat;
    }

    public void setLat(StringFilter lat) {
        this.lat = lat;
    }

    public StringFilter getLon() {
        return lon;
    }

    public Optional<StringFilter> optionalLon() {
        return Optional.ofNullable(lon);
    }

    public StringFilter lon() {
        if (lon == null) {
            setLon(new StringFilter());
        }
        return lon;
    }

    public void setLon(StringFilter lon) {
        this.lon = lon;
    }

    public IntegerFilter getCreditperiod() {
        return creditperiod;
    }

    public Optional<IntegerFilter> optionalCreditperiod() {
        return Optional.ofNullable(creditperiod);
    }

    public IntegerFilter creditperiod() {
        if (creditperiod == null) {
            setCreditperiod(new IntegerFilter());
        }
        return creditperiod;
    }

    public void setCreditperiod(IntegerFilter creditperiod) {
        this.creditperiod = creditperiod;
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
        final CustomerCriteria that = (CustomerCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(customertype, that.customertype) &&
            Objects.equals(code, that.code) &&
            Objects.equals(title, that.title) &&
            Objects.equals(namewithinitials, that.namewithinitials) &&
            Objects.equals(fullname, that.fullname) &&
            Objects.equals(callingname, that.callingname) &&
            Objects.equals(nicno, that.nicno) &&
            Objects.equals(nicissueddate, that.nicissueddate) &&
            Objects.equals(dateofbirth, that.dateofbirth) &&
            Objects.equals(bloodgroup, that.bloodgroup) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(maritalstatus, that.maritalstatus) &&
            Objects.equals(marrieddate, that.marrieddate) &&
            Objects.equals(nationality, that.nationality) &&
            Objects.equals(territory, that.territory) &&
            Objects.equals(religion, that.religion) &&
            Objects.equals(team, that.team) &&
            Objects.equals(businessname, that.businessname) &&
            Objects.equals(businessregdate, that.businessregdate) &&
            Objects.equals(businessregno, that.businessregno) &&
            Objects.equals(profilepicturepath, that.profilepicturepath) &&
            Objects.equals(residencehouseno, that.residencehouseno) &&
            Objects.equals(residenceaddress, that.residenceaddress) &&
            Objects.equals(residencecity, that.residencecity) &&
            Objects.equals(residencephone, that.residencephone) &&
            Objects.equals(businesslocationno, that.businesslocationno) &&
            Objects.equals(businessaddress, that.businessaddress) &&
            Objects.equals(businesscity, that.businesscity) &&
            Objects.equals(businessphone1, that.businessphone1) &&
            Objects.equals(businessphone2, that.businessphone2) &&
            Objects.equals(businessmobile, that.businessmobile) &&
            Objects.equals(businessfax, that.businessfax) &&
            Objects.equals(businessemail, that.businessemail) &&
            Objects.equals(businessprovinceid, that.businessprovinceid) &&
            Objects.equals(businessdistrictid, that.businessdistrictid) &&
            Objects.equals(contactpersonname, that.contactpersonname) &&
            Objects.equals(contactpersonphone, that.contactpersonphone) &&
            Objects.equals(contactpersonmobile, that.contactpersonmobile) &&
            Objects.equals(contactpersonemail, that.contactpersonemail) &&
            Objects.equals(rootmappath, that.rootmappath) &&
            Objects.equals(website, that.website) &&
            Objects.equals(registrationdate, that.registrationdate) &&
            Objects.equals(isactive, that.isactive) &&
            Objects.equals(isinternalcustomer, that.isinternalcustomer) &&
            Objects.equals(description, that.description) &&
            Objects.equals(lmu, that.lmu) &&
            Objects.equals(lmd, that.lmd) &&
            Objects.equals(maximumdiscount, that.maximumdiscount) &&
            Objects.equals(creditlimit, that.creditlimit) &&
            Objects.equals(hassecuritydeposit, that.hassecuritydeposit) &&
            Objects.equals(securitydepositamount, that.securitydepositamount) &&
            Objects.equals(paybycash, that.paybycash) &&
            Objects.equals(cashpaymentbeforeload, that.cashpaymentbeforeload) &&
            Objects.equals(cashlastinvoicebeforeload, that.cashlastinvoicebeforeload) &&
            Objects.equals(paybycredit, that.paybycredit) &&
            Objects.equals(creditoneweekcheck, that.creditoneweekcheck) &&
            Objects.equals(creditpaymentbydays, that.creditpaymentbydays) &&
            Objects.equals(haspurchasingdeposit, that.haspurchasingdeposit) &&
            Objects.equals(hassecuritydepositbond, that.hassecuritydepositbond) &&
            Objects.equals(hasassestsdeposit, that.hasassestsdeposit) &&
            Objects.equals(customerrootmappath, that.customerrootmappath) &&
            Objects.equals(employername, that.employername) &&
            Objects.equals(employeraddress, that.employeraddress) &&
            Objects.equals(employerphone, that.employerphone) &&
            Objects.equals(employerdesignation, that.employerdesignation) &&
            Objects.equals(previousemployername, that.previousemployername) &&
            Objects.equals(previousemployeraddress, that.previousemployeraddress) &&
            Objects.equals(previousindustry, that.previousindustry) &&
            Objects.equals(previousperiod, that.previousperiod) &&
            Objects.equals(previouspositions, that.previouspositions) &&
            Objects.equals(previousresionforleaving, that.previousresionforleaving) &&
            Objects.equals(hascreaditlimit, that.hascreaditlimit) &&
            Objects.equals(accountid, that.accountid) &&
            Objects.equals(accountcode, that.accountcode) &&
            Objects.equals(isregistered, that.isregistered) &&
            Objects.equals(vatregnumber, that.vatregnumber) &&
            Objects.equals(tinnumber, that.tinnumber) &&
            Objects.equals(lat, that.lat) &&
            Objects.equals(lon, that.lon) &&
            Objects.equals(creditperiod, that.creditperiod) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            customertype,
            code,
            title,
            namewithinitials,
            fullname,
            callingname,
            nicno,
            nicissueddate,
            dateofbirth,
            bloodgroup,
            gender,
            maritalstatus,
            marrieddate,
            nationality,
            territory,
            religion,
            team,
            businessname,
            businessregdate,
            businessregno,
            profilepicturepath,
            residencehouseno,
            residenceaddress,
            residencecity,
            residencephone,
            businesslocationno,
            businessaddress,
            businesscity,
            businessphone1,
            businessphone2,
            businessmobile,
            businessfax,
            businessemail,
            businessprovinceid,
            businessdistrictid,
            contactpersonname,
            contactpersonphone,
            contactpersonmobile,
            contactpersonemail,
            rootmappath,
            website,
            registrationdate,
            isactive,
            isinternalcustomer,
            description,
            lmu,
            lmd,
            maximumdiscount,
            creditlimit,
            hassecuritydeposit,
            securitydepositamount,
            paybycash,
            cashpaymentbeforeload,
            cashlastinvoicebeforeload,
            paybycredit,
            creditoneweekcheck,
            creditpaymentbydays,
            haspurchasingdeposit,
            hassecuritydepositbond,
            hasassestsdeposit,
            customerrootmappath,
            employername,
            employeraddress,
            employerphone,
            employerdesignation,
            previousemployername,
            previousemployeraddress,
            previousindustry,
            previousperiod,
            previouspositions,
            previousresionforleaving,
            hascreaditlimit,
            accountid,
            accountcode,
            isregistered,
            vatregnumber,
            tinnumber,
            lat,
            lon,
            creditperiod,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalCustomertype().map(f -> "customertype=" + f + ", ").orElse("") +
            optionalCode().map(f -> "code=" + f + ", ").orElse("") +
            optionalTitle().map(f -> "title=" + f + ", ").orElse("") +
            optionalNamewithinitials().map(f -> "namewithinitials=" + f + ", ").orElse("") +
            optionalFullname().map(f -> "fullname=" + f + ", ").orElse("") +
            optionalCallingname().map(f -> "callingname=" + f + ", ").orElse("") +
            optionalNicno().map(f -> "nicno=" + f + ", ").orElse("") +
            optionalNicissueddate().map(f -> "nicissueddate=" + f + ", ").orElse("") +
            optionalDateofbirth().map(f -> "dateofbirth=" + f + ", ").orElse("") +
            optionalBloodgroup().map(f -> "bloodgroup=" + f + ", ").orElse("") +
            optionalGender().map(f -> "gender=" + f + ", ").orElse("") +
            optionalMaritalstatus().map(f -> "maritalstatus=" + f + ", ").orElse("") +
            optionalMarrieddate().map(f -> "marrieddate=" + f + ", ").orElse("") +
            optionalNationality().map(f -> "nationality=" + f + ", ").orElse("") +
            optionalTerritory().map(f -> "territory=" + f + ", ").orElse("") +
            optionalReligion().map(f -> "religion=" + f + ", ").orElse("") +
            optionalTeam().map(f -> "team=" + f + ", ").orElse("") +
            optionalBusinessname().map(f -> "businessname=" + f + ", ").orElse("") +
            optionalBusinessregdate().map(f -> "businessregdate=" + f + ", ").orElse("") +
            optionalBusinessregno().map(f -> "businessregno=" + f + ", ").orElse("") +
            optionalProfilepicturepath().map(f -> "profilepicturepath=" + f + ", ").orElse("") +
            optionalResidencehouseno().map(f -> "residencehouseno=" + f + ", ").orElse("") +
            optionalResidenceaddress().map(f -> "residenceaddress=" + f + ", ").orElse("") +
            optionalResidencecity().map(f -> "residencecity=" + f + ", ").orElse("") +
            optionalResidencephone().map(f -> "residencephone=" + f + ", ").orElse("") +
            optionalBusinesslocationno().map(f -> "businesslocationno=" + f + ", ").orElse("") +
            optionalBusinessaddress().map(f -> "businessaddress=" + f + ", ").orElse("") +
            optionalBusinesscity().map(f -> "businesscity=" + f + ", ").orElse("") +
            optionalBusinessphone1().map(f -> "businessphone1=" + f + ", ").orElse("") +
            optionalBusinessphone2().map(f -> "businessphone2=" + f + ", ").orElse("") +
            optionalBusinessmobile().map(f -> "businessmobile=" + f + ", ").orElse("") +
            optionalBusinessfax().map(f -> "businessfax=" + f + ", ").orElse("") +
            optionalBusinessemail().map(f -> "businessemail=" + f + ", ").orElse("") +
            optionalBusinessprovinceid().map(f -> "businessprovinceid=" + f + ", ").orElse("") +
            optionalBusinessdistrictid().map(f -> "businessdistrictid=" + f + ", ").orElse("") +
            optionalContactpersonname().map(f -> "contactpersonname=" + f + ", ").orElse("") +
            optionalContactpersonphone().map(f -> "contactpersonphone=" + f + ", ").orElse("") +
            optionalContactpersonmobile().map(f -> "contactpersonmobile=" + f + ", ").orElse("") +
            optionalContactpersonemail().map(f -> "contactpersonemail=" + f + ", ").orElse("") +
            optionalRootmappath().map(f -> "rootmappath=" + f + ", ").orElse("") +
            optionalWebsite().map(f -> "website=" + f + ", ").orElse("") +
            optionalRegistrationdate().map(f -> "registrationdate=" + f + ", ").orElse("") +
            optionalIsactive().map(f -> "isactive=" + f + ", ").orElse("") +
            optionalIsinternalcustomer().map(f -> "isinternalcustomer=" + f + ", ").orElse("") +
            optionalDescription().map(f -> "description=" + f + ", ").orElse("") +
            optionalLmu().map(f -> "lmu=" + f + ", ").orElse("") +
            optionalLmd().map(f -> "lmd=" + f + ", ").orElse("") +
            optionalMaximumdiscount().map(f -> "maximumdiscount=" + f + ", ").orElse("") +
            optionalCreditlimit().map(f -> "creditlimit=" + f + ", ").orElse("") +
            optionalHassecuritydeposit().map(f -> "hassecuritydeposit=" + f + ", ").orElse("") +
            optionalSecuritydepositamount().map(f -> "securitydepositamount=" + f + ", ").orElse("") +
            optionalPaybycash().map(f -> "paybycash=" + f + ", ").orElse("") +
            optionalCashpaymentbeforeload().map(f -> "cashpaymentbeforeload=" + f + ", ").orElse("") +
            optionalCashlastinvoicebeforeload().map(f -> "cashlastinvoicebeforeload=" + f + ", ").orElse("") +
            optionalPaybycredit().map(f -> "paybycredit=" + f + ", ").orElse("") +
            optionalCreditoneweekcheck().map(f -> "creditoneweekcheck=" + f + ", ").orElse("") +
            optionalCreditpaymentbydays().map(f -> "creditpaymentbydays=" + f + ", ").orElse("") +
            optionalHaspurchasingdeposit().map(f -> "haspurchasingdeposit=" + f + ", ").orElse("") +
            optionalHassecuritydepositbond().map(f -> "hassecuritydepositbond=" + f + ", ").orElse("") +
            optionalHasassestsdeposit().map(f -> "hasassestsdeposit=" + f + ", ").orElse("") +
            optionalCustomerrootmappath().map(f -> "customerrootmappath=" + f + ", ").orElse("") +
            optionalEmployername().map(f -> "employername=" + f + ", ").orElse("") +
            optionalEmployeraddress().map(f -> "employeraddress=" + f + ", ").orElse("") +
            optionalEmployerphone().map(f -> "employerphone=" + f + ", ").orElse("") +
            optionalEmployerdesignation().map(f -> "employerdesignation=" + f + ", ").orElse("") +
            optionalPreviousemployername().map(f -> "previousemployername=" + f + ", ").orElse("") +
            optionalPreviousemployeraddress().map(f -> "previousemployeraddress=" + f + ", ").orElse("") +
            optionalPreviousindustry().map(f -> "previousindustry=" + f + ", ").orElse("") +
            optionalPreviousperiod().map(f -> "previousperiod=" + f + ", ").orElse("") +
            optionalPreviouspositions().map(f -> "previouspositions=" + f + ", ").orElse("") +
            optionalPreviousresionforleaving().map(f -> "previousresionforleaving=" + f + ", ").orElse("") +
            optionalHascreaditlimit().map(f -> "hascreaditlimit=" + f + ", ").orElse("") +
            optionalAccountid().map(f -> "accountid=" + f + ", ").orElse("") +
            optionalAccountcode().map(f -> "accountcode=" + f + ", ").orElse("") +
            optionalIsregistered().map(f -> "isregistered=" + f + ", ").orElse("") +
            optionalVatregnumber().map(f -> "vatregnumber=" + f + ", ").orElse("") +
            optionalTinnumber().map(f -> "tinnumber=" + f + ", ").orElse("") +
            optionalLat().map(f -> "lat=" + f + ", ").orElse("") +
            optionalLon().map(f -> "lon=" + f + ", ").orElse("") +
            optionalCreditperiod().map(f -> "creditperiod=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
