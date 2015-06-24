package com.wonders.webservice.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_AREA_INFO_TMP")
public class AreaInfoDto implements java.io.Serializable {

	// Fields

	private String areaInfoId;
	private String assetNo;
	private String useType;
	private Double reallyArea;
	private String takeOverDep;
	private String inFloor;
	private String note;
	private String houseAssetId;
	private Date createTime;
	private String updater;
	private Date updateTime;
	private String creator;
	private String publish;
	private String id;
	private Date uploadDate;
	private String detail;
	
	

	// Constructors
	@Column(name = "DETAIL", length = 100)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	/** default constructor */
	public AreaInfoDto() {
	}

	/** full constructor */
	public AreaInfoDto(String assetNo, String useType, Double reallyArea,
			String takeOverDep, String inFloor, String note,
			String houseAssetId, Date createTime, String updater,
			Date updateTime, String creator, String publish, String id, Date uploadDate) {
		this.assetNo = assetNo;
		this.useType = useType;
		this.reallyArea = reallyArea;
		this.takeOverDep = takeOverDep;
		this.inFloor = inFloor;
		this.note = note;
		this.houseAssetId = houseAssetId;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.creator = creator;
		this.publish = publish;
		this.id = id;
		this.uploadDate = uploadDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(name = "AREA_INFO_ID", unique = true, nullable = false, length = 32)
	public String getAreaInfoId() {
		return this.areaInfoId;
	}

	public void setAreaInfoId(String areaInfoId) {
		this.areaInfoId = areaInfoId;
	}

	@Column(name = "ASSET_NO", length = 30)
	public String getAssetNo() {
		return this.assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	@Column(name = "USE_TYPE", length = 200)
	public String getUseType() {
		return this.useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@Column(name = "REALLY_AREA", precision = 20, scale = 6)
	public Double getReallyArea() {
		return this.reallyArea;
	}

	public void setReallyArea(Double reallyArea) {
		this.reallyArea = reallyArea;
	}

	@Column(name = "TAKE_OVER_DEP", length = 200)
	public String getTakeOverDep() {
		return this.takeOverDep;
	}

	public void setTakeOverDep(String takeOverDep) {
		this.takeOverDep = takeOverDep;
	}

	@Column(name = "IN_FLOOR", length = 50)
	public String getInFloor() {
		return this.inFloor;
	}

	public void setInFloor(String inFloor) {
		this.inFloor = inFloor;
	}

	@Column(name = "NOTE", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "HOUSE_ASSET_ID", length = 100)
	public String getHouseAssetId() {
		return this.houseAssetId;
	}

	public void setHouseAssetId(String houseAssetId) {
		this.houseAssetId = houseAssetId;
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
		return publish;
	}
	
	public void setPublish(String publish) {
		this.publish = publish;
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
