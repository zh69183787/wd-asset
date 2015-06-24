package com.wonders.asset.model;

/**
 * Created by Administrator on 2014/11/17.
 */

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="T_PROJECT_TRANSFER")
public class ProjectTransfer {

    private String year;
    private String projectTransferId;
    private String projectId;
    private Double count;

    @Column(name="COUNT",nullable=true)
    public Double getCount() {
        return count;
    }
    public void setCount(Double count) {
        this.count = count;
    }

    @Column(name="YEAR",nullable=true,length=4)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "PROJECT_TRANSFER_ID", nullable = false, length = 40)
    public String getProjectTransferId() {
        return projectTransferId;
    }

    public void setProjectTransferId(String projectTransferId) {
        this.projectTransferId = projectTransferId;
    }

    @Column(name="PROJECT_ID",nullable=true,length=50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
