package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Taxes.
 */
@Entity
@Table(name = "taxes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Taxes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "effectivefrom")
    private LocalDate effectivefrom;

    @Column(name = "effectiveto")
    private LocalDate effectiveto;

    @Column(name = "percentage", precision = 21, scale = 2)
    private BigDecimal percentage;

    @Column(name = "fixedamount")
    private Float fixedamount;

    @Column(name = "ismanual")
    private Boolean ismanual;

    @Column(name = "isactive")
    private Boolean isactive;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Taxes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Taxes code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public Taxes description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEffectivefrom() {
        return this.effectivefrom;
    }

    public Taxes effectivefrom(LocalDate effectivefrom) {
        this.setEffectivefrom(effectivefrom);
        return this;
    }

    public void setEffectivefrom(LocalDate effectivefrom) {
        this.effectivefrom = effectivefrom;
    }

    public LocalDate getEffectiveto() {
        return this.effectiveto;
    }

    public Taxes effectiveto(LocalDate effectiveto) {
        this.setEffectiveto(effectiveto);
        return this;
    }

    public void setEffectiveto(LocalDate effectiveto) {
        this.effectiveto = effectiveto;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }

    public Taxes percentage(BigDecimal percentage) {
        this.setPercentage(percentage);
        return this;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public Float getFixedamount() {
        return this.fixedamount;
    }

    public Taxes fixedamount(Float fixedamount) {
        this.setFixedamount(fixedamount);
        return this;
    }

    public void setFixedamount(Float fixedamount) {
        this.fixedamount = fixedamount;
    }

    public Boolean getIsmanual() {
        return this.ismanual;
    }

    public Taxes ismanual(Boolean ismanual) {
        this.setIsmanual(ismanual);
        return this;
    }

    public void setIsmanual(Boolean ismanual) {
        this.ismanual = ismanual;
    }

    public Boolean getIsactive() {
        return this.isactive;
    }

    public Taxes isactive(Boolean isactive) {
        this.setIsactive(isactive);
        return this;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Taxes lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Taxes lmd(Instant lmd) {
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
        if (!(o instanceof Taxes)) {
            return false;
        }
        return getId() != null && getId().equals(((Taxes) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Taxes{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", effectivefrom='" + getEffectivefrom() + "'" +
            ", effectiveto='" + getEffectiveto() + "'" +
            ", percentage=" + getPercentage() +
            ", fixedamount=" + getFixedamount() +
            ", ismanual='" + getIsmanual() + "'" +
            ", isactive='" + getIsactive() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
