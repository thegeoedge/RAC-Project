package com.heavenscode.rac.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Autocarejob.
 */
@Entity
@Table(name = "autocarejob")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Autocarejob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "jobnumber")
    private Integer jobnumber;

    @Column(name = "vehicleid")
    private Integer vehicleid;

    @Column(name = "vehiclenumber")
    private String vehiclenumber;

    @Column(name = "millage")
    private Float millage;

    @Column(name = "nextmillage")
    private Float nextmillage;

    @Column(name = "nextservicedate")
    private LocalDate nextservicedate;

    @Column(name = "vehicletypeid")
    private Integer vehicletypeid;

    @Column(name = "jobtypeid")
    private Integer jobtypeid;

    @Column(name = "jobtypename")
    private String jobtypename;

    @Column(name = "jobopenby")
    private Integer jobopenby;

    @Column(name = "jobopentime")
    private Instant jobopentime;

    @Column(name = "lmu")
    private Integer lmu;

    @Column(name = "lmd")
    private Instant lmd;

    @Column(name = "specialrquirments")
    private String specialrquirments;

    @Column(name = "specialinstructions")
    private String specialinstructions;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "nextserviceinstructions")
    private String nextserviceinstructions;

    @Column(name = "lastserviceinstructions")
    private String lastserviceinstructions;

    @Column(name = "isadvisorchecked")
    private Boolean isadvisorchecked;

    @Column(name = "isempallocated")
    private Boolean isempallocated;

    @Column(name = "jobclosetime")
    private Instant jobclosetime;

    @Column(name = "isjobclose")
    private Boolean isjobclose;

    @Column(name = "isfeedback")
    private Boolean isfeedback;

    @Column(name = "feedbackstatusid")
    private Integer feedbackstatusid;

    @Column(name = "customername")
    private String customername;

    @Column(name = "customertel")
    private String customertel;

    @Column(name = "customerid")
    private Integer customerid;

    @Column(name = "advisorfinalcheck")
    private Boolean advisorfinalcheck;

    @Column(name = "jobdate")
    private Instant jobdate;

    @Column(name = "iscompanyservice")
    private Boolean iscompanyservice;

    @Column(name = "freeservicenumber")
    private String freeservicenumber;

    @Column(name = "companyid")
    private Integer companyid;

    @Column(name = "updatetocustomer")
    private Boolean updatetocustomer;

    @Column(name = "nextgearoilmilage")
    private String nextgearoilmilage;

    @Column(name = "isjobinvoiced")
    private Boolean isjobinvoiced;

    @Column(name = "iswaiting")
    private Boolean iswaiting;

    @Column(name = "iscustomercomment")
    private Boolean iscustomercomment;

    @Column(name = "imagefolder")
    private String imagefolder;

    @Column(name = "frontimage")
    private String frontimage;

    @Column(name = "leftimage")
    private String leftimage;

    @Column(name = "rightimage")
    private String rightimage;

    @Column(name = "backimage")
    private String backimage;

    @Column(name = "dashboardimage")
    private String dashboardimage;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Autocarejob id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobnumber() {
        return this.jobnumber;
    }

    public Autocarejob jobnumber(Integer jobnumber) {
        this.setJobnumber(jobnumber);
        return this;
    }

    public void setJobnumber(Integer jobnumber) {
        this.jobnumber = jobnumber;
    }

    public Integer getVehicleid() {
        return this.vehicleid;
    }

    public Autocarejob vehicleid(Integer vehicleid) {
        this.setVehicleid(vehicleid);
        return this;
    }

    public void setVehicleid(Integer vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getVehiclenumber() {
        return this.vehiclenumber;
    }

    public Autocarejob vehiclenumber(String vehiclenumber) {
        this.setVehiclenumber(vehiclenumber);
        return this;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public Float getMillage() {
        return this.millage;
    }

    public Autocarejob millage(Float millage) {
        this.setMillage(millage);
        return this;
    }

    public void setMillage(Float millage) {
        this.millage = millage;
    }

    public Float getNextmillage() {
        return this.nextmillage;
    }

    public Autocarejob nextmillage(Float nextmillage) {
        this.setNextmillage(nextmillage);
        return this;
    }

    public void setNextmillage(Float nextmillage) {
        this.nextmillage = nextmillage;
    }

    public LocalDate getNextservicedate() {
        return this.nextservicedate;
    }

    public Autocarejob nextservicedate(LocalDate nextservicedate) {
        this.setNextservicedate(nextservicedate);
        return this;
    }

    public void setNextservicedate(LocalDate nextservicedate) {
        this.nextservicedate = nextservicedate;
    }

    public Integer getVehicletypeid() {
        return this.vehicletypeid;
    }

    public Autocarejob vehicletypeid(Integer vehicletypeid) {
        this.setVehicletypeid(vehicletypeid);
        return this;
    }

    public void setVehicletypeid(Integer vehicletypeid) {
        this.vehicletypeid = vehicletypeid;
    }

    public Integer getJobtypeid() {
        return this.jobtypeid;
    }

    public Autocarejob jobtypeid(Integer jobtypeid) {
        this.setJobtypeid(jobtypeid);
        return this;
    }

    public void setJobtypeid(Integer jobtypeid) {
        this.jobtypeid = jobtypeid;
    }

    public String getJobtypename() {
        return this.jobtypename;
    }

    public Autocarejob jobtypename(String jobtypename) {
        this.setJobtypename(jobtypename);
        return this;
    }

    public void setJobtypename(String jobtypename) {
        this.jobtypename = jobtypename;
    }

    public Integer getJobopenby() {
        return this.jobopenby;
    }

    public Autocarejob jobopenby(Integer jobopenby) {
        this.setJobopenby(jobopenby);
        return this;
    }

    public void setJobopenby(Integer jobopenby) {
        this.jobopenby = jobopenby;
    }

    public Instant getJobopentime() {
        return this.jobopentime;
    }

    public Autocarejob jobopentime(Instant jobopentime) {
        this.setJobopentime(jobopentime);
        return this;
    }

    public void setJobopentime(Instant jobopentime) {
        this.jobopentime = jobopentime;
    }

    public Integer getLmu() {
        return this.lmu;
    }

    public Autocarejob lmu(Integer lmu) {
        this.setLmu(lmu);
        return this;
    }

    public void setLmu(Integer lmu) {
        this.lmu = lmu;
    }

    public Instant getLmd() {
        return this.lmd;
    }

    public Autocarejob lmd(Instant lmd) {
        this.setLmd(lmd);
        return this;
    }

    public void setLmd(Instant lmd) {
        this.lmd = lmd;
    }

    public String getSpecialrquirments() {
        return this.specialrquirments;
    }

    public Autocarejob specialrquirments(String specialrquirments) {
        this.setSpecialrquirments(specialrquirments);
        return this;
    }

    public void setSpecialrquirments(String specialrquirments) {
        this.specialrquirments = specialrquirments;
    }

    public String getSpecialinstructions() {
        return this.specialinstructions;
    }

    public Autocarejob specialinstructions(String specialinstructions) {
        this.setSpecialinstructions(specialinstructions);
        return this;
    }

    public void setSpecialinstructions(String specialinstructions) {
        this.specialinstructions = specialinstructions;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Autocarejob remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNextserviceinstructions() {
        return this.nextserviceinstructions;
    }

    public Autocarejob nextserviceinstructions(String nextserviceinstructions) {
        this.setNextserviceinstructions(nextserviceinstructions);
        return this;
    }

    public void setNextserviceinstructions(String nextserviceinstructions) {
        this.nextserviceinstructions = nextserviceinstructions;
    }

    public String getLastserviceinstructions() {
        return this.lastserviceinstructions;
    }

    public Autocarejob lastserviceinstructions(String lastserviceinstructions) {
        this.setLastserviceinstructions(lastserviceinstructions);
        return this;
    }

    public void setLastserviceinstructions(String lastserviceinstructions) {
        this.lastserviceinstructions = lastserviceinstructions;
    }

    public Boolean getIsadvisorchecked() {
        return this.isadvisorchecked;
    }

    public Autocarejob isadvisorchecked(Boolean isadvisorchecked) {
        this.setIsadvisorchecked(isadvisorchecked);
        return this;
    }

    public void setIsadvisorchecked(Boolean isadvisorchecked) {
        this.isadvisorchecked = isadvisorchecked;
    }

    public Boolean getIsempallocated() {
        return this.isempallocated;
    }

    public Autocarejob isempallocated(Boolean isempallocated) {
        this.setIsempallocated(isempallocated);
        return this;
    }

    public void setIsempallocated(Boolean isempallocated) {
        this.isempallocated = isempallocated;
    }

    public Instant getJobclosetime() {
        return this.jobclosetime;
    }

    public Autocarejob jobclosetime(Instant jobclosetime) {
        this.setJobclosetime(jobclosetime);
        return this;
    }

    public void setJobclosetime(Instant jobclosetime) {
        this.jobclosetime = jobclosetime;
    }

    public Boolean getIsjobclose() {
        return this.isjobclose;
    }

    public Autocarejob isjobclose(Boolean isjobclose) {
        this.setIsjobclose(isjobclose);
        return this;
    }

    public void setIsjobclose(Boolean isjobclose) {
        this.isjobclose = isjobclose;
    }

    public Boolean getIsfeedback() {
        return this.isfeedback;
    }

    public Autocarejob isfeedback(Boolean isfeedback) {
        this.setIsfeedback(isfeedback);
        return this;
    }

    public void setIsfeedback(Boolean isfeedback) {
        this.isfeedback = isfeedback;
    }

    public Integer getFeedbackstatusid() {
        return this.feedbackstatusid;
    }

    public Autocarejob feedbackstatusid(Integer feedbackstatusid) {
        this.setFeedbackstatusid(feedbackstatusid);
        return this;
    }

    public void setFeedbackstatusid(Integer feedbackstatusid) {
        this.feedbackstatusid = feedbackstatusid;
    }

    public String getCustomername() {
        return this.customername;
    }

    public Autocarejob customername(String customername) {
        this.setCustomername(customername);
        return this;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomertel() {
        return this.customertel;
    }

    public Autocarejob customertel(String customertel) {
        this.setCustomertel(customertel);
        return this;
    }

    public void setCustomertel(String customertel) {
        this.customertel = customertel;
    }

    public Integer getCustomerid() {
        return this.customerid;
    }

    public Autocarejob customerid(Integer customerid) {
        this.setCustomerid(customerid);
        return this;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Boolean getAdvisorfinalcheck() {
        return this.advisorfinalcheck;
    }

    public Autocarejob advisorfinalcheck(Boolean advisorfinalcheck) {
        this.setAdvisorfinalcheck(advisorfinalcheck);
        return this;
    }

    public void setAdvisorfinalcheck(Boolean advisorfinalcheck) {
        this.advisorfinalcheck = advisorfinalcheck;
    }

    public Instant getJobdate() {
        return this.jobdate;
    }

    public Autocarejob jobdate(Instant jobdate) {
        this.setJobdate(jobdate);
        return this;
    }

    public void setJobdate(Instant jobdate) {
        this.jobdate = jobdate;
    }

    public Boolean getIscompanyservice() {
        return this.iscompanyservice;
    }

    public Autocarejob iscompanyservice(Boolean iscompanyservice) {
        this.setIscompanyservice(iscompanyservice);
        return this;
    }

    public void setIscompanyservice(Boolean iscompanyservice) {
        this.iscompanyservice = iscompanyservice;
    }

    public String getFreeservicenumber() {
        return this.freeservicenumber;
    }

    public Autocarejob freeservicenumber(String freeservicenumber) {
        this.setFreeservicenumber(freeservicenumber);
        return this;
    }

    public void setFreeservicenumber(String freeservicenumber) {
        this.freeservicenumber = freeservicenumber;
    }

    public Integer getCompanyid() {
        return this.companyid;
    }

    public Autocarejob companyid(Integer companyid) {
        this.setCompanyid(companyid);
        return this;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Boolean getUpdatetocustomer() {
        return this.updatetocustomer;
    }

    public Autocarejob updatetocustomer(Boolean updatetocustomer) {
        this.setUpdatetocustomer(updatetocustomer);
        return this;
    }

    public void setUpdatetocustomer(Boolean updatetocustomer) {
        this.updatetocustomer = updatetocustomer;
    }

    public String getNextgearoilmilage() {
        return this.nextgearoilmilage;
    }

    public Autocarejob nextgearoilmilage(String nextgearoilmilage) {
        this.setNextgearoilmilage(nextgearoilmilage);
        return this;
    }

    public void setNextgearoilmilage(String nextgearoilmilage) {
        this.nextgearoilmilage = nextgearoilmilage;
    }

    public Boolean getIsjobinvoiced() {
        return this.isjobinvoiced;
    }

    public Autocarejob isjobinvoiced(Boolean isjobinvoiced) {
        this.setIsjobinvoiced(isjobinvoiced);
        return this;
    }

    public void setIsjobinvoiced(Boolean isjobinvoiced) {
        this.isjobinvoiced = isjobinvoiced;
    }

    public Boolean getIswaiting() {
        return this.iswaiting;
    }

    public Autocarejob iswaiting(Boolean iswaiting) {
        this.setIswaiting(iswaiting);
        return this;
    }

    public void setIswaiting(Boolean iswaiting) {
        this.iswaiting = iswaiting;
    }

    public Boolean getIscustomercomment() {
        return this.iscustomercomment;
    }

    public Autocarejob iscustomercomment(Boolean iscustomercomment) {
        this.setIscustomercomment(iscustomercomment);
        return this;
    }

    public void setIscustomercomment(Boolean iscustomercomment) {
        this.iscustomercomment = iscustomercomment;
    }

    public String getImagefolder() {
        return this.imagefolder;
    }

    public Autocarejob imagefolder(String imagefolder) {
        this.setImagefolder(imagefolder);
        return this;
    }

    public void setImagefolder(String imagefolder) {
        this.imagefolder = imagefolder;
    }

    public String getFrontimage() {
        return this.frontimage;
    }

    public Autocarejob frontimage(String frontimage) {
        this.setFrontimage(frontimage);
        return this;
    }

    public void setFrontimage(String frontimage) {
        this.frontimage = frontimage;
    }

    public String getLeftimage() {
        return this.leftimage;
    }

    public Autocarejob leftimage(String leftimage) {
        this.setLeftimage(leftimage);
        return this;
    }

    public void setLeftimage(String leftimage) {
        this.leftimage = leftimage;
    }

    public String getRightimage() {
        return this.rightimage;
    }

    public Autocarejob rightimage(String rightimage) {
        this.setRightimage(rightimage);
        return this;
    }

    public void setRightimage(String rightimage) {
        this.rightimage = rightimage;
    }

    public String getBackimage() {
        return this.backimage;
    }

    public Autocarejob backimage(String backimage) {
        this.setBackimage(backimage);
        return this;
    }

    public void setBackimage(String backimage) {
        this.backimage = backimage;
    }

    public String getDashboardimage() {
        return this.dashboardimage;
    }

    public Autocarejob dashboardimage(String dashboardimage) {
        this.setDashboardimage(dashboardimage);
        return this;
    }

    public void setDashboardimage(String dashboardimage) {
        this.dashboardimage = dashboardimage;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autocarejob)) {
            return false;
        }
        return getId() != null && getId().equals(((Autocarejob) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autocarejob{" +
            "id=" + getId() +
            ", jobnumber=" + getJobnumber() +
            ", vehicleid=" + getVehicleid() +
            ", vehiclenumber='" + getVehiclenumber() + "'" +
            ", millage=" + getMillage() +
            ", nextmillage=" + getNextmillage() +
            ", nextservicedate='" + getNextservicedate() + "'" +
            ", vehicletypeid=" + getVehicletypeid() +
            ", jobtypeid=" + getJobtypeid() +
            ", jobtypename='" + getJobtypename() + "'" +
            ", jobopenby=" + getJobopenby() +
            ", jobopentime='" + getJobopentime() + "'" +
            ", lmu=" + getLmu() +
            ", lmd='" + getLmd() + "'" +
            ", specialrquirments='" + getSpecialrquirments() + "'" +
            ", specialinstructions='" + getSpecialinstructions() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", nextserviceinstructions='" + getNextserviceinstructions() + "'" +
            ", lastserviceinstructions='" + getLastserviceinstructions() + "'" +
            ", isadvisorchecked='" + getIsadvisorchecked() + "'" +
            ", isempallocated='" + getIsempallocated() + "'" +
            ", jobclosetime='" + getJobclosetime() + "'" +
            ", isjobclose='" + getIsjobclose() + "'" +
            ", isfeedback='" + getIsfeedback() + "'" +
            ", feedbackstatusid=" + getFeedbackstatusid() +
            ", customername='" + getCustomername() + "'" +
            ", customertel='" + getCustomertel() + "'" +
            ", customerid=" + getCustomerid() +
            ", advisorfinalcheck='" + getAdvisorfinalcheck() + "'" +
            ", jobdate='" + getJobdate() + "'" +
            ", iscompanyservice='" + getIscompanyservice() + "'" +
            ", freeservicenumber='" + getFreeservicenumber() + "'" +
            ", companyid=" + getCompanyid() +
            ", updatetocustomer='" + getUpdatetocustomer() + "'" +
            ", nextgearoilmilage='" + getNextgearoilmilage() + "'" +
            ", isjobinvoiced='" + getIsjobinvoiced() + "'" +
            ", iswaiting='" + getIswaiting() + "'" +
            ", iscustomercomment='" + getIscustomercomment() + "'" +
            ", imagefolder='" + getImagefolder() + "'" +
            ", frontimage='" + getFrontimage() + "'" +
            ", leftimage='" + getLeftimage() + "'" +
            ", rightimage='" + getRightimage() + "'" +
            ", backimage='" + getBackimage() + "'" +
            ", dashboardimage='" + getDashboardimage() + "'" +
            "}";
    }
}
