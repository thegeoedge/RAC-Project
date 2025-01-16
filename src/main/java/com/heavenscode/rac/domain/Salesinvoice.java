package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Salesinvoice.
 */
@Entity
@Table(name = "salesinvoice")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Salesinvoice implements Serializable {

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

    @Column(name = "quoteid")
    private Integer quoteid;

    @Column(name = "orderid")
    private Integer orderid;

    @Column(name = "delieverydate")
    private Instant delieverydate;

    @Column(name = "salesrepid")
    private Integer salesrepid;

    @Column(name = "salesrepname")
    private String salesrepname;

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

    @Column(name = "autocarecharges")
    private Float autocarecharges;

    @Column(name = "autocarejobid")
    private Integer autocarejobid;

    @Column(name = "vehicleno")
    private String vehicleno;

    @Column(name = "nextmeter")
    private String nextmeter;

    @Column(name = "currentmeter")
    private String currentmeter;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "hasdummybill")
    private Boolean hasdummybill;

    @Column(name = "dummybillid")
    private Integer dummybillid;

    @Column(name = "dummybillamount")
    private Float dummybillamount;

    @Column(name = "dummycommision")
    private Float dummycommision;

    @Column(name = "isserviceinvoice")
    private Boolean isserviceinvoice;

    @Column(name = "nbtamount")
    private Float nbtamount;

    @Column(name = "vatamount")
    private Float vatamount;

    @Column(name = "autocarecompanyid")
    private Integer autocarecompanyid;

    @Column(name = "iscompanyinvoice")
    private Boolean iscompanyinvoice;

    @Column(name = "invcanceldate")
    private Instant invcanceldate;

    @Column(name = "invcancelby")
    private Integer invcancelby;

    @Column(name = "isvatinvoice")
    private Boolean isvatinvoice;

    @Column(name = "paymenttype")
    private String paymenttype;

    @Column(name = "pendingamount")
    private Float pendingamount;

    @Column(name = "advancepayment")
    private Float advancepayment;

    @Column(name = "discountcode")
    private String discountcode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Salesinvoice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Salesinvoice code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getInvoicedate() {
        return this.invoicedate;
    }

    public Salesinvoice invoicedate(Instant invoicedate) {
        this.setInvoicedate(invoicedate);
        return this;
    }

    public void setInvoicedate(Instant invoicedate) {
        this.invoicedate = invoicedate;
    }

    public Instant getCreateddate() {
        return this.createddate;
    }

    public Salesinvoice createddate(Instant createddate) {
        this.setCreateddate(createddate);
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public Integer getQuoteid() {
        return this.quoteid;
    }

    public Salesinvoice quoteid(Integer quoteid) {
        this.setQuoteid(quoteid);
        return this;
    }

    public void setQuoteid(Integer quoteid) {
        this.quoteid = quoteid;
    }

    public Integer getOrderid() {
        return this.orderid;
    }

    public Salesinvoice orderid(Integer orderid) {
        this.setOrderid(orderid);
        return this;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Instant getDelieverydate() {
        return this.delieverydate;
    }

    public Salesinvoice delieverydate(Instant delieverydate) {
        this.setDelieverydate(delieverydate);
        return this;
    }

    public void setDelieverydate(Instant delieverydate) {
        this.delieverydate = delieverydate;
    }

    public Integer getSalesrepid() {
        return this.salesrepid;
    }

    public Salesinvoice salesrepid(Integer salesrepid) {
        this.setSalesrepid(salesrepid);
        return this;
    }

    public void setSalesrepid(Integer salesrepid) {
        this.salesrepid = salesrepid;
    }

    public String getSalesrepname() {
        return this.salesrepname;
    }

    public Salesinvoice salesrepname(String salesrepname) {
        this.setSalesrepname(salesrepname);
        return this;
    }

    public void setSalesrepname(String salesrepname) {
        this.salesrepname = salesrepname;
    }

    public String getDelieverfrom() {
        return this.delieverfrom;
    }

    public Salesinvoice delieverfrom(String delieverfrom) {
        this.setDelieverfrom(delieverfrom);
        return this;
    }

    public void setDelieverfrom(String delieverfrom) {
        this.delieverfrom = delieverfrom;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Salesinvoice customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Salesinvoice customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddress() {
        return this.customeraddress;
    }

    public Salesinvoice customeraddress(String customeraddress) {
        this.setCustomeraddress(customeraddress);
        return this;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getDeliveryaddress() {
        return this.deliveryaddress;
    }

    public Salesinvoice deliveryaddress(String deliveryaddress) {
        this.setDeliveryaddress(deliveryaddress);
        return this;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public Float getSubtotal() {
        return this.subtotal;
    }

    public Salesinvoice subtotal(Float subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotaltax() {
        return this.totaltax;
    }

    public Salesinvoice totaltax(Float totaltax) {
        this.setTotaltax(totaltax);
        return this;
    }

    public void setTotaltax(Float totaltax) {
        this.totaltax = totaltax;
    }

    public Float getTotaldiscount() {
        return this.totaldiscount;
    }

    public Salesinvoice totaldiscount(Float totaldiscount) {
        this.setTotaldiscount(totaldiscount);
        return this;
    }

    public void setTotaldiscount(Float totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public Float getNettotal() {
        return this.nettotal;
    }

    public Salesinvoice nettotal(Float nettotal) {
        this.setNettotal(nettotal);
        return this;
    }

    public void setNettotal(Float nettotal) {
        this.nettotal = nettotal;
    }

    public String getMessage() {
        return this.message;
    }

    public Salesinvoice message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Salesinvoice lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Salesinvoice lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Float getPaidamount() {
        return this.paidamount;
    }

    public Salesinvoice paidamount(Float paidamount) {
        this.setPaidamount(paidamount);
        return this;
    }

    public void setPaidamount(Float paidamount) {
        this.paidamount = paidamount;
    }

    public Float getAmountowing() {
        return this.amountowing;
    }

    public Salesinvoice amountowing(Float amountowing) {
        this.setAmountowing(amountowing);
        return this;
    }

    public void setAmountowing(Float amountowing) {
        this.amountowing = amountowing;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Salesinvoice isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Integer getLocationid() {
        return this.locationid;
    }

    public Salesinvoice locationid(Integer locationid) {
        this.setLocationid(locationid);
        return this;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getLocationcode() {
        return this.locationcode;
    }

    public Salesinvoice locationcode(String locationcode) {
        this.setLocationcode(locationcode);
        return this;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public String getReferencecode() {
        return this.referencecode;
    }

    public Salesinvoice referencecode(String referencecode) {
        this.setReferencecode(referencecode);
        return this;
    }

    public void setReferencecode(String referencecode) {
        this.referencecode = referencecode;
    }

    public Integer getCreatedbyid() {
        return this.createdbyid;
    }

    public Salesinvoice createdbyid(Integer createdbyid) {
        this.setCreatedbyid(createdbyid);
        return this;
    }

    public void setCreatedbyid(Integer createdbyid) {
        this.createdbyid = createdbyid;
    }

    public String getCreatedbyname() {
        return this.createdbyname;
    }

    public Salesinvoice createdbyname(String createdbyname) {
        this.setCreatedbyname(createdbyname);
        return this;
    }

    public void setCreatedbyname(String createdbyname) {
        this.createdbyname = createdbyname;
    }

    public Float getAutocarecharges() {
        return this.autocarecharges;
    }

    public Salesinvoice autocarecharges(Float autocarecharges) {
        this.setAutocarecharges(autocarecharges);
        return this;
    }

    public void setAutocarecharges(Float autocarecharges) {
        this.autocarecharges = autocarecharges;
    }

    public Integer getAutocarejobid() {
        return this.autocarejobid;
    }

    public Salesinvoice autocarejobid(Integer autocarejobid) {
        this.setAutocarejobid(autocarejobid);
        return this;
    }

    public void setAutocarejobid(Integer autocarejobid) {
        this.autocarejobid = autocarejobid;
    }

    public String getVehicleno() {
        return this.vehicleno;
    }

    public Salesinvoice vehicleno(String vehicleno) {
        this.setVehicleno(vehicleno);
        return this;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getNextmeter() {
        return this.nextmeter;
    }

    public Salesinvoice nextmeter(String nextmeter) {
        this.setNextmeter(nextmeter);
        return this;
    }

    public void setNextmeter(String nextmeter) {
        this.nextmeter = nextmeter;
    }

    public String getCurrentmeter() {
        return this.currentmeter;
    }

    public Salesinvoice currentmeter(String currentmeter) {
        this.setCurrentmeter(currentmeter);
        return this;
    }

    public void setCurrentmeter(String currentmeter) {
        this.currentmeter = currentmeter;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Salesinvoice remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getHasdummybill() {
        return this.hasdummybill;
    }

    public Salesinvoice hasdummybill(Boolean hasdummybill) {
        this.setHasdummybill(hasdummybill);
        return this;
    }

    public void setHasdummybill(Boolean hasdummybill) {
        this.hasdummybill = hasdummybill;
    }

    public Integer getDummybillid() {
        return this.dummybillid;
    }

    public Salesinvoice dummybillid(Integer dummybillid) {
        this.setDummybillid(dummybillid);
        return this;
    }

    public void setDummybillid(Integer dummybillid) {
        this.dummybillid = dummybillid;
    }

    public Float getDummybillamount() {
        return this.dummybillamount;
    }

    public Salesinvoice dummybillamount(Float dummybillamount) {
        this.setDummybillamount(dummybillamount);
        return this;
    }

    public void setDummybillamount(Float dummybillamount) {
        this.dummybillamount = dummybillamount;
    }

    public Float getDummycommision() {
        return this.dummycommision;
    }

    public Salesinvoice dummycommision(Float dummycommision) {
        this.setDummycommision(dummycommision);
        return this;
    }

    public void setDummycommision(Float dummycommision) {
        this.dummycommision = dummycommision;
    }

    public Boolean getIsserviceinvoice() {
        return this.isserviceinvoice;
    }

    public Salesinvoice isserviceinvoice(Boolean isserviceinvoice) {
        this.setIsserviceinvoice(isserviceinvoice);
        return this;
    }

    public void setIsserviceinvoice(Boolean isserviceinvoice) {
        this.isserviceinvoice = isserviceinvoice;
    }

    public Float getNbtamount() {
        return this.nbtamount;
    }

    public Salesinvoice nbtamount(Float nbtamount) {
        this.setNbtamount(nbtamount);
        return this;
    }

    public void setNbtamount(Float nbtamount) {
        this.nbtamount = nbtamount;
    }

    public Float getVatamount() {
        return this.vatamount;
    }

    public Salesinvoice vatamount(Float vatamount) {
        this.setVatamount(vatamount);
        return this;
    }

    public void setVatamount(Float vatamount) {
        this.vatamount = vatamount;
    }

    public Integer getAutocarecompanyid() {
        return this.autocarecompanyid;
    }

    public Salesinvoice autocarecompanyid(Integer autocarecompanyid) {
        this.setAutocarecompanyid(autocarecompanyid);
        return this;
    }

    public void setAutocarecompanyid(Integer autocarecompanyid) {
        this.autocarecompanyid = autocarecompanyid;
    }

    public Boolean getIscompanyinvoice() {
        return this.iscompanyinvoice;
    }

    public Salesinvoice iscompanyinvoice(Boolean iscompanyinvoice) {
        this.setIscompanyinvoice(iscompanyinvoice);
        return this;
    }

    public void setIscompanyinvoice(Boolean iscompanyinvoice) {
        this.iscompanyinvoice = iscompanyinvoice;
    }

    public Instant getInvcanceldate() {
        return this.invcanceldate;
    }

    public Salesinvoice invcanceldate(Instant invcanceldate) {
        this.setInvcanceldate(invcanceldate);
        return this;
    }

    public void setInvcanceldate(Instant invcanceldate) {
        this.invcanceldate = invcanceldate;
    }

    public Integer getInvcancelby() {
        return this.invcancelby;
    }

    public Salesinvoice invcancelby(Integer invcancelby) {
        this.setInvcancelby(invcancelby);
        return this;
    }

    public void setInvcancelby(Integer invcancelby) {
        this.invcancelby = invcancelby;
    }

    public Boolean getIsvatinvoice() {
        return this.isvatinvoice;
    }

    public Salesinvoice isvatinvoice(Boolean isvatinvoice) {
        this.setIsvatinvoice(isvatinvoice);
        return this;
    }

    public void setIsvatinvoice(Boolean isvatinvoice) {
        this.isvatinvoice = isvatinvoice;
    }

    public String getPaymenttype() {
        return this.paymenttype;
    }

    public Salesinvoice paymenttype(String paymenttype) {
        this.setPaymenttype(paymenttype);
        return this;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Float getPendingamount() {
        return this.pendingamount;
    }

    public Salesinvoice pendingamount(Float pendingamount) {
        this.setPendingamount(pendingamount);
        return this;
    }

    public void setPendingamount(Float pendingamount) {
        this.pendingamount = pendingamount;
    }

    public Float getAdvancepayment() {
        return this.advancepayment;
    }

    public Salesinvoice advancepayment(Float advancepayment) {
        this.setAdvancepayment(advancepayment);
        return this;
    }

    public void setAdvancepayment(Float advancepayment) {
        this.advancepayment = advancepayment;
    }

    public String getDiscountcode() {
        return this.discountcode;
    }

    public Salesinvoice discountcode(String discountcode) {
        this.setDiscountcode(discountcode);
        return this;
    }

    public void setDiscountcode(String discountcode) {
        this.discountcode = discountcode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salesinvoice)) {
            return false;
        }
        return getId() != null && getId().equals(((Salesinvoice) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Salesinvoice{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", invoicedate='" + getInvoicedate() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", quoteid=" + getQuoteid() +
            ", orderid=" + getOrderid() +
            ", delieverydate='" + getDelieverydate() + "'" +
            ", salesrepid=" + getSalesrepid() +
            ", salesrepname='" + getSalesrepname() + "'" +
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
            ", autocarecharges=" + getAutocarecharges() +
            ", autocarejobid=" + getAutocarejobid() +
            ", vehicleno='" + getVehicleno() + "'" +
            ", nextmeter='" + getNextmeter() + "'" +
            ", currentmeter='" + getCurrentmeter() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", hasdummybill='" + getHasdummybill() + "'" +
            ", dummybillid=" + getDummybillid() +
            ", dummybillamount=" + getDummybillamount() +
            ", dummycommision=" + getDummycommision() +
            ", isserviceinvoice='" + getIsserviceinvoice() + "'" +
            ", nbtamount=" + getNbtamount() +
            ", vatamount=" + getVatamount() +
            ", autocarecompanyid=" + getAutocarecompanyid() +
            ", iscompanyinvoice='" + getIscompanyinvoice() + "'" +
            ", invcanceldate='" + getInvcanceldate() + "'" +
            ", invcancelby=" + getInvcancelby() +
            ", isvatinvoice='" + getIsvatinvoice() + "'" +
            ", paymenttype='" + getPaymenttype() + "'" +
            ", pendingamount=" + getPendingamount() +
            ", advancepayment=" + getAdvancepayment() +
            ", discountcode='" + getDiscountcode() + "'" +
            "}";
    }
}
