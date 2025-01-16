package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autocarehoist.
 */
@Entity
@Table(name = "autocarehoist")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocarehoist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "hoistname")
    private String hoistname;

    @Column(name = "hoistcode")
    private String hoistcode;

    @Column(name = "hoisttypeid")
    private Integer hoisttypeid;

    @Column(name = "hoisttypename")
    private String hoisttypename;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocarehoist id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoistname() {
        return this.hoistname;
    }

    public Autocarehoist hoistname(String hoistname) {
        this.setHoistname(hoistname);
        return this;
    }

    public void setHoistname(String hoistname) {
        this.hoistname = hoistname;
    }

    public String getHoistcode() {
        return this.hoistcode;
    }

    public Autocarehoist hoistcode(String hoistcode) {
        this.setHoistcode(hoistcode);
        return this;
    }

    public void setHoistcode(String hoistcode) {
        this.hoistcode = hoistcode;
    }

    public Integer getHoisttypeid() {
        return this.hoisttypeid;
    }

    public Autocarehoist hoisttypeid(Integer hoisttypeid) {
        this.setHoisttypeid(hoisttypeid);
        return this;
    }

    public void setHoisttypeid(Integer hoisttypeid) {
        this.hoisttypeid = hoisttypeid;
    }

    public String getHoisttypename() {
        return this.hoisttypename;
    }

    public Autocarehoist hoisttypename(String hoisttypename) {
        this.setHoisttypename(hoisttypename);
        return this;
    }

    public void setHoisttypename(String hoisttypename) {
        this.hoisttypename = hoisttypename;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autocarehoist lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autocarehoist lmd(Instant lmd) {
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
        if (!(o instanceof Autocarehoist)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocarehoist) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocarehoist{" +
            "id=" + getId() +
            ", hoistname='" + getHoistname() + "'" +
            ", hoistcode='" + getHoistcode() + "'" +
            ", hoisttypeid=" + getHoisttypeid() +
            ", hoisttypename='" + getHoisttypename() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            "}";
    }
}
