package com.wonders.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_UNIT_MASTER")
public class UnitMaster {

	private String id;
	private String name;
	private String seniorUnit;
	private String conversion;
	
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
	@Column(name="NAME",nullable=true,length=400)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="SENIOR_UNIT",nullable=true,length=400)
	public String getSeniorUnit() {
		return seniorUnit;
	}
	public void setSeniorUnit(String seniorUnit) {
		this.seniorUnit = seniorUnit;
	}
	@Column(name="CONVERSION",nullable=true,length=400)
	public String getConversion() {
		return conversion;
	}
	public void setConversion(String conversion) {
		this.conversion = conversion;
	}
	
}
