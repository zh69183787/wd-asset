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
 * 大修更新改造项目总体情况
 * 
 * @author Kai Yao
 * @date 2013-11-20
 */
@Entity
@Table(name = "DW_OVERHAUL_PROJECT_STAT")
@SuppressWarnings("serial")
public class DwOverhaulProjectStat implements Serializable {

	private String id;
	private String overhaultCount; // 大修项目个数
	private String overhaultPrice; // 大修项目费用
	private String overhaultCountThisYear;	//当年大修项目个数
	private String overhaultPriceThisYear; // 当年大修项目费用
	private String renovateCount;// 更新改造项目个数
	private String renovatePrice;// 更新改造项目费用
	private String renovateCountThisYear;// 当年更新改造项目个数
	private String renovatePriceThisYear;// 当年更新改造项目费用
	private String newAssetCount;	//新增资产项数
	private String newAssetPrice;	//新增资产费用
	private String newAssetCountThisYear;	//当年新增资产项数
	private String newAssetPriceThisYear;	//当年新增资产费用
	
	
	private String useAssetCount; // 使用资产项数
	private String useAssetValue; // 使用资产原值
	private String scrapAssetCount; // 报废资产项数
	private String scrapAssetValue; // 报废资产原值
	private String stopAssetCount; // 停用资产项数
	private String stopAssetValue; // 停用资产原值
	
	private String rentAssetCount; // 租赁资产项数
	private String rentAssetValue; // 租赁资产项数
	private String unusedAssetCount; // 闲置资产项数
	private String unusedAssetValue; // 闲置资产项数
	private String allotAssetCount; // 调拨资产项数
	private String allotAssetValue; // 调拨资产项数
	
	private String checkedAssetCount; // 盘点资产总数
	private String checkedResultCount; // 盘点结果个数
	private Date createDate;

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

	@Column(name = "RENOVATE_COUNT", length = 50)
	public String getRenovateCount() {
		return renovateCount;
	}

	public void setRenovateCount(String renovateCount) {
		this.renovateCount = renovateCount;
	}

	@Column(name = "RENOVATE_PRICE", length = 50)
	public String getRenovatePrice() {
		return renovatePrice;
	}

	public void setRenovatePrice(String renovatePrice) {
		this.renovatePrice = renovatePrice;
	}

	@Column(name = "OVERHAULT_COUNT", length = 50)
	public String getOverhaultCount() {
		return overhaultCount;
	}

	public void setOverhaultCount(String overhaultCount) {
		this.overhaultCount = overhaultCount;
	}

	@Column(name = "OVERHAULT_PRICE", length = 50)
	public String getOverhaultPrice() {
		return overhaultPrice;
	}

	public void setOverhaultPrice(String overhaultPrice) {
		this.overhaultPrice = overhaultPrice;
	}

	@Column(name = "USE_ASSET_COUNT", length = 50)
	public String getUseAssetCount() {
		return useAssetCount;
	}

	public void setUseAssetCount(String useAssetCount) {
		this.useAssetCount = useAssetCount;
	}

	@Column(name = "USE_ASSET_VALUE", length = 50)
	public String getUseAssetValue() {
		return useAssetValue;
	}

	public void setUseAssetValue(String useAssetValue) {
		this.useAssetValue = useAssetValue;
	}

	@Column(name = "SCRAP_ASSET_COUNT", length = 50)
	public String getScrapAssetCount() {
		return scrapAssetCount;
	}

	public void setScrapAssetCount(String scrapAssetCount) {
		this.scrapAssetCount = scrapAssetCount;
	}

	@Column(name = "SCRAP_ASSET_VALUE", length = 50)
	public String getScrapAssetValue() {
		return scrapAssetValue;
	}

	public void setScrapAssetValue(String scrapAssetValue) {
		this.scrapAssetValue = scrapAssetValue;
	}

	@Column(name = "STOP_ASSET_COUNT", length = 50)
	public String getStopAssetCount() {
		return stopAssetCount;
	}

	public void setStopAssetCount(String stopAssetCount) {
		this.stopAssetCount = stopAssetCount;
	}

	@Column(name = "STOP_ASSET_VALUE", length = 50)
	public String getStopAssetValue() {
		return stopAssetValue;
	}

	public void setStopAssetValue(String stopAssetValue) {
		this.stopAssetValue = stopAssetValue;
	}

	@Column(name = "RENT_ASSET_COUNT", length = 50)
	public String getRentAssetCount() {
		return rentAssetCount;
	}

