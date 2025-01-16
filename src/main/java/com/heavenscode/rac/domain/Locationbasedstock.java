package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Locationbasedstock.
 */
@Entity
@Table(name = "locationbasedstock")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Locationbasedstock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "itemid", nullable = false)
    private Integer itemid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "locationid", nullable = false)
    private Integer locationid;

    @Column(name = "locationcode")
    private String locationcode;

    @Column(name = "availablequantity")
    private Float availablequantity;

    @Column(name = "hasbatches")
    private Boolean hasbatches;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Locationbasedstock id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemid() {
        return this.itemid;
    }

    public Locationbasedstock itemid(Integer itemid) {
        this.setItemid(itemid);
        return this;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getCode() {
        return this.code;
    }

    public Locationbasedstock code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Locationbasedstock name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocationid() {
        return this.locationid;
    }

    public Locationbasedstock locationid(Integer locationid) {
        this.setLocationid(locationid);
        return this;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public String getLocationcode() {
        return this.locationcode;
    }

    public Locationbasedstock locationcode(String locationcode) {
        this.setLocationcode(locationcode);
        return this;
    }

    public void setLocationcode(String locationcode) {
        this.locationcode = locationcode;
    }

    public Float getAvailablequantity() {
        return this.availablequantity;
    }

    public Locationbasedstock availablequantity(Float availablequantity) {
        this.setAvailablequantity(availablequantity);
        return this;
    }

    public void setAvailablequantity(Float availablequantity) {
        this.availablequantity = availablequantity;
    }

    public Boolean getHasbatches() {
        return this.hasbatches;
    }

    public Locationbasedstock hasbatches(Boolean hasbatches) {
        this.setHasbatches(hasbatches);
        return this;
    }

    public void setHasbatches(Boolean hasbatches) {
        this.hasbatches = hasbatches;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Locationbasedstock lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Locationbasedstock lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Locationbasedstock)) {
            return false;
        }
        return getId() != null && getId().equals(((Locationbasedstock) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Locationbasedstock{" +
            "id=" + getId() +
            ", itemid=" + getItemid() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", locationid=" + getLocationid() +
            ", locationcode='" + getLocationcode() + "'" +
            ", availablequantity=" + getAvailablequantity() +
            ", hasbatches='" + getHasbatches() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
