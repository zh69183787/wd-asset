package com.wonders.asset.model.bo;

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

import com.wonders.asset.model.dw.DwOverhaulMajorType;

/**
 * 大修更新改造专业分布及变化
 * @author ycl
 *
 */

public class DwOverhaulMajorTypeBo implements Serializable{
	
	private String id;
	private String year;
	private String typeName;
	private String typeId;
	
	private String overhaulPayPlan; //大修用款计划
	private String overhaulActualPriceGroup;	//大修实际费用(集团)
	private String overhaulActualPriceOffice;	//大修实际费用(交港局)
	private String overhaulOccupyRate;	//大修计划实际占用比例
	
	private String renovatePayPlan; //更新改造用款计划
	private String renovateActualPriceGroup;	//更新改造实际费用(集团)
	private String renovateActualPriceOffice;	//更新改造实际费用(集团)
	private String renovateOccupyRate;		//更新改造计划实际占用比例
	
	private Date createDate;				//创建时间
	
	
	public DwOverhaulMajorTypeBo(DwOverhaulMajorType mt){
		this.id = mt.getId();
		this.year = mt.getYear();
		this.typeName = mt.getTypeName();
		this.typeId = mt.getTypeId();
		
		this.overhaulPayPlan = mt.getOverhaulPayPlan()==null?"":(mt.getOverhaulPayPlan()==0?"0":(mt.getOverhaulPayPlan()+""));
		this.overhaulActualPriceGroup = mt.getOverhaulActualPriceGroup()==null?"":(mt.getOverhaulActualPriceGroup()==0?"0":(mt.getOverhaulActualPriceGroup()+""));
		this.overhaulActualPriceOffice = mt.getOverhaulActualPriceOffice()==null?"":(mt.getOverhaulActualPriceOffice()==0?"0":(mt.getOverhaulActualPriceOffice()+""));
		this.overhaulOccupyRate = mt.getOverhaulOccupyRate()==null?"":(mt.getOverhaulActualPriceOffice()+"");
		
		this.renovatePayPlan = mt.getRenovatePayPlan()==null?"":(mt.getRenovatePayPlan()==0?"0":(mt.getRenovatePayPlan()+""));
		this.renovateActualPriceGroup = mt.getRenovateActualPriceGroup()==null?"":(mt.getRenovateActualPriceGroup()==0?"0":(mt.getRenovateActualPriceGroup()+""));
		this.renovateActualPriceOffice = mt.getRenovateOccupyRate();
		this.renovateOccupyRate = mt.getRenovateOccupyRate();
		this.createDate = mt.getCreateDate();
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getOverhaulPayPlan() {
		return overhaulPayPlan;
	}
	public void setOverhaulPayPlan(String overhaulPayPlan) {
		this.overhaulPayPlan = overhaulPayPlan;
	}
	
	public String getOverhaulActualPriceGroup() {
		return overhaulActualPriceGroup;
	}
	public void setOverhaulActualPriceGroup(String overhaulActualPriceGroup) {
		this.overhaulActualPriceGroup = overhaulActualPriceGroup;
	}
	
	public String getOverhaulActualPriceOffice() {
		return overhaulActualPriceOffice;
	}
	public void setOverhaulActualPriceOffice(String overhaulActualPriceOffice) {
		this.overhaulActualPriceOffice = overhaulActualPriceOffice;
	}
	
	public String getOverhaulOccupyRate() {
		return overhaulOccupyRate;
	}
	public void setOverhaulOccupyRate(String overhaulOccupyRate) {
		this.overhaulOccupyRate = overhaulOccupyRate;
	}
	
	public String getRenovatePayPlan() {
		return renovatePayPlan;
	}
	public void setRenovatePayPlan(String renovatePayPlan) {
		this.renovatePayPlan = renovatePayPlan;
	}
	
	public String getRenovateActualPriceGroup() {
		return renovateActualPriceGroup;
	}
	public void setRenovateActualPriceGroup(String renovateActualPriceGroup) {
		this.renovateActualPriceGroup = renovateActualPriceGroup;
	}
	
	public String getRenovateActualPriceOffice() {
		return renovateActualPriceOffice;
	}
	public void setRenovateActualPriceOffice(String renovateActualPriceOffice) {
		this.renovateActualPriceOffice = renovateActualPriceOffice;
	}
	
	public String getRenovateOccupyRate() {
		return renovateOccupyRate;
	}
	public void setRenovateOccupyRate(String renovateOccupyRate) {
		this.renovateOccupyRate = renovateOccupyRate;
	}
	
}
