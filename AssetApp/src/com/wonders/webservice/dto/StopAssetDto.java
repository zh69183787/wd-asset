package com.wonders.webservice.dto;

import com.wonders.utils.DateAdapter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Date;
import java.util.List;

/**
 * Created by HH on 2014/11/7.
 */
@Entity
@Table(name = "T_STOP_ASSET_TMP")  
@XmlRootElement(name = "StopAsset")  
@SuppressWarnings("serial")
public class StopAssetDto  implements java.io.Serializable {
    // Fields
    private String stopAssetId;
    private String assetNo;
    private String creator;
    private String organ;
    private String stopReason;
    private Date stopDate;
    private String startReason;
    private Date startDate;
    private String note;
    private String state;
    private String publish;
    private Date createTime;
    private String updater;
    private Date updateTime;  
    private String id;
    private String loanNo;
    private Date uploadDate;
    
    @Column(name = "LOAN_NO")
	public String getLoanNo() {  
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
    
    private List<AttachmentDto> attachmentList;
    
    @Transient
    @XmlElementWrapper(name = "attachmentList")
    @XmlElements({
            @XmlElement(name="Attachment", type = AttachmentDto.class)    
    })
	public List<AttachmentDto> getAttachmentList() {
		return attachmentList;
	}
    public void setAttachmentList(List<AttachmentDto> attachmentList) {
		this.attachmentList = attachmentList;
	}  

    @Column(name = "UPLOAD_DATE", length =7)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }


    // Constructors

    /** default constructor */
    public StopAssetDto() {
    }

    /** full constructor */
    public StopAssetDto(String assetNo, String creator, String organ,
                     String stopReason, Date stopDate, String startReason,
                     Date startDate, String note, String state, String publish,
                     Date createTime, String updater, Date updateTime, String id,Date uploadDate) {
        this.assetNo = assetNo;
        this.creator = creator;
        this.organ = organ;
        this.stopReason = stopReason;
        this.stopDate = stopDate;
        this.startReason = startReason;
        this.startDate = startDate;
        this.note = note;
        this.state = state;
        this.publish = publish;
        this.createTime = createTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.id = id;
        this.uploadDate=uploadDate;

    }

    // Property accessors
    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name = "STOP_ASSET_ID", unique = true, nullable = false, length = 32)
    public String getStopAssetId() {
        return this.stopAssetId;
    }

    public void setStopAssetId(String stopAssetId) {
        this.stopAssetId = stopAssetId;
    }

    @Column(name = "ASSET_NO", length = 30)
    public String getAssetNo() {
        return this.assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    @Column(name = "CREATOR", length = 200)
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name = "ORGAN", length = 200)
    public String getOrgan() {
        return this.organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    @Column(name = "STOP_REASON", length = 250)
    public String getStopReason() {
        return this.stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "STOP_DATE", length = 7)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getStopDate() {
        return this.stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    @Column(name = "START_REASON", length = 250)
    public String getStartReason() {
        return this.startReason;
    }

    public void setStartReason(String startReason) {
        this.startReason = startReason;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", length = 7)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "NOTE", length = 250)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "STATE", length = 1)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "PUBLISH", length = 1)
    public String getPublish() {
        return this.publish;
    }

    public void setPublish(String removed) {
        this.publish = removed;
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

    @Column(name = "ID", length = 100)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
