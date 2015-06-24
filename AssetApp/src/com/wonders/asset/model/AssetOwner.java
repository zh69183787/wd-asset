package com.wonders.asset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_ASSET_OWNER")
@SuppressWarnings("serial")
public class AssetOwner extends SystemInformation {

	private String user;
	private String owner;
	private Date beginUseDate;
	private Date stopUseDate;
	private Date receiveDate;
	private Date purchaseDate;


    private String lineCodeId;
	private String stationCodeId;
	private String useOrganizationCodeId;
	private String ownerOrganizationCodeId;
	private String departmentCodeId;
	
	private Line line;
	private Station station;
	private Organization useOrganization;
	private Organization ownerOrganization;
	private Department department;

    @Column(name = "USER_", nullable = true, length = 400)
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "OWNER_", nullable = true, length = 400)
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "GEGIN_USE_DATE", nullable = true, length = 400)
	public Date getBeginUseDate() {
		return beginUseDate;
	}

	public void setBeginUseDate(Date beginUseDate) {
		this.beginUseDate = beginUseDate;
	}

	@Column(name = "STOP_USE_DATE", nullable = true, length = 400)
	public Date getStopUseDate() {
		return stopUseDate;
	}

	public void setStopUseDate(Date stopUseDate) {
		this.stopUseDate = stopUseDate;
	}

	@Column(name = "RECEIVE_DATE", nullable = true, length = 400)
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	@Column(name = "PURCHASE_DATE", nullable = true, length = 400)
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Transient
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Transient
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Transient
	public Organization getUseOrganization() {
		return useOrganization;
	}

	public void setUseOrganization(Organization useOrganization) {
		this.useOrganization = useOrganization;
	}

	@Transient
	public Organization getOwnerOrganization() {
		return ownerOrganization;
	}

	public void setOwnerOrganization(Organization ownerOrganization) {
		this.ownerOrganization = ownerOrganization;
	}

	@Transient
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	@Column(name = "LINE_CODE_ID", nullable = true, length = 400)
	public String getLineCodeId() {
		return lineCodeId;
	}

	public void setLineCodeId(String lineCodeId) {
		this.lineCodeId = lineCodeId;
	}

	@Column(name = "STATION_CODE_ID", nullable = true, length = 400)
	public String getStationCodeId() {
		return stationCodeId;
	}

	public void setStationCodeId(String stationCodeId) {
		this.stationCodeId = stationCodeId;
	}

	@Column(name = "USER_ORG_CODE_ID", nullable = true, length = 400)
	public String getUseOrganizationCodeId() {
		return useOrganizationCodeId;
	}

	public void setUseOrganizationCodeId(String useOrganizationCodeId) {
		this.useOrganizationCodeId = useOrganizationCodeId;
	}

	@Column(name = "OWNER_ORG_CODE_ID", nullable = true, length = 400)
	public String getOwnerOrganizationCodeId() {
		return ownerOrganizationCodeId;
	}

	public void setOwnerOrganizationCodeId(String ownerOrganizationCodeId) {
		this.ownerOrganizationCodeId = ownerOrganizationCodeId;
	}

	@Column(name = "DEPARTMENT_CODE_ID", nullable = true, length = 400)
	public String getDepartmentCodeId() {
		return departmentCodeId;
	}

	public void setDepartmentCodeId(String departmentCodeId) {
		this.departmentCodeId = departmentCodeId;
	}

	
	
	
}
