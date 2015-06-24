package com.wonders.asset.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TWorkPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_WORK_PLAN")
public class WorkPlan implements java.io.Serializable {

    // Fields

    private String workPlanId;
    private Date planEndDate;
    private Date realEndDate;
    private Date planBeginDate;
    private Date realBeginDate;
    private String content;
    private String note;
    private String deptId;
    private Date createTime;
    private String updater;
    private Date updateTime;
    private String creator;
    private String publish;

    // Constructors

    /** default constructor */
    public WorkPlan() {
    }

    /** full constructor */
    public WorkPlan(Date planEndDate, Date realEndDate, Date planBeginDate,
                     Date realBeginDate, String content, String note, String deptId,
                     Date createTime, String updater, Date updateTime, String creator,
                     String publish) {
        this.planEndDate = planEndDate;
        this.realEndDate = realEndDate;
        this.planBeginDate = planBeginDate;
        this.realBeginDate = realBeginDate;
        this.content = content;
        this.note = note;
        this.deptId = deptId;
        this.createTime = createTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.creator = creator;
        this.publish = publish;
    }

    // Property accessors
    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name = "WORK_PLAN_ID", unique = true, nullable = false, length = 32)
    public String getWorkPlanId() {
        return this.workPlanId;
    }

    public void setWorkPlanId(String workPlanId) {
        this.workPlanId = workPlanId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PLAN_END_DATE", length = 7)
    public Date getPlanEndDate() {
        return this.planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REAL_END_DATE", length = 7)
    public Date getRealEndDate() {
        return this.realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PLAN_BEGIN_DATE", length = 7)
    public Date getPlanBeginDate() {
        return this.planBeginDate;
    }

    public void setPlanBeginDate(Date planBeginDate) {
        this.planBeginDate = planBeginDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REAL_BEGIN_DATE", length = 7)
    public Date getRealBeginDate() {
        return this.realBeginDate;
    }

    public void setRealBeginDate(Date realBeginDate) {
        this.realBeginDate = realBeginDate;
    }

    @Column(name = "CONTENT", length = 500)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "NOTE", length = 500)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "DEPT_ID", length = 100)
    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_TIME", length = 7)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "UPDATER", length = 100)
    public String getUpdater() {
        return this.updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATE_TIME", length = 7)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "CREATOR", length = 100)
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name = "PUBLISH", length = 1)
    public String getPublish() {
        return this.publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

}