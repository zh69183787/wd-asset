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
 * TAssetCheckinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ASSET_CHECKINFO")
public class AssetCheckinfo implements java.io.Serializable {

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
	private String assetTaskId;

	// Constructors

	/** default constructor */
	public AssetCheckinfo() {
	}

	/** minimal constructor */
	public AssetCheckinfo(String assetTaskId) {
		this.assetTaskId = assetTaskId;
	}

	/** full constructor */
	public AssetCheckinfo(String assetNo, String checkInfo, String checkCode,
			String checkDate, String checkPerson, Date createTime,
			String updater, Date updateTime, String creator, String removed,
			String id, String assetTaskId) {
		this.assetNo = assetNo;
		this.checkInfo = checkInfo;
		this.checkCode = checkCode;
		this.checkDate = checkDate;
		this.checkPerson = checkPerson;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.creator = creator;
		this.removed = removed;
		this.id = id;
		this.assetTaskId = assetTaskId;
	}

	// Property accessors
	@Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(name = "ASSET_CHECKINFO_ID", unique = true, nullable = false, length = 32)
	public String getAssetCheckinfoId() {
		return this.assetCheckinfoId;
	}

	public void setAssetCheckinfoId(String assetCheckinfoId) {
		this.assetCheckinfoId = assetCheckinfoId;
	}

	@Column(name = "ASSET_NO", length = 30)
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

	@Column(name = "ASSET_TASK_ID", nullable = false, length = 32)
	public String getAssetTaskId() {
		return this.assetTaskId;
	}

	public void setAssetTaskId(String assetTaskId) {
		this.assetTaskId = assetTaskId;
	}

}