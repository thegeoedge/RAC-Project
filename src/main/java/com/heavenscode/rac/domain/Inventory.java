package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Inventory.
 */
@Entity
@Table(name = "inventory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "partnumber")
    private String partnumber;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private Integer type;

    @Column(name = "classification1")
    private String classification1;

    @Column(name = "classification2")
    private String classification2;

    @Column(name = "classification3")
    private String classification3;

    @Column(name = "classification4")
    private String classification4;

    @Column(name = "classification5")
    private String classification5;

    @Column(name = "unitofmeasurement")
    private String unitofmeasurement;

    @Column(name = "decimalplaces")
    private Integer decimalplaces;

    @Column(name = "isassemblyunit")
    private Boolean isassemblyunit;

    @Column(name = "assemblyunitof")
    private Integer assemblyunitof;

    @Column(name = "reorderlevel")
    private Float reorderlevel;

    @Column(name = "lastcost")
    private Float lastcost;

    @Column(name = "lastsellingprice")
    private Float lastsellingprice;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "availablequantity")
    private Float availablequantity;

    @Column(name = "hasbatches")
    private Boolean hasbatches;

    @Column(name = "itemspecfilepath")
    private String itemspecfilepath;

    @Column(name = "itemimagepath")
    private String itemimagepath;

    @Column(name = "returnprice")
    private Float returnprice;

    @Column(name = "activeitem")
    private Boolean activeitem;

    @Column(name = "minstock")
    private Float minstock;

    @Column(name = "maxstock")
    private Float maxstock;

    @Column(name = "dailyaverage")
    private Float dailyaverage;

    @Column(name = "bufferlevel")
    private Float bufferlevel;

    @Column(name = "leadtime")
    private Float leadtime;

    @Column(name = "buffertime")
    private Float buffertime;

    @Column(name = "saftydays")
    private Float saftydays;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "accountid")
    private Integer accountid;

    @Column(name = "casepackqty")
    private Integer casepackqty;

    @Column(name = "isregistered")
    private Boolean isregistered;

    @Column(name = "defaultstocklocationid")
    private Integer defaultstocklocationid;

    @Column(name = "rackno")
    private String rackno;

    @Lob
    @Column(name = "barcodeimage")
    private byte[] barcodeimage;

    // @Column(name = "barcodeimage_content_type")
    // private String barcodeimageContentType;

    @Column(name = "commissionempid")
    private Integer commissionempid;

    @Column(name = "checktypeid")
    private Integer checktypeid;

    @Column(name = "checktype")
    private String checktype;

    @Column(name = "reorderqty")
    private Float reorderqty;

    @Column(name = "notininvoice")
    private Boolean notininvoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Inventory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Inventory code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPartnumber() {
        return this.partnumber;
    }

    public Inventory partnumber(String partnumber) {
        this.setPartnumber(partnumber);
        return this;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getName() {
        return this.name;
    }

    public Inventory name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Inventory description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return this.type;
    }

    public Inventory type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getClassification1() {
        return this.classification1;
    }

    public Inventory classification1(String classification1) {
        this.setClassification1(classification1);
        return this;
    }

    public void setClassification1(String classification1) {
        this.classification1 = classification1;
    }

    public String getClassification2() {
        return this.classification2;
    }

    public Inventory classification2(String classification2) {
        this.setClassification2(classification2);
        return this;
    }

    public void setClassification2(String classification2) {
        this.classification2 = classification2;
    }

    public String getClassification3() {
        return this.classification3;
    }

    public Inventory classification3(String classification3) {
        this.setClassification3(classification3);
        return this;
    }

    public void setClassification3(String classification3) {
        this.classification3 = classification3;
    }

    public String getClassification4() {
        return this.classification4;
    }

    public Inventory classification4(String classification4) {
        this.setClassification4(classification4);
        return this;
    }

    public void setClassification4(String classification4) {
        this.classification4 = classification4;
    }

    public String getClassification5() {
        return this.classification5;
    }

    public Inventory classification5(String classification5) {
        this.setClassification5(classification5);
        return this;
    }

    public void setClassification5(String classification5) {
        this.classification5 = classification5;
    }

    public String getUnitofmeasurement() {
        return this.unitofmeasurement;
    }

    public Inventory unitofmeasurement(String unitofmeasurement) {
        this.setUnitofmeasurement(unitofmeasurement);
        return this;
    }

    public void setUnitofmeasurement(String unitofmeasurement) {
        this.unitofmeasurement = unitofmeasurement;
    }

    public Integer getDecimalplaces() {
        return this.decimalplaces;
    }

    public Inventory decimalplaces(Integer decimalplaces) {
        this.setDecimalplaces(decimalplaces);
        return this;
    }

    public void setDecimalplaces(Integer decimalplaces) {
        this.decimalplaces = decimalplaces;
    }

    public Boolean getIsassemblyunit() {
        return this.isassemblyunit;
    }

    public Inventory isassemblyunit(Boolean isassemblyunit) {
        this.setIsassemblyunit(isassemblyunit);
        return this;
    }

    public void setIsassemblyunit(Boolean isassemblyunit) {
        this.isassemblyunit = isassemblyunit;
    }

    public Integer getAssemblyunitof() {
        return this.assemblyunitof;
    }

    public Inventory assemblyunitof(Integer assemblyunitof) {
        this.setAssemblyunitof(assemblyunitof);
        return this;
    }

    public void setAssemblyunitof(Integer assemblyunitof) {
        this.assemblyunitof = assemblyunitof;
    }

    public Float getReorderlevel() {
        return this.reorderlevel;
    }

    public Inventory reorderlevel(Float reorderlevel) {
        this.setReorderlevel(reorderlevel);
        return this;
    }

    public void setReorderlevel(Float reorderlevel) {
        this.reorderlevel = reorderlevel;
    }

    public Float getLastcost() {
        return this.lastcost;
    }

    public Inventory lastcost(Float lastcost) {
        this.setLastcost(lastcost);
        return this;
    }

    public void setLastcost(Float lastcost) {
        this.lastcost = lastcost;
    }

    public Float getLastsellingprice() {
        return this.lastsellingprice;
    }

    public Inventory lastsellingprice(Float lastsellingprice) {
        this.setLastsellingprice(lastsellingprice);
        return this;
    }

    public void setLastsellingprice(Float lastsellingprice) {
        this.lastsellingprice = lastsellingprice;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Inventory lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Inventory lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public Float getAvailablequantity() {
        return this.availablequantity;
    }

    public Inventory availablequantity(Float availablequantity) {
        this.setAvailablequantity(availablequantity);
        return this;
    }

    public void setAvailablequantity(Float availablequantity) {
        this.availablequantity = availablequantity;
    }

    public Boolean getHasbatches() {
        return this.hasbatches;
    }

    public Inventory hasbatches(Boolean hasbatches) {
        this.setHasbatches(hasbatches);
        return this;
    }

    public void setHasbatches(Boolean hasbatches) {
        this.hasbatches = hasbatches;
    }

    public String getItemspecfilepath() {
        return this.itemspecfilepath;
    }

    public Inventory itemspecfilepath(String itemspecfilepath) {
        this.setItemspecfilepath(itemspecfilepath);
        return this;
    }

    public void setItemspecfilepath(String itemspecfilepath) {
        this.itemspecfilepath = itemspecfilepath;
    }

    public String getItemimagepath() {
        return this.itemimagepath;
    }

    public Inventory itemimagepath(String itemimagepath) {
        this.setItemimagepath(itemimagepath);
        return this;
    }

    public void setItemimagepath(String itemimagepath) {
        this.itemimagepath = itemimagepath;
    }

    public Float getReturnprice() {
        return this.returnprice;
    }

    public Inventory returnprice(Float returnprice) {
        this.setReturnprice(returnprice);
        return this;
    }

    public void setReturnprice(Float returnprice) {
        this.returnprice = returnprice;
    }

    public Boolean getActiveitem() {
        return this.activeitem;
    }

    public Inventory activeitem(Boolean activeitem) {
        this.setActiveitem(activeitem);
        return this;
    }

    public void setActiveitem(Boolean activeitem) {
        this.activeitem = activeitem;
    }

    public Float getMinstock() {
        return this.minstock;
    }

    public Inventory minstock(Float minstock) {
        this.setMinstock(minstock);
        return this;
    }

    public void setMinstock(Float minstock) {
        this.minstock = minstock;
    }

    public Float getMaxstock() {
        return this.maxstock;
    }

    public Inventory maxstock(Float maxstock) {
        this.setMaxstock(maxstock);
        return this;
    }

    public void setMaxstock(Float maxstock) {
        this.maxstock = maxstock;
    }

    public Float getDailyaverage() {
        return this.dailyaverage;
    }

    public Inventory dailyaverage(Float dailyaverage) {
        this.setDailyaverage(dailyaverage);
        return this;
    }

    public void setDailyaverage(Float dailyaverage) {
        this.dailyaverage = dailyaverage;
    }

    public Float getBufferlevel() {
        return this.bufferlevel;
    }

    public Inventory bufferlevel(Float bufferlevel) {
        this.setBufferlevel(bufferlevel);
        return this;
    }

    public void setBufferlevel(Float bufferlevel) {
        this.bufferlevel = bufferlevel;
    }

    public Float getLeadtime() {
        return this.leadtime;
    }

    public Inventory leadtime(Float leadtime) {
        this.setLeadtime(leadtime);
        return this;
    }

    public void setLeadtime(Float leadtime) {
        this.leadtime = leadtime;
    }

    public Float getBuffertime() {
        return this.buffertime;
    }

    public Inventory buffertime(Float buffertime) {
        this.setBuffertime(buffertime);
        return this;
    }

    public void setBuffertime(Float buffertime) {
        this.buffertime = buffertime;
    }

    public Float getSaftydays() {
        return this.saftydays;
    }

    public Inventory saftydays(Float saftydays) {
        this.setSaftydays(saftydays);
        return this;
    }

    public void setSaftydays(Float saftydays) {
        this.saftydays = saftydays;
    }

    public String getAccountcode() {
        return this.accountcode;
    }

    public Inventory accountcode(String accountcode) {
        this.setAccountcode(accountcode);
        return this;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public Integer getAccountid() {
        return this.accountid;
    }

    public Inventory accountid(Integer accountid) {
        this.setAccountid(accountid);
        return this;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Integer getCasepackqty() {
        return this.casepackqty;
    }

    public Inventory casepackqty(Integer casepackqty) {
        this.setCasepackqty(casepackqty);
        return this;
    }

    public void setCasepackqty(Integer casepackqty) {
        this.casepackqty = casepackqty;
    }

    public Boolean getIsregistered() {
        return this.isregistered;
    }

    public Inventory isregistered(Boolean isregistered) {
        this.setIsregistered(isregistered);
        return this;
    }

    public void setIsregistered(Boolean isregistered) {
        this.isregistered = isregistered;
    }

    public Integer getDefaultstocklocationid() {
        return this.defaultstocklocationid;
    }

    public Inventory defaultstocklocationid(Integer defaultstocklocationid) {
        this.setDefaultstocklocationid(defaultstocklocationid);
        return this;
    }

    public void setDefaultstocklocationid(Integer defaultstocklocationid) {
        this.defaultstocklocationid = defaultstocklocationid;
    }

    public String getRackno() {
        return this.rackno;
    }

    public Inventory rackno(String rackno) {
        this.setRackno(rackno);
        return this;
    }

    public void setRackno(String rackno) {
        this.rackno = rackno;
    }

    public byte[] getBarcodeimage() {
        return this.barcodeimage;
    }

    public Inventory barcodeimage(byte[] barcodeimage) {
        this.setBarcodeimage(barcodeimage);
        return this;
    }

    public void setBarcodeimage(byte[] barcodeimage) {
        this.barcodeimage = barcodeimage;
    }

    // public String getBarcodeimageContentType() {
    //     return this.barcodeimageContentType;
    // }

    // public Inventory barcodeimageContentType(String barcodeimageContentType) {
    //     this.barcodeimageContentType = barcodeimageContentType;
    //     return this;
    // }

    // public void setBarcodeimageContentType(String barcodeimageContentType) {
    //     this.barcodeimageContentType = barcodeimageContentType;
    // }

    public Integer getCommissionempid() {
        return this.commissionempid;
    }

    public Inventory commissionempid(Integer commissionempid) {
        this.setCommissionempid(commissionempid);
        return this;
    }

    public void setCommissionempid(Integer commissionempid) {
        this.commissionempid = commissionempid;
    }

    public Integer getChecktypeid() {
        return this.checktypeid;
    }

    public Inventory checktypeid(Integer checktypeid) {
        this.setChecktypeid(checktypeid);
        return this;
    }

    public void setChecktypeid(Integer checktypeid) {
        this.checktypeid = checktypeid;
    }

    public String getChecktype() {
        return this.checktype;
    }

    public Inventory checktype(String checktype) {
        this.setChecktype(checktype);
        return this;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }

    public Float getReorderqty() {
        return this.reorderqty;
    }

    public Inventory reorderqty(Float reorderqty) {
        this.setReorderqty(reorderqty);
        return this;
    }

    public void setReorderqty(Float reorderqty) {
        this.reorderqty = reorderqty;
    }

    public Boolean getNotininvoice() {
        return this.notininvoice;
    }

    public Inventory notininvoice(Boolean notininvoice) {
        this.setNotininvoice(notininvoice);
        return this;
    }

    public void setNotininvoice(Boolean notininvoice) {
        this.notininvoice = notininvoice;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inventory)) {
            return false;
        }
        return getId() != null && getId().equals(((Inventory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inventory{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", partnumber='" + getPartnumber() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type=" + getType() +
            ", classification1='" + getClassification1() + "'" +
            ", classification2='" + getClassification2() + "'" +
            ", classification3='" + getClassification3() + "'" +
            ", classification4='" + getClassification4() + "'" +
            ", classification5='" + getClassification5() + "'" +
            ", unitofmeasurement='" + getUnitofmeasurement() + "'" +
            ", decimalplaces=" + getDecimalplaces() +
            ", isassemblyunit='" + getIsassemblyunit() + "'" +
            ", assemblyunitof=" + getAssemblyunitof() +
            ", reorderlevel=" + getReorderlevel() +
            ", lastcost=" + getLastcost() +
            ", lastsellingprice=" + getLastsellingprice() +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", availablequantity=" + getAvailablequantity() +
            ", hasbatches='" + getHasbatches() + "'" +
            ", itemspecfilepath='" + getItemspecfilepath() + "'" +
            ", itemimagepath='" + getItemimagepath() + "'" +
            ", returnprice=" + getReturnprice() +
            ", activeitem='" + getActiveitem() + "'" +
            ", minstock=" + getMinstock() +
            ", maxstock=" + getMaxstock() +
            ", dailyaverage=" + getDailyaverage() +
            ", bufferlevel=" + getBufferlevel() +
            ", leadtime=" + getLeadtime() +
            ", buffertime=" + getBuffertime() +
            ", saftydays=" + getSaftydays() +
            ", accountcode='" + getAccountcode() + "'" +
            ", accountid=" + getAccountid() +
            ", casepackqty=" + getCasepackqty() +
            ", isregistered='" + getIsregistered() + "'" +
            ", defaultstocklocationid=" + getDefaultstocklocationid() +
            ", rackno='" + getRackno() + "'" +
            ", barcodeimage='" + getBarcodeimage() + "'" +
            // ", barcodeimageContentType='" + getBarcodeimageContentType() + "'" +
            ", commissionempid=" + getCommissionempid() +
            ", checktypeid=" + getChecktypeid() +
            ", checktype='" + getChecktype() + "'" +
            ", reorderqty=" + getReorderqty() +
            ", notininvoice='" + getNotininvoice() + "'" +
            "}";
    }
}
