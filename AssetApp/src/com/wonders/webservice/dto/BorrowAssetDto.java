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
@Table(name = "T_BORROW_ASSET_TMP")
@XmlRootElement(name = "BorrowAsset")
@SuppressWarnings("serial")
public class BorrowAssetDto {

	// Fields
	private String borrowAssetId;
	private String assetNo;
	private String loanOrg;
	private Date loanDate;
	private String loanReason;
	private String borrOrg;
	private String borrower;
	private String returnBack;
	private String returnPerson;
	private Date returnDate;
	private String note;
	private String state;
	private String publish;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;
	private String id;
	private Date uploadDate;
	
	private String businessType;
	private String loanNo;
	
	@Column(name = "BUSINESS_TYPE")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {  
		this.businessType = businessType;
	}

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

	// Constructors

	/** default constructor */
	public BorrowAssetDto() {
	}

	/** full constructor */
	public BorrowAssetDto(String assetNo, String loanOrg, Date loanDate,
			String loanReason, String borrOrg, String borrower,
			String returnBack, String returnPerson, Date returnDate,
			String note, String state, String publish, String creator,
			Date createTime, String updater, Date updateTime, String id,Date uploadDate) {
		this.assetNo = assetNo;
		this.loanOrg = loanOrg;
		this.loanDate = loanDate;
		this.loanReason = loanReason;
		this.borrOrg = borrOrg;
		this.borrower = borrower;
		this.returnBack = returnBack;
		this.returnPerson = returnPerson;
		this.returnDate = returnDate;
		this.note = note;
		this.state = state;
		this.publish = publish;
		this.creator = creator;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.id = id;
		this.uploadDate = uploadDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(name = "BORROW_ASSET_ID", unique = true, nullable = false, length = 32)
	public String getBorrowAssetId() {
		return this.borrowAssetId;
	}

	public void setBorrowAssetId(String borrowAssetId) {
		this.borrowAssetId = borrowAssetId;
	}

	@Column(name = "ASSET_NO", length = 200)
	public String getAssetNo() {
		return this.assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	@Column(name = "LOAN_ORG", length = 200)
	public String getLoanOrg() {
		return this.loanOrg;
	}

	public void setLoanOrg(String loanOrg) {
		this.loanOrg = loanOrg;
	}

	@Temporal(TemporalType.DATE)
    @XmlJavaTypeAdapter(DateAdapter.class)
	@Column(name = "LOAN_DATE", length = 7)
	public Date getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	@Column(name = "LOAN_REASON", length = 250)
	public String getLoanReason() {
		return this.loanReason;
	}

	public void setLoanReason(String loanReason) {
		this.loanReason = loanReason;
	}

	@Column(name = "BORR_ORG", length = 200)
	public String getBorrOrg() {
		return this.borrOrg;
	}

	public void setBorrOrg(String borrOrg) {
		this.borrOrg = borrOrg;
	}

	@Column(name = "BORROWER", length = 200)
	public String getBorrower() {
		return this.borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	@Column(name = "RETURN_BACK", length = 50)
	public String getReturnBack() {
		return this.returnBack;
	}

	public void setReturnBack(String returnBack) {
		this.returnBack = returnBack;
	}

	@Column(name = "RETURN_PERSON", length = 200)
	public String getReturnPerson() {
		return this.returnPerson;
	}

	public void setReturnPerson(String returnPerson) {
		this.returnPerson = returnPerson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RETURN_DATE", length = 7)
    @XmlJavaTypeAdapter(DateAdapter.class)
	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Column(name = "NOTE", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "STATE", length = 30)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "PUBLISH", length = 1)
	public String getPublish() {
		return publish;
	}
	
	public void setPublish(String publish) {
		this.publish = publish;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOAD_DATE", length = 7)
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}
