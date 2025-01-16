package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autojobsinvoice.
 */
@Entity
@Table(name = "autojobsinvoice")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autojobsinvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "invoicedate")
    private Instant invoicedate;

    @Column(name = "createddate")
    private Instant createddate;

    @Column(name = "jobid")
    private Integer jobid;

    @Column(name = "quoteid")
    private Integer quoteid;

    @Column(name = "orderid")
    private Integer orderid;

    @Column(name = "delieverydate")
    private Instant delieverydate;

    @Column(name = "autojobsrepid")
    private Integer autojobsrepid;

    @Column(name = "autojobsrepname")
    private String autojobsrepname;

    @Column(name = "delieverfrom")
    private String delieverfrom;

    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "customername")
    private String customername;

    @Column(name = "customeraddress")
    private String customeraddress;

    @Column(name = "deliveryaddress")
    private String deliveryaddress;

    @Column(name = "subtotal")
    private Float subtotal;

    @Column(name = "totaltax")
    private Float totaltax;

    @Column(name = "totaldiscount")
    private Float totaldiscount;

    @Column(name = "nettotal")
    private Float nettotal;

    @Column(name = "message")
    private String message;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "paidamount")
    private Float paidamount;

    @Column(name = "amountowing")
    private Float amountowing;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "locationid")
    private Integer locationid;

    @Column(name = "locationcode")
    private String locationcode;

    @Column(name = "referencecode")
    private String referencecode;

    @Column(name = "createdbyid")
    private Integer createdbyid;

    @Column(name = "createdbyname")
    private String createdbyname;

    @Column(name = "autocarecompanyid")
    private Integer autocarecompanyid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autojobsinvoice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Autojobsinvoice code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getInvoicedate() {
        return this.invoicedate;
    }

    public Autojobsinvoice invoicedate(Instant invoicedate) {
        this.setInvoicedate(invoicedate);
        return this;
    }

    public void setInvoicedate(Instant invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Instant getCreateddate() {
        return this.createddate;
    }

    public Autojobsinvoice createddate(Instant createddate) {
        this.setCreateddate(createddate);
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public Autojobsinvoice jobid(Integer jobid) {
        this.setJobid(jobid);
        return this;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public Integer getQuoteid() {
        return this.quoteid;
    }

    public Autojobsinvoice quoteid(Integer quoteid) {
        this.setQuoteid(quoteid);
        return this;
    }

    public void setQuoteid(Integer quoteid) {
        this.quoteid = quoteid;
    }

    public Integer getOrderid() {
        return this.orderid;
    }

    public Autojobsinvoice orderid(Integer orderid) {
        this.setOrderid(orderid);
        return this;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Instant getDelieverydate() {
        return this.delieverydate;
    }

    public Autojobsinvoice delieverydate(Instant delieverydate) {
        this.setDelieverydate(delieverydate);
        return this;
    }

    public void setDelieverydate(Instant delieverydate) {
        this.delieverydate = delieverydate;
    }

    public Integer getAutojobsrepid() {
        return this.autojobsrepid;
    }

    public Autojobsinvoice autojobsrepid(Integer autojobsrepid) {
        this.setAutojobsrepid(autojobsrepid);
        return this;
    }

    public void setAutojobsrepid(Integer autojobsrepid) {
        this.autojobsrepid = autojobsrepid;
    }

    public String getAutojobsrepname() {
        return this.autojobsrepname;
    }

    public Autojobsinvoice autojobsrepname(String autojobsrepname) {
        this.setAutojobsrepname(autojobsrepname);
        return this;
    }

    public void setAutojobsrepname(String autojobsrepname) {
        this.autojobsrepname = autojobsrepname;
    }

    public String getDelieverfrom() {
        return this.delieverfrom;
    }

    public Autojobsinvoice delieverfrom(String delieverfrom) {
        this.setDelieverfrom(delieverfrom);
        return this;
    }

    public void setDelieverfrom(String delieverfrom) {
        this.delieverfrom = delieverfrom;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Autojobsinvoice customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Autojobsinvoice customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddress() {
        return this.customeraddress;
    }

    public Autojobsinvoice customeraddress(String customeraddress) {
        this.setCustomeraddress(customeraddress);
        return this;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getDeliveryaddress() {
        return this.deliveryaddress;
    }

    public Autojobsinvoice deliveryaddress(String deliveryaddress) {
        this.setDeliveryaddress(deliveryaddress);
        return this;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public Float getSubtotal() {
        return this.subtotal;
    }

    public Autojobsinvoice subtotal(Float subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotaltax() {
        return this.totaltax;
    }

    public Autojobsinvoice totaltax(Float totaltax) {
        this.setTotaltax(totaltax);
        return this;
    }

    public void setTotaltax(Float totaltax) {
        this.totaltax = totaltax;
    }

    public Float getTotaldiscount() {
        return this.totaldiscount;
    }

    public Autojobsinvoice totaldiscount(Float totaldiscount) {
        this.setTotaldiscount(totaldiscount);
        return this;
    }

    public void setTotaldiscount(Float totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public Float getNettotal() {
        return this.nettotal;
    }

    public Autojobsinvoice nettotal(Float nettotal) {
        this.setNettotal(nettotal);
        return this;
    }

    public void setNettotal(Float nettotal) {
        this.nettotal = nettotal;
    }

    public String getMessage() {
        return this.message;
    }

    public Autojobsinvoice message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autojobsinvoice lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autojobsinvoice lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Float getPaidamount() {
        return this.paidamount;
    }

    public Autojobsinvoice paidamount(Float paidamount) {
        this.setPaidamount(paidamount);
        return this;
    }

    public void setPaidamount(Float paidamount) {
        this.paidamount = paidamount;
    }

    public Float getAmountowing() {
        return this.amountowing;
    }

    public Autojobsinvoice amountowing(Float amountowing) {
        this.setAmountowing(amountowing);
        return this;
    }

    public void setAmountowing(Float amountowing) {
        this.amountowing = amountowing;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Autojobsinvoice isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Integer getLocationid() {
        return this.locationid;
    }

    public Autojobsinvoice locationid(Integer locationid) {
        this.setLocationid(locationid);
        return this;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getLocationcode() {
        return this.locationcode;
    }

    public Autojobsinvoice locationcode(String locationcode) {
        this.setLocationcode(locationcode);
        return this;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public String getReferencecode() {
        return this.referencecode;
    }

    public Autojobsinvoice referencecode(String referencecode) {
        this.setReferencecode(referencecode);
        return this;
    }

    public void setReferencecode(String referencecode) {
        this.referencecode = referencecode;
    }

    public Integer getCreatedbyid() {
        return this.createdbyid;
    }

    public Autojobsinvoice createdbyid(Integer createdbyid) {
        this.setCreatedbyid(createdbyid);
        return this;
    }

    public void setCreatedbyid(Integer createdbyid) {
        this.createdbyid = createdbyid;
    }

    public String getCreatedbyname() {
        return this.createdbyname;
    }

    public Autojobsinvoice createdbyname(String createdbyname) {
        this.setCreatedbyname(createdbyname);
        return this;
    }

    public void setCreatedbyname(String createdbyname) {
        this.createdbyname = createdbyname;
    }

    public Integer getAutocarecompanyid() {
        return this.autocarecompanyid;
    }

    public Autojobsinvoice autocarecompanyid(Integer autocarecompanyid) {
        this.setAutocarecompanyid(autocarecompanyid);
        return this;
    }

    public void setAutocarecompanyid(Integer autocarecompanyid) {
        this.autocarecompanyid = autocarecompanyid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autojobsinvoice)) {
            return false;
        }
        return getId() != null && getId().equals(((Autojobsinvoice) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autojobsinvoice{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", invoicedate='" + getInvoicedate() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", jobid=" + getJobid() +
            ", quoteid=" + getQuoteid() +
            ", orderid=" + getOrderid() +
            ", delieverydate='" + getDelieverydate() + "'" +
            ", autojobsrepid=" + getAutojobsrepid() +
            ", autojobsrepname='" + getAutojobsrepname() + "'" +
            ", delieverfrom='" + getDelieverfrom() + "'" +
            ", customerid=" + getCustomerid() +
            ", customername='" + getCustomername() + "'" +
            ", customeraddress='" + getCustomeraddress() + "'" +
            ", deliveryaddress='" + getDeliveryaddress() + "'" +
            ", subtotal=" + getSubtotal() +
            ", totaltax=" + getTotaltax() +
            ", totaldiscount=" + getTotaldiscount() +
            ", nettotal=" + getNettotal() +
            ", message='" + getMessage() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", paidamount=" + getPaidamount() +
            ", amountowing=" + getAmountowing() +
            ", isactive='" + getIsactive() + "'" +
            ", locationid=" + getLocationid() +
            ", locationcode='" + getLocationcode() + "'" +
            ", referencecode='" + getReferencecode() + "'" +
            ", createdbyid=" + getCreatedbyid() +
            ", createdbyname='" + getCreatedbyname() + "'" +
            ", autocarecompanyid=" + getAutocarecompanyid() +
            "}";
    }
}
