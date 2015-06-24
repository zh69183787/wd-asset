package com.wonders.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_ASSET_PRICE")
@SuppressWarnings("serial")
public class AssetPrice extends SystemInformation{

	private String updateId;
	//private String assetId;
	private Double factoryPrice;			//出厂价
	private Double contractPrice;			//合同价
	private Double originalValue;			//原值
	private Double finalPrice;				//决算价
	
	
	private Double periodDepreciation;		//本期折旧
	private Double accumulatedDepreciation;		//累计折旧
	private Double netValue;				//净值
	private Double residualRate;			//残值率
	private String depreciationMethod;		//折旧方法
	//private Asset asset;
	
	@Column(name="UPDATE_ID",nullable=true,length=40)
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	/*
	@Column(name="ASSET_ID",nullable=true,length=40)
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}*/
	
	@Column(name="FACTORY_PRICE",nullable=true)
	public Double getFactoryPrice() {
		return factoryPrice;
	}
	public void setFactoryPrice(Double factoryPrice) {
		this.factoryPrice = factoryPrice;
	}
	
	@Column(name="CONTRACT_PRICE",nullable=true)
	public Double getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}
	
	@Column(name="ORIGINAL_VALUE",nullable=true)
	public Double getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}
	
	@Column(name="FINAL_PRICE",nullable=true)
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	@Column(name="PERIOD_DEPRECIATION",nullable=true)
	public Double getPeriodDepreciation() {
		return periodDepreciation;
	}
	public void setPeriodDepreciation(Double periodDepreciation) {
		this.periodDepreciation = periodDepreciation;
	}
	
	@Column(name="ACCUMULATED_DEPRECIATION",nullable=true)
	public Double getAccumulatedDepreciation() {
		return accumulatedDepreciation;
	}
	public void setAccumulatedDepreciation(Double accumulatedDepreciation) {
		this.accumulatedDepreciation = accumulatedDepreciation;
	}
	
	@Column(name="NET_VALUE",nullable=true)
	public Double getNetValue() {
		return netValue;
	}
	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}
	
	@Column(name="RESIDUAL_RATE",nullable=true)
	public Double getResidualRate() {
		return residualRate;
	}
	public void setResidualRate(Double residualRate) {
		this.residualRate = residualRate;
	}
	
	@Column(name="DEPRECIATION_MeTHOD",nullable=true)
	public String getDepreciationMethod() {
		return depreciationMethod;
	}
	public void setDepreciationMethod(String depreciationMethod) {
		this.depreciationMethod = depreciationMethod;
	}
	
	/*@ManyToOne
	@JoinColumn(name="ASSET_ID")
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}*/
	
	
	
}
