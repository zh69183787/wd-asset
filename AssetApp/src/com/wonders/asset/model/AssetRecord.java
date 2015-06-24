package com.wonders.asset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_ASSET_RECORD")
@SuppressWarnings("serial")
public class AssetRecord extends SystemInformation{
	
	private String id;
	private String projectName;
	private String maintainContent;
	private String maintainCost;
	private String assetType;
	private String projectAppNo;
	private Date finishDate;
	private String finishPrice;
	private Asset Asset;

	private String assetRecordID;
	private int no;
	@Transient
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	@Column(name="ASSET_RECORD_ID")
	public String getAssetRecordID() {
		return assetRecordID;
	}
	public void setAssetRecordID(String assetRecordID) {
		this.assetRecordID = assetRecordID;
	}
	@Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name = "ID", unique = true, nullable = false, length = 32)  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="PROJECT_NAME",nullable=true)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name="MAINTAIN_CONTENT",nullable=true)
	public String getMaintainContent() {
		return maintainContent;
	}
	public void setMaintainContent(String maintainContent) {
		this.maintainContent = maintainContent;
	}
	
	@Column(name="MAINTAIN_COST",nullable=true)
	public String getMaintainCost() {
		return maintainCost;
	}
	public void setMaintainCost(String maintainCost) {
		this.maintainCost = maintainCost;
	}
	
	@Column(name="ASSET_TYPE",nullable=true)
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
	@Column(name="PROJECT_APP_NO",nullable=true)
	public String getProjectAppNo() {
		return projectAppNo;
	}
	public void setProjectAppNo(String projectAppNo) {
		this.projectAppNo = projectAppNo;
	}
	
	@Column(name="FINISH_DATE",nullable=true)
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	@Column(name="FINISH_PRICE",nullable=true)
	public String getFinishPrice() {
		return finishPrice;
	}
	public void setFinishPrice(String finishPrice) {
		this.finishPrice = finishPrice;
	}
	
	@ManyToOne
	@JoinColumn(name="ASSET_ID")
	public Asset getAsset() {
		return Asset;
	}
	public void setAsset(Asset asset) {
		Asset = asset;
	}
	
	
}
