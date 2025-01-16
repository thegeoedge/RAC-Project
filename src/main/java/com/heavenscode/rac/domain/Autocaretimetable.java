package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Autocaretimetable.
 */
@Entity
@Table(name = "autocaretimetable")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocaretimetable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "hoisttypeid")
    private Integer hoisttypeid;

    @Column(name = "hoisttypename")
    private String hoisttypename;

    @Column(name = "hoisttime")
    private Instant hoisttime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocaretimetable id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHoisttypeid() {
        return this.hoisttypeid;
    }

    public Autocaretimetable hoisttypeid(Integer hoisttypeid) {
        this.setHoisttypeid(hoisttypeid);
        return this;
    }

    public void setHoisttypeid(Integer hoisttypeid) {
        this.hoisttypeid = hoisttypeid;
    }

    public String getHoisttypename() {
        return this.hoisttypename;
    }

    public Autocaretimetable hoisttypename(String hoisttypename) {
        this.setHoisttypename(hoisttypename);
        return this;
    }

    public void setHoisttypename(String hoisttypename) {
        this.hoisttypename = hoisttypename;
    }

    public Instant getHoisttime() {
        return this.hoisttime;
    }

    public Autocaretimetable hoisttime(Instant hoisttime) {
        this.setHoisttime(hoisttime);
        return this;
    }

    public void setHoisttime(Instant hoisttime) {
        this.hoisttime = hoisttime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocaretimetable)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocaretimetable) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocaretimetable{" +
            "id=" + getId() +
            ", hoisttypeid=" + getHoisttypeid() +
            ", hoisttypename='" + getHoisttypename() + "'" +
            ", hoisttime='" + getHoisttime() + "'" +
            "}";
    }
}
