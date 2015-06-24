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
 * Created by HH on 2014/11/9.
 */
@Entity
@Table(name = "T_ALLOCATE_ASSET_TMP")
@XmlRootElement(name = "AllocateAsset")
@SuppressWarnings("serial")
public class AllocateAssetDto  implements java.io.Serializable {
    // Fields

    private String allocateAssetId;
    private String assetNo;
    private String outOrg;
    private String originalLocalNo;
    private String originalUser;
    private String inOrg;
    private String newLoaclNo;
    private String newUser;
    private Date allotDate;
    private String allotReason;
    private String note;
    private String publish;
    private String creator;
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

    @Column(name = "UPLOAD_DATE", length = 7)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
// Constructors

    /** default constructor */
    public AllocateAssetDto() {
    }

    /** full constructor */
    public AllocateAssetDto(String assetNo, String outOrg,String loanNo,
                         String originalLocalNo, String originalUser, String inOrg,
                         String newLoaclNo, String newUser, Date allotDate,
                         String allotReason, String note, String publish, String creator,
                         Date createTime, String updater, Date updateTime, String id) {
        this.assetNo = assetNo;
        this.loanNo = loanNo;
        this.outOrg = outOrg;
        this.originalLocalNo = originalLocalNo;
        this.originalUser = originalUser;
        this.inOrg = inOrg;
        this.newLoaclNo = newLoaclNo;
        this.newUser = newUser;
        this.allotDate = allotDate;
        this.allotReason = allotReason;
        this.note = note;
        this.publish = publish;
        this.creator = creator;
        this.createTime = createTime;
        this.updater = updater;
        this.updateTime = updateTime;
        this.id = id;

    }

    // Property accessors
    @Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name = "ALLOCATE_ASSET_ID", unique = true, nullable = false, length = 32)
    public String getAllocateAssetId() {
        return this.allocateAssetId;
    }

    public void setAllocateAssetId(String allocateAssetId) {
        this.allocateAssetId = allocateAssetId;
    }

    @Column(name = "ASSET_NO", length = 30)
    public String getAssetNo() {
        return this.assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    @Column(name = "OUT_ORG", length = 200)
    public String getOutOrg() {
        return this.outOrg;
    }

    public void setOutOrg(String outOrg) {
        this.outOrg = outOrg;
    }

    @Column(name = "ORIGINAL_LOCAL_NO", length = 200)
    public String getOriginalLocalNo() {
        return this.originalLocalNo;
    }

    public void setOriginalLocalNo(String originalLocalNo) {
        this.originalLocalNo = originalLocalNo;
    }

    @Column(name = "ORIGINAL_USER", length = 200)
    public String getOriginalUser() {
        return this.originalUser;
    }

    public void setOriginalUser(String originalUser) {
        this.originalUser = originalUser;
    }

    @Column(name = "IN_ORG", length = 200)
    public String getInOrg() {
        return this.inOrg;
    }

    public void setInOrg(String inOrg) {
        this.inOrg = inOrg;
    }

    @Column(name = "NEW_LOACL_NO", length = 20)
    public String getNewLoaclNo() {
        return this.newLoaclNo;
    }

    public void setNewLoaclNo(String newLoaclNo) {
        this.newLoaclNo = newLoaclNo;
    }

    @Column(name = "NEW_USER", length = 200)
    public String getNewUser() {
        return this.newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "ALLOT_DATE", length = 7)
    public Date getAllotDate() {
        return this.allotDate;
    }

    public void setAllotDate(Date allotDate) {
        this.allotDate = allotDate;
    }

    @Column(name = "ALLOT_REASON", length = 250)
    public String getAllotReason() {
        return this.allotReason;
    }

    public void setAllotReason(String allotReason) {
        this.allotReason = allotReason;
    }

    @Column(name = "NOTE", length = 250)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "PUBLISH", length = 1)
    public String getPublish() {
        return this.publish;
    }

    public void setPublish(String removed) {
        this.publish = removed;
    }

    @Column(name = "CREATOR", length = 100)
    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
