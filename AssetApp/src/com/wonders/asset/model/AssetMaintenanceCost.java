package com.wonders.asset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 日常维护费用数据接口
 * @author ycl
 *
 */
@Entity
@Table(name="T_ASSET_MAINTENANCE_COST")
public class AssetMaintenanceCost {

	private String id;
	private String assetNo;
	private String assetName;
	private Date costTime;
	private Double maintenanceCost;
	private Double costHour;
	private Date importDate;
	private String createUser;
	private Date createDate;
	private String modifyUser;
	private Date modifyDate;
	
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name="ID",nullable=false,length=40)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="ASSET_NO",nullable=true,length=400)
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	@Column(name="ASSET_NAME",nullable=true,length=400)
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="COST_TIME")
	public Date getCostTime() {
		return costTime;
	}
	public void setCostTime(Date costTime) {
		this.costTime = costTime;
	}
	
	@Column(name="MAINTENANCE_COST")
	public Double getMaintenanceCost() {
		return maintenanceCost;
	}
	public void setMaintenanceCost(Double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
	
	@Column(name="COST_HOUR")
	public Double getCostHour() {
		return costHour;
	}
	public void setCostHour(Double costHour) {
		this.costHour = costHour;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="IMPORT_DATE")
	public Date getImportDate() {
		return importDate;
	}
	
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	
	@Column(name="CREATE_USER",nullable=true,length=400)
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="MODIFY_USER",nullable=true,length=400)
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFY_DATE")
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}


