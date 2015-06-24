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
 * 大修更新改造专业分布及变化
 * @author ycl
 *
 */

@Entity
@Table(name = "DW_OVERHAUL_MAJOR_TYPE")
@SuppressWarnings("serial")
public class DwOverhaulMajorType implements Serializable{
	
	private String id;
	private String year;
	private String typeName;
	private String typeId;
	
	private Double overhaulPayPlan; //大修用款计划
	private Double overhaulActualPriceGroup;	//大修实际费用(集团)
	private Double overhaulActualPriceOffice;	//大修实际费用(交港局)
	private String overhaulOccupyRate;	//大修计划实际占用比例
	
	private Double renovatePayPlan; //更新改造用款计划
	private Double renovateActualPriceGroup;	//更新改造实际费用(集团)
	private Double renovateActualPriceOffice;	//更新改造实际费用(集团)
	private String renovateOccupyRate;		//更新改造计划实际占用比例
	
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="TYPE_NAME",nullable=true,length=200)
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Column(name="TYPE_ID",nullable=true,length=200)
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Column(name = "OVERHAUL_PAY_PLAN")
	public Double getOverhaulPayPlan() {
		return overhaulPayPlan;
	}
	public void setOverhaulPayPlan(Double overhaulPayPlan) {
		this.overhaulPayPlan = overhaulPayPlan;
	}
	
	@Column(name = "OVERHAUL_ACTUAL_PRICE_GROUP")
	public Double getOverhaulActualPriceGroup() {
		return overhaulActualPriceGroup;
	}
	public void setOverhaulActualPriceGroup(Double overhaulActualPriceGroup) {
		this.overhaulActualPriceGroup = overhaulActualPriceGroup;
	}
	
	@Column(name = "OVERHAUL_ACTUAL_PRICE_OFFICE")
	public Double getOverhaulActualPriceOffice() {
		return overhaulActualPriceOffice;
	}
	public void setOverhaulActualPriceOffice(Double overhaulActualPriceOffice) {
		this.overhaulActualPriceOffice = overhaulActualPriceOffice;
	}
	
	@Column(name = "OVERHAUL_OCCUPY_RATE", length = 40)
	public String getOverhaulOccupyRate() {
		return overhaulOccupyRate;
	}
	public void setOverhaulOccupyRate(String overhaulOccupyRate) {
		this.overhaulOccupyRate = overhaulOccupyRate;
	}
	
	@Column(name = "RENOVATE_PAY_PLAN")
	public Double getRenovatePayPlan() {
		return renovatePayPlan;
	}
	public void setRenovatePayPlan(Double renovatePayPlan) {
		this.renovatePayPlan = renovatePayPlan;
	}
	
	@Column(name = "RENOVATE_ACTUAL_PRICE_GROUP")
	public Double getRenovateActualPriceGroup() {
		return renovateActualPriceGroup;
	}
	public void setRenovateActualPriceGroup(Double renovateActualPriceGroup) {
		this.renovateActualPriceGroup = renovateActualPriceGroup;
	}
	
	@Column(name = "RENOVATE_ACTUAL_PRICE_OFFICE")
	public Double getRenovateActualPriceOffice() {
		return renovateActualPriceOffice;
	}
	public void setRenovateActualPriceOffice(Double renovateActualPriceOffice) {
		this.renovateActualPriceOffice = renovateActualPriceOffice;
	}
	
	@Column(name = "RENOVATE_OCCUPY_RATE", length = 40)
	public String getRenovateOccupyRate() {
		return renovateOccupyRate;
	}
	public void setRenovateOccupyRate(String renovateOccupyRate) {
		this.renovateOccupyRate = renovateOccupyRate;
	}
	
}
