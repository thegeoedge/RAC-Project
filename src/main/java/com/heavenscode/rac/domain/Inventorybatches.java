package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Inventorybatches.
 */
@Entity
@Table(name = "inventorybatches")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Inventorybatches implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "itemid")
    private Integer itemid;

    @Column(name = "code")
    private String code;

    @Column(name = "txdate")
    private Instant txdate;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "price")
    private Float price;

    @Column(name = "costwithoutvat")
    private Float costwithoutvat;

    @Column(name = "pricewithoutvat")
    private Float pricewithoutvat;

    @Column(name = "notes")
    private String notes;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "lineid")
    private Integer lineid;

    @Column(name = "manufacturedate")
    private Instant manufacturedate;

    @Column(name = "expiredate")
    private Instant expiredate;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "addeddate")
    private Instant addeddate;

    @Column(name = "costtotal")
    private Float costtotal;

    @Column(name = "returnprice")
    private Float returnprice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Inventorybatches id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemid() {
        return this.itemid;
    }

    public Inventorybatches itemid(Integer itemid) {
        this.setItemid(itemid);
        return this;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getCode() {
        return this.code;
    }

    public Inventorybatches code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getTxdate() {
        return this.txdate;
    }

    public Inventorybatches txdate(Instant txdate) {
        this.setTxdate(txdate);
        return this;
    }

    public void setTxdate(Instant txdate) {
        this.txdate = txdate;
    }

    public Float getCost() {
        return this.cost;
    }

    public Inventorybatches cost(Float cost) {
        this.setCost(cost);
        return this;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPrice() {
        return this.price;
    }

    public Inventorybatches price(Float price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCostwithoutvat() {
        return this.costwithoutvat;
    }

    public Inventorybatches costwithoutvat(Float costwithoutvat) {
        this.setCostwithoutvat(costwithoutvat);
        return this;
    }

    public void setCostwithoutvat(Float costwithoutvat) {
        this.costwithoutvat = costwithoutvat;
    }

    public Float getPricewithoutvat() {
        return this.pricewithoutvat;
    }

    public Inventorybatches pricewithoutvat(Float pricewithoutvat) {
        this.setPricewithoutvat(pricewithoutvat);
        return this;
    }

    public void setPricewithoutvat(Float pricewithoutvat) {
        this.pricewithoutvat = pricewithoutvat;
    }

    public String getNotes() {
        return this.notes;
    }

    public Inventorybatches notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Inventorybatches lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Inventorybatches lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Integer getLineid() {
        return this.lineid;
    }

    public Inventorybatches lineid(Integer lineid) {
        this.setLineid(lineid);
        return this;
    }

    public void setLineid(Integer lineid) {
        this.lineid = lineid;
    }

    public Instant getManufacturedate() {
        return this.manufacturedate;
    }

    public Inventorybatches manufacturedate(Instant manufacturedate) {
        this.setManufacturedate(manufacturedate);
        return this;
    }

    public void setManufacturedate(Instant manufacturedate) {
        this.manufacturedate = manufacturedate;
    }

    public Instant getExpiredate() {
        return this.expiredate;
    }

    public Inventorybatches expiredate(Instant expiredate) {
        this.setExpiredate(expiredate);
        return this;
    }

    public void setExpiredate(Instant expiredate) {
        this.expiredate = expiredate;
    }

    public Float getQuantity() {
        return this.quantity;
    }

    public Inventorybatches quantity(Float quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Instant getAddeddate() {
        return this.addeddate;
    }

    public Inventorybatches addeddate(Instant addeddate) {
        this.setAddeddate(addeddate);
        return this;
    }

    public void setAddeddate(Instant addeddate) {
        this.addeddate = addeddate;
    }

    public Float getCosttotal() {
        return this.costtotal;
    }

    public Inventorybatches costtotal(Float costtotal) {
        this.setCosttotal(costtotal);
        return this;
    }

    public void setCosttotal(Float costtotal) {
        this.costtotal = costtotal;
    }

    public Float getReturnprice() {
        return this.returnprice;
    }

    public Inventorybatches returnprice(Float returnprice) {
        this.setReturnprice(returnprice);
        return this;
    }

    public void setReturnprice(Float returnprice) {
        this.returnprice = returnprice;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventorybatches)) {
            return false;
        }
        return getId() != null && getId().equals(((Inventorybatches) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inventorybatches{" +
            "id=" + getId() +
            ", itemid=" + getItemid() +
            ", code='" + getCode() + "'" +
            ", txdate='" + getTxdate() + "'" +
            ", cost=" + getCost() +
            ", price=" + getPrice() +
            ", costwithoutvat=" + getCostwithoutvat() +
            ", pricewithoutvat=" + getPricewithoutvat() +
            ", notes='" + getNotes() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", lineid=" + getLineid() +
            ", manufacturedate='" + getManufacturedate() + "'" +
            ", expiredate='" + getExpiredate() + "'" +
            ", quantity=" + getQuantity() +
            ", addeddate='" + getAddeddate() + "'" +
            ", costtotal=" + getCosttotal() +
            ", returnprice=" + getReturnprice() +
            "}";
    }
}
