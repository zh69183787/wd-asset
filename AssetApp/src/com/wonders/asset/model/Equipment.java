package com.wonders.asset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_EQUIPMENTS")
@SuppressWarnings("serial")
public class Equipment extends SystemInformation{

	private String name;
	private String type;
	private String manufactureCountry;
	private Date useDate;
	private Double serviceLife;
	private String Location;
	private Double price;
	
	private Enterprise manufacturer;
	private Enterprise supplier;	
	private Asset asset;
	
	@Column(name="NAME",nullable=true,length=400)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="TYPE",nullable=true,length=400)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="MANUFACTURE_COUNTRY",nullable=true,length=400)
	public String getManufactureCountry() {
		return manufactureCountry;
	}
	public void setManufactureCountry(String manufactureCountry) {
		this.manufactureCountry = manufactureCountry;
	}
	
	@Column(name="USE_DATE",nullable=true)
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Column(name="SERVICE_LIFE",nullable=true)
	public Double getServiceLife() {
		return serviceLife;
	}
	public void setServiceLife(Double serviceLife) {
		this.serviceLife = serviceLife;
	}
	
	@Column(name="LOCATION",nullable=true,length=400)
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	
	@Column(name="PRICE",nullable=true)
	public Double getPrice() {
		return price;
	}
	
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	public Enterprise getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Enterprise manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	public Enterprise getSupplier() {
		return supplier;
	}
	public void setSupplier(Enterprise supplier) {
		this.supplier = supplier;
	}
	
	@ManyToOne
	@JoinColumn(name="ASSET_ID")
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	
	
	
	
}
