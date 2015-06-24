package com.wonders.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_ENTERPRISE")
@SuppressWarnings("serial")
public class Enterprise extends SystemInformation{

	private String name;
	private String corporation;
	private String contactPerson;
	private String contactAddress;
	private String type;		//1：供应商，2：生产商
	private String corporationTel;
	private String tel;
	private String zip;
	
	@Column(name="NAME",nullable=true,length=400)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="CORPORATION",nullable=true,length=400)
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	@Column(name="CONTACT_PERSON",nullable=true,length=400)
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	@Column(name="CONTRACT_ADDRESS",nullable=true,length=2000)
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	@Column(name="TYPE",nullable=true,length=400)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="CORPORATION_TEL",nullable=true,length=400)
	public String getCorporationTel() {
		return corporationTel;
	}
	public void setCorporationTel(String corporationTel) {
		this.corporationTel = corporationTel;
	}
	@Column(name="TEL",nullable=true,length=400)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="ZIP",nullable=true,length=400)
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
