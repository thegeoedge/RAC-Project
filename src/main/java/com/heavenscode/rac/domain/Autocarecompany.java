package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autocarecompany.
 */
@Entity
@Table(name = "autocarecompany")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocarecompany implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "servicephone")
    private String servicephone;

    @Column(name = "sparepartphone")
    private String sparepartphone;

    @Column(name = "bodypaint")
    private String bodypaint;

    @Column(name = "generalphone")
    private String generalphone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "vatregnumber")
    private String vatregnumber;

    @Column(name = "tinnumber")
    private String tinnumber;

    @Column(name = "accountcode")
    private String accountcode;

    @Column(name = "accountid")
    private Integer accountid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocarecompany id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Autocarecompany name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public Autocarecompany address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServicephone() {
        return this.servicephone;
    }

    public Autocarecompany servicephone(String servicephone) {
        this.setServicephone(servicephone);
        return this;
    }

    public void setServicephone(String servicephone) {
        this.servicephone = servicephone;
    }

    public String getSparepartphone() {
        return this.sparepartphone;
    }

    public Autocarecompany sparepartphone(String sparepartphone) {
        this.setSparepartphone(sparepartphone);
        return this;
    }

    public void setSparepartphone(String sparepartphone) {
        this.sparepartphone = sparepartphone;
    }

    public String getBodypaint() {
        return this.bodypaint;
    }

    public Autocarecompany bodypaint(String bodypaint) {
        this.setBodypaint(bodypaint);
        return this;
    }

    public void setBodypaint(String bodypaint) {
        this.bodypaint = bodypaint;
    }

    public String getGeneralphone() {
        return this.generalphone;
    }

    public Autocarecompany generalphone(String generalphone) {
        this.setGeneralphone(generalphone);
        return this;
    }

    public void setGeneralphone(String generalphone) {
        this.generalphone = generalphone;
    }

    public String getFax() {
        return this.fax;
    }

    public Autocarecompany fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public Autocarecompany email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return this.description;
    }

    public Autocarecompany description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autocarecompany lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autocarecompany lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public String getVatregnumber() {
        return this.vatregnumber;
    }

    public Autocarecompany vatregnumber(String vatregnumber) {
        this.setVatregnumber(vatregnumber);
        return this;
    }

    public void setVatregnumber(String vatregnumber) {
        this.vatregnumber = vatregnumber;
    }

    public String getTinnumber() {
        return this.tinnumber;
    }

    public Autocarecompany tinnumber(String tinnumber) {
        this.setTinnumber(tinnumber);
        return this;
    }

    public void setTinnumber(String tinnumber) {
        this.tinnumber = tinnumber;
    }

    public String getAccountcode() {
        return this.accountcode;
    }

    public Autocarecompany accountcode(String accountcode) {
        this.setAccountcode(accountcode);
        return this;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public Integer getAccountid() {
        return this.accountid;
    }

    public Autocarecompany accountid(Integer accountid) {
        this.setAccountid(accountid);
        return this;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocarecompany)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocarecompany) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocarecompany{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", servicephone='" + getServicephone() + "'" +
            ", sparepartphone='" + getSparepartphone() + "'" +
            ", bodypaint='" + getBodypaint() + "'" +
            ", generalphone='" + getGeneralphone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", description='" + getDescription() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", vatregnumber='" + getVatregnumber() + "'" +
            ", tinnumber='" + getTinnumber() + "'" +
            ", accountcode='" + getAccountcode() + "'" +
            ", accountid=" + getAccountid() +
            "}";
    }
}
