package com.wonders.api.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

public class AssetRecordDto {
	
	private String assetNo;
	private String assetName;
	private String operateProjectAsset;
	private Timestamp operateProjectAssetDate;
	private BigDecimal originalValue;
	private BigDecimal netValue;
	private String useLife;
	private String maintainCost;
	private String assetType;
    private String completeTransAssetType;
	private String id;
	
	private String assetCodeType;
	
	@Column(name="ASSET_CODE_TYPE",nullable=true,length=1)
	public String getAssetCodeType() {
		return assetCodeType;
	}
	public void setAssetCodeType(String assetCodeType) {
		this.assetCodeType = assetCodeType;
	}
	
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getOperateProjectAsset() {
		return operateProjectAsset;
	}
	public void setOperateProjectAsset(String operateProjectAsset) {
		this.operateProjectAsset = operateProjectAsset;
	}
	public Timestamp getOperateProjectAssetDate() {
		return operateProjectAssetDate;
	}
	public void setOperateProjectAssetDate(Timestamp operateProjectAssetDate) {
		this.operateProjectAssetDate = operateProjectAssetDate;
	}

    public String getCompleteTransAssetType() {
        return completeTransAssetType;
    }

    public void setCompleteTransAssetType(String completeTransAssetType) {
        this.completeTransAssetType = completeTransAssetType;
    }

    public BigDecimal getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(BigDecimal originalValue) {
		this.originalValue = originalValue;
	}
	public BigDecimal getNetValue() {
		return netValue;
	}
	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}
	public String getUseLife() {
		return useLife;
	}
	public void setUseLife(String useLife) {
		this.useLife = useLife;
	}
	public String getMaintainCost() {
		return maintainCost;
	}
	public void setMaintainCost(String maintainCost) {
		this.maintainCost = maintainCost;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
