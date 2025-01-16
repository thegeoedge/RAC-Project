package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Autocarejobinimages.
 */
@Entity
@Table(name = "autocarejobinimages")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocarejobinimages implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jobid")
    private Integer jobid;

    @Column(name = "imagefolder")
    private String imagefolder;

    @Column(name = "imagename")
    private String imagename;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocarejobinimages id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobid() {
        return this.jobid;
    }

    public Autocarejobinimages jobid(Integer jobid) {
        this.setJobid(jobid);
        return this;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public String getImagefolder() {
        return this.imagefolder;
    }

    public Autocarejobinimages imagefolder(String imagefolder) {
        this.setImagefolder(imagefolder);
        return this;
    }

    public void setImagefolder(String imagefolder) {
        this.imagefolder = imagefolder;
    }

    public String getImagename() {
        return this.imagename;
    }

    public Autocarejobinimages imagename(String imagename) {
        this.setImagename(imagename);
        return this;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocarejobinimages)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocarejobinimages) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocarejobinimages{" +
            "id=" + getId() +
            ", jobid=" + getJobid() +
            ", imagefolder='" + getImagefolder() + "'" +
            ", imagename='" + getImagename() + "'" +
            "}";
    }
}
