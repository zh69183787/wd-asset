package com.wonders.webservice.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wonders.utils.DateAdapter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_DISABLE_ASSET_TMP")  
@XmlRootElement(name = "DisableAsset")  
@SuppressWarnings("serial")
public class DisableAssetDto implements java.io.Serializable {
    // Fields
    private String disableAssetId;
    private String assetNo;
    private String creator;
    private String organ;
    private String stopReason;  
    private Date stopDate;
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


    // Property accessors
    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name = "DISABLE_ASSET_ID", unique = true, nullable = false, length = 32)
    public String getDisableAssetId() {
        return this.disableAssetId;
    }
    
    public void setDisableAssetId(String disableAssetId) {   
        this.disableAssetId = disableAssetId;
    }
    // Constructors
    public DisableAssetDto(){
    	
    }
    public DisableAssetDto(String disableAssetId, String assetNo, String creator,
			String organ, String stopReason, Date stopDate, String note,
			String state, String publish, Date createTime, String updater,
			Date updateTime, String id, String loanNo,
			List<AttachmentDto> attachmentList, Date uploadDate) {
		super();
		this.disableAssetId = disableAssetId;
		this.assetNo = assetNo;
		this.creator = creator;
		this.organ = organ;
		this.stopReason = stopReason;
		this.stopDate = stopDate;
		this.note = note;
		this.state = state;
		this.publish = publish;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.id = id;
		this.loanNo = loanNo;
		this.attachmentList = attachmentList;
		this.uploadDate = uploadDate;
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
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "STOP_DATE", length = 7)
    public Date getStopDate() {
        return this.stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    @Column(name = "NOTE", length = 250)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "STATE")
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
