package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Customervehicle.
 */
@Entity
@Table(name = "customervehicle")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customervehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "vehiclenumber")
    private String vehiclenumber;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "categoryname")
    private String categoryname;

    @Column(name = "typeid")
    private Integer typeid;

    @Column(name = "typename")
    private String typename;

    @Column(name = "makeid")
    private Integer makeid;

    @Column(name = "makename")
    private String makename;

    @Column(name = "model")
    private String model;

    @Column(name = "yom")
    private String yom;

    @Column(name = "customercode")
    private String customercode;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "servicecount")
    private Integer servicecount;

    @Column(name = "eng_no")
    private String engNo;

    @Column(name = "cha_no")
    private String chaNo;

    @Column(name = "milage")
    private String milage;

    @Column(name = "lastservicedate")
    private LocalDate lastservicedate;

    @Column(name = "nextservicedate")
    private LocalDate nextservicedate;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "nextgearoilmilage")
    private String nextgearoilmilage;

    @Column(name = "nextmilage")
    private String nextmilage;

    @Column(name = "serviceperiod")
    private Integer serviceperiod;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customervehicle id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Customervehicle customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getVehiclenumber() {
        return this.vehiclenumber;
    }

    public Customervehicle vehiclenumber(String vehiclenumber) {
        this.setVehiclenumber(vehiclenumber);
        return this;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }

    public Customervehicle categoryid(Integer categoryid) {
        this.setCategoryid(categoryid);
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return this.categoryname;
    }

    public Customervehicle categoryname(String categoryname) {
        this.setCategoryname(categoryname);
        return this;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Integer getTypeid() {
        return this.typeid;
    }

    public Customervehicle typeid(Integer typeid) {
        this.setTypeid(typeid);
        return this;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return this.typename;
    }

    public Customervehicle typename(String typename) {
        this.setTypename(typename);
        return this;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getMakeid() {
        return this.makeid;
    }

    public Customervehicle makeid(Integer makeid) {
        this.setMakeid(makeid);
        return this;
    }

    public void setMakeid(Integer makeid) {
        this.makeid = makeid;
    }

    public String getMakename() {
        return this.makename;
    }

    public Customervehicle makename(String makename) {
        this.setMakename(makename);
        return this;
    }

    public void setMakename(String makename) {
        this.makename = makename;
    }

    public String getModel() {
        return this.model;
    }

    public Customervehicle model(String model) {
        this.setModel(model);
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYom() {
        return this.yom;
    }

    public Customervehicle yom(String yom) {
        this.setYom(yom);
        return this;
    }

    public void setYom(String yom) {
        this.yom = yom;
    }

    public String getCustomercode() {
        return this.customercode;
    }

    public Customervehicle customercode(String customercode) {
        this.setCustomercode(customercode);
        return this;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Customervehicle remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getServicecount() {
        return this.servicecount;
    }

    public Customervehicle servicecount(Integer servicecount) {
        this.setServicecount(servicecount);
        return this;
    }

    public void setServicecount(Integer servicecount) {
        this.servicecount = servicecount;
    }

    public String getEngNo() {
        return this.engNo;
    }

    public Customervehicle engNo(String engNo) {
        this.setEngNo(engNo);
        return this;
    }

    public void setEngNo(String engNo) {
        this.engNo = engNo;
    }

    public String getChaNo() {
        return this.chaNo;
    }

    public Customervehicle chaNo(String chaNo) {
        this.setChaNo(chaNo);
        return this;
    }

    public void setChaNo(String chaNo) {
        this.chaNo = chaNo;
    }

    public String getMilage() {
        return this.milage;
    }

    public Customervehicle milage(String milage) {
        this.setMilage(milage);
        return this;
    }

    public void setMilage(String milage) {
        this.milage = milage;
    }

    public LocalDate getLastservicedate() {
        return this.lastservicedate;
    }

    public Customervehicle lastservicedate(LocalDate lastservicedate) {
        this.setLastservicedate(lastservicedate);
        return this;
    }

    public void setLastservicedate(LocalDate lastservicedate) {
        this.lastservicedate = lastservicedate;
    }

    public LocalDate getNextservicedate() {
        return this.nextservicedate;
    }

    public Customervehicle nextservicedate(LocalDate nextservicedate) {
        this.setNextservicedate(nextservicedate);
        return this;
    }

    public void setNextservicedate(LocalDate nextservicedate) {
        this.nextservicedate = nextservicedate;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Customervehicle lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Customervehicle lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public String getNextgearoilmilage() {
        return this.nextgearoilmilage;
    }

    public Customervehicle nextgearoilmilage(String nextgearoilmilage) {
        this.setNextgearoilmilage(nextgearoilmilage);
        return this;
    }

    public void setNextgearoilmilage(String nextgearoilmilage) {
        this.nextgearoilmilage = nextgearoilmilage;
    }

    public String getNextmilage() {
        return this.nextmilage;
    }

    public Customervehicle nextmilage(String nextmilage) {
        this.setNextmilage(nextmilage);
        return this;
    }

    public void setNextmilage(String nextmilage) {
        this.nextmilage = nextmilage;
    }

    public Integer getServiceperiod() {
        return this.serviceperiod;
    }

    public Customervehicle serviceperiod(Integer serviceperiod) {
        this.setServiceperiod(serviceperiod);
        return this;
    }

    public void setServiceperiod(Integer serviceperiod) {
        this.serviceperiod = serviceperiod;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customervehicle)) {
            return false;
        }
        return getId() != null && getId().equals(((Customervehicle) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customervehicle{" +
            "id=" + getId() +
            ", customerid=" + getCustomerid() +
            ", vehiclenumber='" + getVehiclenumber() + "'" +
            ", categoryid=" + getCategoryid() +
            ", categoryname='" + getCategoryname() + "'" +
            ", typeid=" + getTypeid() +
            ", typename='" + getTypename() + "'" +
            ", makeid=" + getMakeid() +
            ", makename='" + getMakename() + "'" +
            ", model='" + getModel() + "'" +
            ", yom='" + getYom() + "'" +
            ", customercode='" + getCustomercode() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", servicecount=" + getServicecount() +
            ", engNo='" + getEngNo() + "'" +
            ", chaNo='" + getChaNo() + "'" +
            ", milage='" + getMilage() + "'" +
            ", lastservicedate='" + getLastservicedate() + "'" +
            ", nextservicedate='" + getNextservicedate() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", nextgearoilmilage='" + getNextgearoilmilage() + "'" +
            ", nextmilage='" + getNextmilage() + "'" +
            ", serviceperiod=" + getServiceperiod() +
            "}";
    }
}
