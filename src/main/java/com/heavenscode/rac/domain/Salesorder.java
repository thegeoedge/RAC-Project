package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Salesorder.
 */
@Entity
@Table(name = "salesorder")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Salesorder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "orderdate")
    private Instant orderdate;

    @Column(name = "createddate")
    private Instant createddate;

    @Column(name = "quoteid")
    private Integer quoteid;

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

    @Column(name = "isinvoiced")
    private Boolean isinvoiced;

    @Column(name = "refinvoiceid")
    private Integer refinvoiceid;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "isfixed")
    private Boolean isfixed;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "advancepayment")
    private Float advancepayment;

    @Column(name = "advancepaymentreturnamount")
    private Float advancepaymentreturnamount;

    @Column(name = "advancepaymentreturndate")
    private Instant advancepaymentreturndate;

    @Column(name = "advancepaymentby")
    private Integer advancepaymentby;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Salesorder id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Salesorder code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getOrderdate() {
        return this.orderdate;
    }

    public Salesorder orderdate(Instant orderdate) {
        this.setOrderdate(orderdate);
        return this;
    }

    public void setOrderdate(Instant orderdate) {
        this.orderdate = orderdate;
    }

    public Instant getCreateddate() {
        return this.createddate;
    }

    public Salesorder createddate(Instant createddate) {
        this.setCreateddate(createddate);
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public Integer getQuoteid() {
        return this.quoteid;
    }

    public Salesorder quoteid(Integer quoteid) {
        this.setQuoteid(quoteid);
        return this;
    }

    public void setQuoteid(Integer quoteid) {
        this.quoteid = quoteid;
    }

    public Integer getSalesrepid() {
        return this.salesrepid;
    }

    public Salesorder salesrepid(Integer salesrepid) {
        this.setSalesrepid(salesrepid);
        return this;
    }

    public void setSalesrepid(Integer salesrepid) {
        this.salesrepid = salesrepid;
    }

    public String getSalesrepname() {
        return this.salesrepname;
    }

    public Salesorder salesrepname(String salesrepname) {
        this.setSalesrepname(salesrepname);
        return this;
    }

    public void setSalesrepname(String salesrepname) {
        this.salesrepname = salesrepname;
    }

    public String getDelieverfrom() {
        return this.delieverfrom;
    }

    public Salesorder delieverfrom(String delieverfrom) {
        this.setDelieverfrom(delieverfrom);
        return this;
    }

    public void setDelieverfrom(String delieverfrom) {
        this.delieverfrom = delieverfrom;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Salesorder customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Salesorder customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomeraddress() {
        return this.customeraddress;
    }

    public Salesorder customeraddress(String customeraddress) {
        this.setCustomeraddress(customeraddress);
        return this;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getDeliveryaddress() {
        return this.deliveryaddress;
    }

    public Salesorder deliveryaddress(String deliveryaddress) {
        this.setDeliveryaddress(deliveryaddress);
        return this;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public Float getSubtotal() {
        return this.subtotal;
    }

    public Salesorder subtotal(Float subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotaltax() {
        return this.totaltax;
    }

    public Salesorder totaltax(Float totaltax) {
        this.setTotaltax(totaltax);
        return this;
    }

    public void setTotaltax(Float totaltax) {
        this.totaltax = totaltax;
    }

    public Float getTotaldiscount() {
        return this.totaldiscount;
    }

    public Salesorder totaldiscount(Float totaldiscount) {
        this.setTotaldiscount(totaldiscount);
        return this;
    }

    public void setTotaldiscount(Float totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    public Float getNettotal() {
        return this.nettotal;
    }

    public Salesorder nettotal(Float nettotal) {
        this.setNettotal(nettotal);
        return this;
    }

    public void setNettotal(Float nettotal) {
        this.nettotal = nettotal;
    }

    public String getMessage() {
        return this.message;
    }

    public Salesorder message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsinvoiced() {
        return this.isinvoiced;
    }

    public Salesorder isinvoiced(Boolean isinvoiced) {
        this.setIsinvoiced(isinvoiced);
        return this;
    }

    public void setIsinvoiced(Boolean isinvoiced) {
        this.isinvoiced = isinvoiced;
    }

    public Integer getRefinvoiceid() {
        return this.refinvoiceid;
    }

    public Salesorder refinvoiceid(Integer refinvoiceid) {
        this.setRefinvoiceid(refinvoiceid);
        return this;
    }

    public void setRefinvoiceid(Integer refinvoiceid) {
        this.refinvoiceid = refinvoiceid;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Salesorder lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Salesorder lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Boolean getIsfixed() {
        return this.isfixed;
    }

    public Salesorder isfixed(Boolean isfixed) {
        this.setIsfixed(isfixed);
        return this;
    }

    public void setIsfixed(Boolean isfixed) {
        this.isfixed = isfixed;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Salesorder isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Float getAdvancepayment() {
        return this.advancepayment;
    }

    public Salesorder advancepayment(Float advancepayment) {
        this.setAdvancepayment(advancepayment);
        return this;
    }

    public void setAdvancepayment(Float advancepayment) {
        this.advancepayment = advancepayment;
    }

    public Float getAdvancepaymentreturnamount() {
        return this.advancepaymentreturnamount;
    }

    public Salesorder advancepaymentreturnamount(Float advancepaymentreturnamount) {
        this.setAdvancepaymentreturnamount(advancepaymentreturnamount);
        return this;
    }

    public void setAdvancepaymentreturnamount(Float advancepaymentreturnamount) {
        this.advancepaymentreturnamount = advancepaymentreturnamount;
    }

    public Instant getAdvancepaymentreturndate() {
        return this.advancepaymentreturndate;
    }

    public Salesorder advancepaymentreturndate(Instant advancepaymentreturndate) {
        this.setAdvancepaymentreturndate(advancepaymentreturndate);
        return this;
    }

    public void setAdvancepaymentreturndate(Instant advancepaymentreturndate) {
        this.advancepaymentreturndate = advancepaymentreturndate;
    }

    public Integer getAdvancepaymentby() {
        return this.advancepaymentby;
    }

    public Salesorder advancepaymentby(Integer advancepaymentby) {
        this.setAdvancepaymentby(advancepaymentby);
        return this;
    }

    public void setAdvancepaymentby(Integer advancepaymentby) {
        this.advancepaymentby = advancepaymentby;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salesorder)) {
            return false;
        }
        return getId() != null && getId().equals(((Salesorder) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Salesorder{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", orderdate='" + getOrderdate() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", quoteid=" + getQuoteid() +
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
            ", isinvoiced='" + getIsinvoiced() + "'" +
            ", refinvoiceid=" + getRefinvoiceid() +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", isfixed='" + getIsfixed() + "'" +
            ", isactive='" + getIsactive() + "'" +
            ", advancepayment=" + getAdvancepayment() +
            ", advancepaymentreturnamount=" + getAdvancepaymentreturnamount() +
            ", advancepaymentreturndate='" + getAdvancepaymentreturndate() + "'" +
            ", advancepaymentby=" + getAdvancepaymentby() +
            "}";
    }
}
