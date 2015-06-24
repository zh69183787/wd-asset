package com.wonders.asset.model.dw;

import java.io.Serializable;
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
 * 物资消耗及人工情况(delete)
 * @author ycl
 *
 */

@Entity
@Table(name = "DW_MATERIALS_CONSUME")
@SuppressWarnings("serial")
public class DwMaterialsConsume implements Serializable{
	
	private String id;
	private String year;			//年份
	private String materialsPrice;		//物资消耗费用		
	private String materialsWorkingHours;	//物资消耗累计工时
	private String maintainPrice;			//日常维护费用
	private String maintainWorkingHours;	//日常维护累计工时
	private String typeName;				//类型名称
	private String typeId;					//类型id
	private Date createDate;				//创建时间
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name="YEAR",nullable=true,length=4)
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name="MATERIALS_PRICE",nullable=true,length=40)
	public String getMaterialsPrice() {
		return materialsPrice;
	}
	public void setMaterialsPrice(String materialsPrice) {
		this.materialsPrice = materialsPrice;
	}
	
	@Column(name="MATERIALS_WORKING_HOURS",nullable=true,length=40)
	public String getMaterialsWorkingHours() {
		return materialsWorkingHours;
	}
	public void setMaterialsWorkingHours(String materialsWorkingHours) {
		this.materialsWorkingHours = materialsWorkingHours;
	}
	@Column(name="MAINTAIN_PRICE",nullable=true,length=40)
	public String getMaintainPrice() {
		return maintainPrice;
	}
	public void setMaintainPrice(String maintainPrice) {
		this.maintainPrice = maintainPrice;
	}
	@Column(name="MAINTAIN_WORKING_HOURS",nullable=true,length=40)
	public String getMaintainWorkingHours() {
		return maintainWorkingHours;
	}
	public void setMaintainWorkingHours(String maintainWorkingHours) {
		this.maintainWorkingHours = maintainWorkingHours;
	}
	
	@Column(name="TYPE_NAME",nullable=true,length=40)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name="TYPE_ID",nullable=true,length=40)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	
}