	public void setRentAssetCount(String rentAssetCount) {
		this.rentAssetCount = rentAssetCount;
	}

	@Column(name = "RENT_ASSET_VALUE", length = 50)
	public String getRentAssetValue() {
		return rentAssetValue;
	}

	public void setRentAssetValue(String rentAssetValue) {
		this.rentAssetValue = rentAssetValue;
	}

	@Column(name = "UNUSED_ASSET_COUNT", length = 50)
	public String getUnusedAssetCount() {
		return unusedAssetCount;
	}

	public void setUnusedAssetCount(String unusedAssetCount) {
		this.unusedAssetCount = unusedAssetCount;
	}

	@Column(name = "UNUSED_ASSET_VALUE", length = 50)
	public String getUnusedAssetValue() {
		return unusedAssetValue;
	}

	public void setUnusedAssetValue(String unusedAssetValue) {
		this.unusedAssetValue = unusedAssetValue;
	}

	@Column(name = "ALLOT_ASSET_COUNT", length = 50)
	public String getAllotAssetCount() {
		return allotAssetCount;
	}

	public void setAllotAssetCount(String allotAssetCount) {
		this.allotAssetCount = allotAssetCount;
	}

	@Column(name = "ALLOT_ASSET_VALUE", length = 50)
	public String getAllotAssetValue() {
		return allotAssetValue;
	}

	public void setAllotAssetValue(String allotAssetValue) {
		this.allotAssetValue = allotAssetValue;
	}

	@Column(name = "CHECKED_ASSET_COUNT", length = 50)
	public String getCheckedAssetCount() {
		return checkedAssetCount;
	}

	public void setCheckedAssetCount(String checkedAssetCount) {
		this.checkedAssetCount = checkedAssetCount;
	}

	@Column(name = "CHECKED_RESULT_COUNT", length = 50)
	public String getCheckedResultCount() {
		return checkedResultCount;
	}

	public void setCheckedResultCount(String checkedResultCount) {
		this.checkedResultCount = checkedResultCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "OVERHAULT_COUNT_THIS_YEAR", length = 50)
	public String getOverhaultCountThisYear() {
		return overhaultCountThisYear;
	}

	public void setOverhaultCountThisYear(String overhaultCountThisYear) {
		this.overhaultCountThisYear = overhaultCountThisYear;
	}

	@Column(name = "OVERHAULT_PRICE_THIS_YEAR", length = 50)
	public String getOverhaultPriceThisYear() {
		return overhaultPriceThisYear;
	}

	public void setOverhaultPriceThisYear(String overhaultPriceThisYear) {
		this.overhaultPriceThisYear = overhaultPriceThisYear;
	}

	@Column(name = "RENOVATE_COUNT_THIS_YEAR", length = 50)
	public String getRenovateCountThisYear() {
		return renovateCountThisYear;
	}

	public void setRenovateCountThisYear(String renovateCountThisYear) {
		this.renovateCountThisYear = renovateCountThisYear;
	}

	@Column(name = "RENOVATE_PRICE_THIS_YEAR", length = 50)
	public String getRenovatePriceThisYear() {
		return renovatePriceThisYear;
	}

	public void setRenovatePriceThisYear(String renovatePriceThisYear) {
		this.renovatePriceThisYear = renovatePriceThisYear;
	}

	@Column(name = "NEW_ASSET_COUNT", length = 50)
	public String getNewAssetCount() {
		return newAssetCount;
	}

	public void setNewAssetCount(String newAssetCount) {
		this.newAssetCount = newAssetCount;
	}

	@Column(name = "NEW_ASSET_PRICE", length = 50)
	public String getNewAssetPrice() {
		return newAssetPrice;
	}

	public void setNewAssetPrice(String newAssetPrice) {
		this.newAssetPrice = newAssetPrice;
	}

	@Column(name = "NEW_ASSET_COUNT_THIS_YEAR", length = 50)
	public String getNewAssetCountThisYear() {
		return newAssetCountThisYear;
	}

	public void setNewAssetCountThisYear(String newAssetCountThisYear) {
		this.newAssetCountThisYear = newAssetCountThisYear;
	}

	@Column(name = "NEW_ASSET_PRICE_THIS_YEAR", length = 50)
	public String getNewAssetPriceThisYear() {
		return newAssetPriceThisYear;
	}

	public void setNewAssetPriceThisYear(String newAssetPriceThisYear) {
		this.newAssetPriceThisYear = newAssetPriceThisYear;
	}

}
