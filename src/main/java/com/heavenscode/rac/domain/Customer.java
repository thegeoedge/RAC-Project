package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "customertype")
    private Integer customertype;

    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "namewithinitials")
    private String namewithinitials;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "callingname")
    private String callingname;

    @Column(name = "nicno")
    private String nicno;

    @Column(name = "nicissueddate")
    private LocalDate nicissueddate;

    @Column(name = "dateofbirth")
    private LocalDate dateofbirth;

    @Column(name = "bloodgroup")
    private String bloodgroup;

    @Column(name = "gender")
    private String gender;

    @Column(name = "maritalstatus")
    private String maritalstatus;

    @Column(name = "marrieddate")
    private LocalDate marrieddate;

    @Column(name = "nationality")
    private Integer nationality;

    @Column(name = "territory")
    private String territory;

    @Column(name = "religion")
    private Integer religion;

    @Column(name = "team")
    private String team;

    @Column(name = "businessname")
    private String businessname;

    @Column(name = "businessregdate")
    private LocalDate businessregdate;

    @Column(name = "businessregno")
    private String businessregno;

    @Column(name = "profilepicturepath")
    private String profilepicturepath;

    @Column(name = "residencehouseno")
    private String residencehouseno;

    @Column(name = "residenceaddress")
    private String residenceaddress;

    @Column(name = "residencecity")
    private String residencecity;

    @Column(name = "residencephone")
    private String residencephone;

    @Column(name = "businesslocationno")
    private String businesslocationno;

    @Column(name = "businessaddress")
    private String businessaddress;

    @Column(name = "businesscity")
    private String businesscity;

    @Column(name = "businessphone1")
    private String businessphone1;

    @Column(name = "businessphone2")
    private String businessphone2;

    @Column(name = "businessmobile")
    private String businessmobile;

    @Column(name = "businessfax")
    private String businessfax;

    @Column(name = "businessemail")
    private String businessemail;

    @Column(name = "businessprovinceid")
    private Integer businessprovinceid;

    @Column(name = "businessdistrictid")
    private Integer businessdistrictid;

    @Column(name = "contactpersonname")
    private String contactpersonname;

    @Column(name = "contactpersonphone")
    private String contactpersonphone;

    @Column(name = "contactpersonmobile")
    private String contactpersonmobile;

    @Column(name = "contactpersonemail")
    private String contactpersonemail;

    @Column(name = "rootmappath")
    private String rootmappath;

    @Column(name = "website")
    private String website;

    @Column(name = "registrationdate")
    private Instant registrationdate;

    @Column(name = "isactive")
    private Integer isactive;

    @Column(name = "isinternalcustomer")
    private Integer isinternalcustomer;

    @Column(name = "description")
    private String description;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "maximumdiscount")
    private Float maximumdiscount;

    @Column(name = "creditlimit")
    private Float creditlimit;

    @Column(name = "hassecuritydeposit")
    private Boolean hassecuritydeposit;

    @Column(name = "securitydepositamount")
    private Float securitydepositamount;

    @Column(name = "paybycash")
    private Boolean paybycash;

    @Column(name = "cashpaymentbeforeload")
    private Boolean cashpaymentbeforeload;

    @Column(name = "cashlastinvoicebeforeload")
    private Boolean cashlastinvoicebeforeload;

    @Column(name = "paybycredit")
    private Boolean paybycredit;

    @Column(name = "creditoneweekcheck")
    private Boolean creditoneweekcheck;

    @Column(name = "creditpaymentbydays")
    private Integer creditpaymentbydays;

    @Column(name = "haspurchasingdeposit")
    private Boolean haspurchasingdeposit;

    @Column(name = "hassecuritydepositbond")
    private Boolean hassecuritydepositbond;

    @Column(name = "hasassestsdeposit")
    private Boolean hasassestsdeposit;

    @Column(name = "customerrootmappath")
    private String customerrootmappath;

    @Column(name = "employername")
    private String employername;

    @Column(name = "employeraddress")
    private String employeraddress;

    @Column(name = "employerphone")
    private String employerphone;

    @Column(name = "employerdesignation")
    private String employerdesignation;

    @Column(name = "previousemployername")
    private String previousemployername;

    @Column(name = "previousemployeraddress")
    private String previousemployeraddress;

    @Column(name = "previousindustry")
    private String previousindustry;

    @Column(name = "previousperiod")
    private String previousperiod;

    @Column(name = "previouspositions")
    private String previouspositions;

    @Column(name = "previousresionforleaving")
    private String previousresionforleaving;

    @Column(name = "hascreaditlimit")
    private Boolean hascreaditlimit;

    @Column(name = "accountid")
    private Integer accountid;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "isregistered")
    private Boolean isregistered;

    @Column(name = "vatregnumber")
    private String vatregnumber;

    @Column(name = "tinnumber")
    private String tinnumber;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lon")
    private String lon;

    @Column(name = "creditperiod")
    private Integer creditperiod;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomertype() {
        return this.customertype;
    }

    public Customer customertype(Integer customertype) {
        this.setCustomertype(customertype);
        return this;
    }

    public void setCustomertype(Integer customertype) {
        this.customertype = customertype;
    }

    public String getCode() {
        return this.code;
    }

    public Customer code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public Customer title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNamewithinitials() {
        return this.namewithinitials;
    }

    public Customer namewithinitials(String namewithinitials) {
        this.setNamewithinitials(namewithinitials);
        return this;
    }

    public void setNamewithinitials(String namewithinitials) {
        this.namewithinitials = namewithinitials;
    }

    public String getFullname() {
        return this.fullname;
    }

    public Customer fullname(String fullname) {
        this.setFullname(fullname);
        return this;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCallingname() {
        return this.callingname;
    }

    public Customer callingname(String callingname) {
        this.setCallingname(callingname);
        return this;
    }

    public void setCallingname(String callingname) {
        this.callingname = callingname;
    }

    public String getNicno() {
        return this.nicno;
    }

    public Customer nicno(String nicno) {
        this.setNicno(nicno);
        return this;
    }

    public void setNicno(String nicno) {
        this.nicno = nicno;
    }

    public LocalDate getNicissueddate() {
        return this.nicissueddate;
    }

    public Customer nicissueddate(LocalDate nicissueddate) {
        this.setNicissueddate(nicissueddate);
        return this;
    }

    public void setNicissueddate(LocalDate nicissueddate) {
        this.nicissueddate = nicissueddate;
    }

    public LocalDate getDateofbirth() {
        return this.dateofbirth;
    }

    public Customer dateofbirth(LocalDate dateofbirth) {
        this.setDateofbirth(dateofbirth);
        return this;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getBloodgroup() {
        return this.bloodgroup;
    }

    public Customer bloodgroup(String bloodgroup) {
        this.setBloodgroup(bloodgroup);
        return this;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getGender() {
        return this.gender;
    }

    public Customer gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalstatus() {
        return this.maritalstatus;
    }

    public Customer maritalstatus(String maritalstatus) {
        this.setMaritalstatus(maritalstatus);
        return this;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public LocalDate getMarrieddate() {
        return this.marrieddate;
    }

    public Customer marrieddate(LocalDate marrieddate) {
        this.setMarrieddate(marrieddate);
        return this;
    }

    public void setMarrieddate(LocalDate marrieddate) {
        this.marrieddate = marrieddate;
    }

    public Integer getNationality() {
        return this.nationality;
    }

    public Customer nationality(Integer nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(Integer nationality) {
        this.nationality = nationality;
    }

    public String getTerritory() {
        return this.territory;
    }

    public Customer territory(String territory) {
        this.setTerritory(territory);
        return this;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public Integer getReligion() {
        return this.religion;
    }

    public Customer religion(Integer religion) {
        this.setReligion(religion);
        return this;
    }

    public void setReligion(Integer religion) {
        this.religion = religion;
    }

    public String getTeam() {
        return this.team;
    }

    public Customer team(String team) {
        this.setTeam(team);
        return this;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getBusinessname() {
        return this.businessname;
    }

    public Customer businessname(String businessname) {
        this.setBusinessname(businessname);
        return this;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public LocalDate getBusinessregdate() {
        return this.businessregdate;
    }

    public Customer businessregdate(LocalDate businessregdate) {
        this.setBusinessregdate(businessregdate);
        return this;
    }

    public void setBusinessregdate(LocalDate businessregdate) {
        this.businessregdate = businessregdate;
    }

    public String getBusinessregno() {
        return this.businessregno;
    }

    public Customer businessregno(String businessregno) {
        this.setBusinessregno(businessregno);
        return this;
    }

    public void setBusinessregno(String businessregno) {
        this.businessregno = businessregno;
    }

    public String getProfilepicturepath() {
        return this.profilepicturepath;
    }

    public Customer profilepicturepath(String profilepicturepath) {
        this.setProfilepicturepath(profilepicturepath);
        return this;
    }

    public void setProfilepicturepath(String profilepicturepath) {
        this.profilepicturepath = profilepicturepath;
    }

    public String getResidencehouseno() {
        return this.residencehouseno;
    }

    public Customer residencehouseno(String residencehouseno) {
        this.setResidencehouseno(residencehouseno);
        return this;
    }

    public void setResidencehouseno(String residencehouseno) {
        this.residencehouseno = residencehouseno;
    }

    public String getResidenceaddress() {
        return this.residenceaddress;
    }

    public Customer residenceaddress(String residenceaddress) {
        this.setResidenceaddress(residenceaddress);
        return this;
    }

    public void setResidenceaddress(String residenceaddress) {
        this.residenceaddress = residenceaddress;
    }

    public String getResidencecity() {
        return this.residencecity;
    }

    public Customer residencecity(String residencecity) {
        this.setResidencecity(residencecity);
        return this;
    }

    public void setResidencecity(String residencecity) {
        this.residencecity = residencecity;
    }

    public String getResidencephone() {
        return this.residencephone;
    }

    public Customer residencephone(String residencephone) {
        this.setResidencephone(residencephone);
        return this;
    }

    public void setResidencephone(String residencephone) {
        this.residencephone = residencephone;
    }

    public String getBusinesslocationno() {
        return this.businesslocationno;
    }

    public Customer businesslocationno(String businesslocationno) {
        this.setBusinesslocationno(businesslocationno);
        return this;
    }

    public void setBusinesslocationno(String businesslocationno) {
        this.businesslocationno = businesslocationno;
    }

    public String getBusinessaddress() {
        return this.businessaddress;
    }

    public Customer businessaddress(String businessaddress) {
        this.setBusinessaddress(businessaddress);
        return this;
    }

    public void setBusinessaddress(String businessaddress) {
        this.businessaddress = businessaddress;
    }

    public String getBusinesscity() {
        return this.businesscity;
    }

    public Customer businesscity(String businesscity) {
        this.setBusinesscity(businesscity);
        return this;
    }

    public void setBusinesscity(String businesscity) {
        this.businesscity = businesscity;
    }

    public String getBusinessphone1() {
        return this.businessphone1;
    }

    public Customer businessphone1(String businessphone1) {
        this.setBusinessphone1(businessphone1);
        return this;
    }

    public void setBusinessphone1(String businessphone1) {
        this.businessphone1 = businessphone1;
    }

    public String getBusinessphone2() {
        return this.businessphone2;
    }

    public Customer businessphone2(String businessphone2) {
        this.setBusinessphone2(businessphone2);
        return this;
    }

    public void setBusinessphone2(String businessphone2) {
        this.businessphone2 = businessphone2;
    }

    public String getBusinessmobile() {
        return this.businessmobile;
    }

    public Customer businessmobile(String businessmobile) {
        this.setBusinessmobile(businessmobile);
        return this;
    }

    public void setBusinessmobile(String businessmobile) {
        this.businessmobile = businessmobile;
    }

    public String getBusinessfax() {
        return this.businessfax;
    }

    public Customer businessfax(String businessfax) {
        this.setBusinessfax(businessfax);
        return this;
    }

    public void setBusinessfax(String businessfax) {
        this.businessfax = businessfax;
    }

    public String getBusinessemail() {
        return this.businessemail;
    }

    public Customer businessemail(String businessemail) {
        this.setBusinessemail(businessemail);
        return this;
    }

    public void setBusinessemail(String businessemail) {
        this.businessemail = businessemail;
    }

    public Integer getBusinessprovinceid() {
        return this.businessprovinceid;
    }

    public Customer businessprovinceid(Integer businessprovinceid) {
        this.setBusinessprovinceid(businessprovinceid);
        return this;
    }

    public void setBusinessprovinceid(Integer businessprovinceid) {
        this.businessprovinceid = businessprovinceid;
    }

    public Integer getBusinessdistrictid() {
        return this.businessdistrictid;
    }

    public Customer businessdistrictid(Integer businessdistrictid) {
        this.setBusinessdistrictid(businessdistrictid);
        return this;
    }

    public void setBusinessdistrictid(Integer businessdistrictid) {
        this.businessdistrictid = businessdistrictid;
    }

    public String getContactpersonname() {
        return this.contactpersonname;
    }

    public Customer contactpersonname(String contactpersonname) {
        this.setContactpersonname(contactpersonname);
        return this;
    }

    public void setContactpersonname(String contactpersonname) {
        this.contactpersonname = contactpersonname;
    }

    public String getContactpersonphone() {
        return this.contactpersonphone;
    }

    public Customer contactpersonphone(String contactpersonphone) {
        this.setContactpersonphone(contactpersonphone);
        return this;
    }

    public void setContactpersonphone(String contactpersonphone) {
        this.contactpersonphone = contactpersonphone;
    }

    public String getContactpersonmobile() {
        return this.contactpersonmobile;
    }

    public Customer contactpersonmobile(String contactpersonmobile) {
        this.setContactpersonmobile(contactpersonmobile);
        return this;
    }

    public void setContactpersonmobile(String contactpersonmobile) {
        this.contactpersonmobile = contactpersonmobile;
    }

    public String getContactpersonemail() {
        return this.contactpersonemail;
    }

    public Customer contactpersonemail(String contactpersonemail) {
        this.setContactpersonemail(contactpersonemail);
        return this;
    }

    public void setContactpersonemail(String contactpersonemail) {
        this.contactpersonemail = contactpersonemail;
    }

    public String getRootmappath() {
        return this.rootmappath;
    }

    public Customer rootmappath(String rootmappath) {
        this.setRootmappath(rootmappath);
        return this;
    }

    public void setRootmappath(String rootmappath) {
        this.rootmappath = rootmappath;
    }

    public String getWebsite() {
        return this.website;
    }

    public Customer website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Instant getRegistrationdate() {
        return this.registrationdate;
    }

    public Customer registrationdate(Instant registrationdate) {
        this.setRegistrationdate(registrationdate);
        return this;
    }

    public void setRegistrationdate(Instant registrationdate) {
        this.registrationdate = registrationdate;
    }

    public Integer getIsactive() {
        return this.isactive;
    }

    public Customer isactive(Integer isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public Integer getIsinternalcustomer() {
        return this.isinternalcustomer;
    }

    public Customer isinternalcustomer(Integer isinternalcustomer) {
        this.setIsinternalcustomer(isinternalcustomer);
        return this;
    }

    public void setIsinternalcustomer(Integer isinternalcustomer) {
        this.isinternalcustomer = isinternalcustomer;
    }

    public String getDescription() {
        return this.description;
    }

    public Customer description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Customer lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Customer lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Float getMaximumdiscount() {
        return this.maximumdiscount;
    }

    public Customer maximumdiscount(Float maximumdiscount) {
        this.setMaximumdiscount(maximumdiscount);
        return this;
    }

    public void setMaximumdiscount(Float maximumdiscount) {
        this.maximumdiscount = maximumdiscount;
    }

    public Float getCreditlimit() {
        return this.creditlimit;
    }

    public Customer creditlimit(Float creditlimit) {
        this.setCreditlimit(creditlimit);
        return this;
    }

    public void setCreditlimit(Float creditlimit) {
        this.creditlimit = creditlimit;
    }

    public Boolean getHassecuritydeposit() {
        return this.hassecuritydeposit;
    }

    public Customer hassecuritydeposit(Boolean hassecuritydeposit) {
        this.setHassecuritydeposit(hassecuritydeposit);
        return this;
    }

    public void setHassecuritydeposit(Boolean hassecuritydeposit) {
        this.hassecuritydeposit = hassecuritydeposit;
    }

    public Float getSecuritydepositamount() {
        return this.securitydepositamount;
    }

    public Customer securitydepositamount(Float securitydepositamount) {
        this.setSecuritydepositamount(securitydepositamount);
        return this;
    }

    public void setSecuritydepositamount(Float securitydepositamount) {
        this.securitydepositamount = securitydepositamount;
    }

    public Boolean getPaybycash() {
        return this.paybycash;
    }

    public Customer paybycash(Boolean paybycash) {
        this.setPaybycash(paybycash);
        return this;
    }

    public void setPaybycash(Boolean paybycash) {
        this.paybycash = paybycash;
    }

    public Boolean getCashpaymentbeforeload() {
        return this.cashpaymentbeforeload;
    }

    public Customer cashpaymentbeforeload(Boolean cashpaymentbeforeload) {
        this.setCashpaymentbeforeload(cashpaymentbeforeload);
        return this;
    }

    public void setCashpaymentbeforeload(Boolean cashpaymentbeforeload) {
        this.cashpaymentbeforeload = cashpaymentbeforeload;
    }

    public Boolean getCashlastinvoicebeforeload() {
        return this.cashlastinvoicebeforeload;
    }

    public Customer cashlastinvoicebeforeload(Boolean cashlastinvoicebeforeload) {
        this.setCashlastinvoicebeforeload(cashlastinvoicebeforeload);
        return this;
    }

    public void setCashlastinvoicebeforeload(Boolean cashlastinvoicebeforeload) {
        this.cashlastinvoicebeforeload = cashlastinvoicebeforeload;
    }

    public Boolean getPaybycredit() {
        return this.paybycredit;
    }

    public Customer paybycredit(Boolean paybycredit) {
        this.setPaybycredit(paybycredit);
        return this;
    }

    public void setPaybycredit(Boolean paybycredit) {
        this.paybycredit = paybycredit;
    }

    public Boolean getCreditoneweekcheck() {
        return this.creditoneweekcheck;
    }

    public Customer creditoneweekcheck(Boolean creditoneweekcheck) {
        this.setCreditoneweekcheck(creditoneweekcheck);
        return this;
    }

    public void setCreditoneweekcheck(Boolean creditoneweekcheck) {
        this.creditoneweekcheck = creditoneweekcheck;
    }

    public Integer getCreditpaymentbydays() {
        return this.creditpaymentbydays;
    }

    public Customer creditpaymentbydays(Integer creditpaymentbydays) {
        this.setCreditpaymentbydays(creditpaymentbydays);
        return this;
    }

    public void setCreditpaymentbydays(Integer creditpaymentbydays) {
        this.creditpaymentbydays = creditpaymentbydays;
    }

    public Boolean getHaspurchasingdeposit() {
        return this.haspurchasingdeposit;
    }

    public Customer haspurchasingdeposit(Boolean haspurchasingdeposit) {
        this.setHaspurchasingdeposit(haspurchasingdeposit);
        return this;
    }

    public void setHaspurchasingdeposit(Boolean haspurchasingdeposit) {
        this.haspurchasingdeposit = haspurchasingdeposit;
    }

    public Boolean getHassecuritydepositbond() {
        return this.hassecuritydepositbond;
    }

    public Customer hassecuritydepositbond(Boolean hassecuritydepositbond) {
        this.setHassecuritydepositbond(hassecuritydepositbond);
        return this;
    }

    public void setHassecuritydepositbond(Boolean hassecuritydepositbond) {
        this.hassecuritydepositbond = hassecuritydepositbond;
    }

    public Boolean getHasassestsdeposit() {
        return this.hasassestsdeposit;
    }

    public Customer hasassestsdeposit(Boolean hasassestsdeposit) {
        this.setHasassestsdeposit(hasassestsdeposit);
        return this;
    }

    public void setHasassestsdeposit(Boolean hasassestsdeposit) {
        this.hasassestsdeposit = hasassestsdeposit;
    }

    public String getCustomerrootmappath() {
        return this.customerrootmappath;
    }

    public Customer customerrootmappath(String customerrootmappath) {
        this.setCustomerrootmappath(customerrootmappath);
        return this;
    }

    public void setCustomerrootmappath(String customerrootmappath) {
        this.customerrootmappath = customerrootmappath;
    }

    public String getEmployername() {
        return this.employername;
    }

    public Customer employername(String employername) {
        this.setEmployername(employername);
        return this;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getEmployeraddress() {
        return this.employeraddress;
    }

    public Customer employeraddress(String employeraddress) {
        this.setEmployeraddress(employeraddress);
        return this;
    }

    public void setEmployeraddress(String employeraddress) {
        this.employeraddress = employeraddress;
    }

    public String getEmployerphone() {
        return this.employerphone;
    }

    public Customer employerphone(String employerphone) {
        this.setEmployerphone(employerphone);
        return this;
    }

    public void setEmployerphone(String employerphone) {
        this.employerphone = employerphone;
    }

    public String getEmployerdesignation() {
        return this.employerdesignation;
    }

    public Customer employerdesignation(String employerdesignation) {
        this.setEmployerdesignation(employerdesignation);
        return this;
    }

    public void setEmployerdesignation(String employerdesignation) {
        this.employerdesignation = employerdesignation;
    }

    public String getPreviousemployername() {
        return this.previousemployername;
    }

    public Customer previousemployername(String previousemployername) {
        this.setPreviousemployername(previousemployername);
        return this;
    }

    public void setPreviousemployername(String previousemployername) {
        this.previousemployername = previousemployername;
    }

    public String getPreviousemployeraddress() {
        return this.previousemployeraddress;
    }

    public Customer previousemployeraddress(String previousemployeraddress) {
        this.setPreviousemployeraddress(previousemployeraddress);
        return this;
    }

    public void setPreviousemployeraddress(String previousemployeraddress) {
        this.previousemployeraddress = previousemployeraddress;
    }

    public String getPreviousindustry() {
        return this.previousindustry;
    }

    public Customer previousindustry(String previousindustry) {
        this.setPreviousindustry(previousindustry);
        return this;
    }

    public void setPreviousindustry(String previousindustry) {
        this.previousindustry = previousindustry;
    }

    public String getPreviousperiod() {
        return this.previousperiod;
    }

    public Customer previousperiod(String previousperiod) {
        this.setPreviousperiod(previousperiod);
        return this;
    }

    public void setPreviousperiod(String previousperiod) {
        this.previousperiod = previousperiod;
    }

    public String getPreviouspositions() {
        return this.previouspositions;
    }

    public Customer previouspositions(String previouspositions) {
        this.setPreviouspositions(previouspositions);
        return this;
    }

    public void setPreviouspositions(String previouspositions) {
        this.previouspositions = previouspositions;
    }

    public String getPreviousresionforleaving() {
        return this.previousresionforleaving;
    }

    public Customer previousresionforleaving(String previousresionforleaving) {
        this.setPreviousresionforleaving(previousresionforleaving);
        return this;
    }

    public void setPreviousresionforleaving(String previousresionforleaving) {
        this.previousresionforleaving = previousresionforleaving;
    }

    public Boolean getHascreaditlimit() {
        return this.hascreaditlimit;
    }

    public Customer hascreaditlimit(Boolean hascreaditlimit) {
        this.setHascreaditlimit(hascreaditlimit);
        return this;
    }

    public void setHascreaditlimit(Boolean hascreaditlimit) {
        this.hascreaditlimit = hascreaditlimit;
    }

    public Integer getAccountid() {
        return this.accountid;
    }

    public Customer accountid(Integer accountid) {
        this.setAccountid(accountid);
        return this;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getAccountcode() {
        return this.accountcode;
    }

    public Customer accountcode(String accountcode) {
        this.setAccountcode(accountcode);
        return this;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public Boolean getIsregistered() {
        return this.isregistered;
    }

    public Customer isregistered(Boolean isregistered) {
        this.setIsregistered(isregistered);
        return this;
    }

    public void setIsregistered(Boolean isregistered) {
        this.isregistered = isregistered;
    }

    public String getVatregnumber() {
        return this.vatregnumber;
    }

    public Customer vatregnumber(String vatregnumber) {
        this.setVatregnumber(vatregnumber);
        return this;
    }

    public void setVatregnumber(String vatregnumber) {
        this.vatregnumber = vatregnumber;
    }

    public String getTinnumber() {
        return this.tinnumber;
    }

    public Customer tinnumber(String tinnumber) {
        this.setTinnumber(tinnumber);
        return this;
    }

    public void setTinnumber(String tinnumber) {
        this.tinnumber = tinnumber;
    }

    public String getLat() {
        return this.lat;
    }

    public Customer lat(String lat) {
        this.setLat(lat);
        return this;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return this.lon;
    }

    public Customer lon(String lon) {
        this.setLon(lon);
        return this;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Integer getCreditperiod() {
        return this.creditperiod;
    }

    public Customer creditperiod(Integer creditperiod) {
        this.setCreditperiod(creditperiod);
        return this;
    }

    public void setCreditperiod(Integer creditperiod) {
        this.creditperiod = creditperiod;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return getId() != null && getId().equals(((Customer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", customertype=" + getCustomertype() +
            ", code='" + getCode() + "'" +
            ", title='" + getTitle() + "'" +
            ", namewithinitials='" + getNamewithinitials() + "'" +
            ", fullname='" + getFullname() + "'" +
            ", callingname='" + getCallingname() + "'" +
            ", nicno='" + getNicno() + "'" +
            ", nicissueddate='" + getNicissueddate() + "'" +
            ", dateofbirth='" + getDateofbirth() + "'" +
            ", bloodgroup='" + getBloodgroup() + "'" +
            ", gender='" + getGender() + "'" +
            ", maritalstatus='" + getMaritalstatus() + "'" +
            ", marrieddate='" + getMarrieddate() + "'" +
            ", nationality=" + getNationality() +
            ", territory='" + getTerritory() + "'" +
            ", religion=" + getReligion() +
            ", team='" + getTeam() + "'" +
            ", businessname='" + getBusinessname() + "'" +
            ", businessregdate='" + getBusinessregdate() + "'" +
            ", businessregno='" + getBusinessregno() + "'" +
            ", profilepicturepath='" + getProfilepicturepath() + "'" +
            ", residencehouseno='" + getResidencehouseno() + "'" +
            ", residenceaddress='" + getResidenceaddress() + "'" +
            ", residencecity='" + getResidencecity() + "'" +
            ", residencephone='" + getResidencephone() + "'" +
            ", businesslocationno='" + getBusinesslocationno() + "'" +
            ", businessaddress='" + getBusinessaddress() + "'" +
            ", businesscity='" + getBusinesscity() + "'" +
            ", businessphone1='" + getBusinessphone1() + "'" +
            ", businessphone2='" + getBusinessphone2() + "'" +
            ", businessmobile='" + getBusinessmobile() + "'" +
            ", businessfax='" + getBusinessfax() + "'" +
            ", businessemail='" + getBusinessemail() + "'" +
            ", businessprovinceid=" + getBusinessprovinceid() +
            ", businessdistrictid=" + getBusinessdistrictid() +
            ", contactpersonname='" + getContactpersonname() + "'" +
            ", contactpersonphone='" + getContactpersonphone() + "'" +
            ", contactpersonmobile='" + getContactpersonmobile() + "'" +
            ", contactpersonemail='" + getContactpersonemail() + "'" +
            ", rootmappath='" + getRootmappath() + "'" +
            ", website='" + getWebsite() + "'" +
            ", registrationdate='" + getRegistrationdate() + "'" +
            ", isactive=" + getIsactive() +
            ", isinternalcustomer=" + getIsinternalcustomer() +
            ", description='" + getDescription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", maximumdiscount=" + getMaximumdiscount() +
            ", creditlimit=" + getCreditlimit() +
            ", hassecuritydeposit='" + getHassecuritydeposit() + "'" +
            ", securitydepositamount=" + getSecuritydepositamount() +
            ", paybycash='" + getPaybycash() + "'" +
            ", cashpaymentbeforeload='" + getCashpaymentbeforeload() + "'" +
            ", cashlastinvoicebeforeload='" + getCashlastinvoicebeforeload() + "'" +
            ", paybycredit='" + getPaybycredit() + "'" +
            ", creditoneweekcheck='" + getCreditoneweekcheck() + "'" +
            ", creditpaymentbydays=" + getCreditpaymentbydays() +
            ", haspurchasingdeposit='" + getHaspurchasingdeposit() + "'" +
            ", hassecuritydepositbond='" + getHassecuritydepositbond() + "'" +
            ", hasassestsdeposit='" + getHasassestsdeposit() + "'" +
            ", customerrootmappath='" + getCustomerrootmappath() + "'" +
            ", employername='" + getEmployername() + "'" +
            ", employeraddress='" + getEmployeraddress() + "'" +
            ", employerphone='" + getEmployerphone() + "'" +
            ", employerdesignation='" + getEmployerdesignation() + "'" +
            ", previousemployername='" + getPreviousemployername() + "'" +
            ", previousemployeraddress='" + getPreviousemployeraddress() + "'" +
            ", previousindustry='" + getPreviousindustry() + "'" +
            ", previousperiod='" + getPreviousperiod() + "'" +
            ", previouspositions='" + getPreviouspositions() + "'" +
            ", previousresionforleaving='" + getPreviousresionforleaving() + "'" +
            ", hascreaditlimit='" + getHascreaditlimit() + "'" +
            ", accountid=" + getAccountid() +
            ", accountcode='" + getAccountcode() + "'" +
            ", isregistered='" + getIsregistered() + "'" +
            ", vatregnumber='" + getVatregnumber() + "'" +
            ", tinnumber='" + getTinnumber() + "'" +
            ", lat='" + getLat() + "'" +
            ", lon='" + getLon() + "'" +
            ", creditperiod=" + getCreditperiod() +
            "}";
    }
}
