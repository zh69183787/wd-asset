package com.wonders.webservice.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@Entity
@Table(name = "T_ASSET_CHECKINFO_TMP")
@XmlRootElement
public class AssetCheckinfoDto implements java.io.Serializable {

    // Fields

    private String assetCheckinfoId;
    private String assetNo;
    private String checkInfo;
    private String checkCode;
    private String checkDate;
    private String checkPerson;
    private Date createTime;
    private String updater;
    private Date updateTime;
    private String creator;
    private String removed;
    private String id;
    private Date uploadDate;


    @Column(name = "UPLOAD_DATE",  length = 7)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }


    // Property accessors
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @Column(name = "ASSET_CHECKINFO_ID", unique = true, nullable = false, length = 32)
    public String getAssetCheckinfoId() {
        return this.assetCheckinfoId;
    }

    public void setAssetCheckinfoId(String assetCheckinfoId) {
        this.assetCheckinfoId = assetCheckinfoId;
    }

    @Column(name = "ASSET_NO", length = 40)
    public String getAssetNo() {
        return this.assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    @Column(name = "CHECK_INFO", length = 500)
    public String getCheckInfo() {
        return this.checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    @Column(name = "CHECK_CODE", length = 50)
    public String getCheckCode() {
        return this.checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }


    @Column(name = "CHECK_DATE", length = 50)
    public String getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    @Column(name = "CHECK_PERSON", length = 200)
    public String getCheckPerson() {
        return this.checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
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

    @Column(name = "REMOVED", length = 1)
    public String getRemoved() {
        return this.removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    @Column(name = "ID", length = 100)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
