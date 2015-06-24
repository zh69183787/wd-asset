package com.wonders.asset.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="T_ASSET")
@SuppressWarnings("serial")
public class Asset extends SystemInformation{

	private String assetCode;
	private String name;
	private Double count;
	private String type;
	private String manufactureCountry;
	private Date madeDate;
	private Date useDate;
	private String useLife;
	private Long expectancyLife;
	private Date WarrantyPeriod;
	
	private AssetType mainType;
	private AssetType subType;
	private AssetType DetailType;
	private UnitMaster unit;
	
	private Enterprise manufacturer;
	private Enterprise supplier;
	private AssetOwner assetOwnerInf;
	private AssetPrice assetPrice;
	
//	private AssetPrice price;
//	private Set<AssetPrice> prices;
	private AssetState state;
	private Set<Equipment> equipments;
	private Contract contract;
	private Project project;
	
	private String detailInstallSite;		//安装地点
	private Date approvalScrapDate;			//批准报废时间
	private String assetPic;				//资产图片
	private String overhaulRate;			//大修频次
	private String completeTransAssetType;	//竣工移交资产类型
	private String operateProjectAsset;		//项目资产标示
	private Date operateProjectAssetDate;	//资产交付日期
	private String projectAppDocNo;			//立项或可研批复文号
	private String projectNo;				//项目编号
	private String contractNo;				//合同编号
	private String verifyState;				//审核状态
	private String dataList;				//技术、规格、操作资料及清单
	private String equipmentList;			//设备清单
	private Double overhaulFinalPrice;		//改造决算价
	
	private Double factoryPrice;			//出厂价
	private Double contractPrice;			//合同价
	private Double originalValue;			//原值
	private String depreciationMethod;		//折旧方法
	
	
	private String mainTypeCodeId;			//大类codeId
	private String subTypeCodeId;			//中类codeId
	private String detailTypeCodeId;		//小类codeId
	
	
	private String projectContractNo;
	private String ownershipPer;
	private String usePer;
	private String locationCode ;
	private Double operateProjectAssetAccount;
	
	
	
	private Set<AssetRecord> assetRecords; 
	
	private String assetCodeType;
	
	/*private Set<AssetOverhaul> assetOverhauls;
	private Set<AssetMaintenanceCost> assetMaintenanceCosts;*/
	@Column(name="ASSET_CODE_TYPE",nullable=true,length=1)
	public String getAssetCodeType() {
		return assetCodeType;
	}
	public void setAssetCodeType(String assetCodeType) {
		this.assetCodeType = assetCodeType;
	}
	
	@Column(name="VERIFY_STATE",nullable=true,length=4)
	public String getVerifyState() {
		return verifyState;
	}
	
	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}
	@Column(name="ASSET_CODE",nullable=true,length=400)
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
	@Column(name="NAME",nullable=true,length=400)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="COUNT",nullable=true)
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
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
	
	@Column(name="MADE_DATE",nullable=true)
	public Date getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}
	
	@Column(name="USE_DATE",nullable=true)
	public Date getUseDate() {
		return useDate;
	}
	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}
	
	@Column(name="USE_LIFE",nullable=true)
	public String getUseLife() {
		return useLife;
	}
	public void setUseLife(String useLife) {
		this.useLife = useLife;
	}
	
	@Column(name="EXPECTANCY_LIFE",nullable=true)
	public Long getExpectancyLife() {
		return expectancyLife;
	}
	
	public void setExpectancyLife(Long expectancyLife) {
		this.expectancyLife = expectancyLife;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="WARRANTY_PERIOD",nullable=true,length=400)
	public Date getWarrantyPeriod() {
		return WarrantyPeriod;
	}
	public void setWarrantyPeriod(Date warrantyPeriod) {
		WarrantyPeriod = warrantyPeriod;
	}
	
	
	/*************************/
	@OneToOne
	@JoinColumn(name="PROJECT_ID")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@Transient
	public AssetType getMainType() {
		return mainType;
	}
	public void setMainType(AssetType mainType) {
		this.mainType = mainType;
	}
	
	@Transient
	public AssetType getSubType() {
		return subType;
	}
	public void setSubType(AssetType subType) {
		this.subType = subType;
	}
	
	@Transient
	public AssetType getDetailType() {
		return DetailType;
	}
	public void setDetailType(AssetType detailType) {
		DetailType = detailType;
	}
	
	@OneToOne
	@JoinColumn(name="UNIT_ID")
	public UnitMaster getUnit() {
		return unit;
	}
	public void setUnit(UnitMaster unit) {
		this.unit = unit;
	}
	
	@OneToOne
	@JoinColumn(name="MANUFACTURER_ID")
	public Enterprise getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Enterprise manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@OneToOne
	@JoinColumn(name="SUPPLIER_ID")
	public Enterprise getSupplier() {
		return supplier;
	}
	public void setSupplier(Enterprise supplier) {
		this.supplier = supplier;
	}
	@OneToOne
	@JoinColumn(name="ASSET_OWNER_INFO_ID")
	public AssetOwner getAssetOwnerInf() {
		return assetOwnerInf;
	}
	public void setAssetOwnerInf(AssetOwner assetOwnerInf) {
		this.assetOwnerInf = assetOwnerInf;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PRICE_ID")
	public AssetPrice getAssetPrice() {
		return assetPrice;
	}
	public void setAssetPrice(AssetPrice assetPrice) {
		this.assetPrice = assetPrice;
	}
	/*
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="asset")
	public Set<AssetPrice> getPrices() {
		return prices;
	}
	public void setPrices(Set<AssetPrice> prices) {
		this.prices = prices;
	}*/
	
	@OneToOne
	@JoinColumn(name="STATE_ID")
	public AssetState getState() {
		return state;
	}
	public void setState(AssetState state) {
		this.state = state;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="asset") 
	public Set<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}

	/*@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="asset") 
	public Set<AssetOverhaul> getAssetOverhauls() {
		return assetOverhauls;
	}
	public void setAssetOverhauls(Set<AssetOverhaul> assetOverhauls) {
		this.assetOverhauls = assetOverhauls;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="asset") 
	public Set<AssetMaintenanceCost> getAssetMaintenanceCosts() {
		return assetMaintenanceCosts;
	}
	public void setAssetMaintenanceCosts(
			Set<AssetMaintenanceCost> assetMaintenanceCosts) {
		this.assetMaintenanceCosts = assetMaintenanceCosts;
	}*/
	@ManyToOne
	@JoinColumn(name="contract_id")
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	@Column(name="DETAIL_INSTALL_SITE",nullable=true,length=2000)
	public String getDetailInstallSite() {
		return detailInstallSite;
	}
	public void setDetailInstallSite(String detailInstallSite) {
		this.detailInstallSite = detailInstallSite;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="APPROVAL_SCRAP_DATE",nullable=true)
	public Date getApprovalScrapDate() {
		return approvalScrapDate;
	}
	public void setApprovalScrapDate(Date approvalScrapDate) {
		this.approvalScrapDate = approvalScrapDate;
	}
	
	@Column(name="ASSET_PIC",nullable=true,length=400)
	public String getAssetPic() {
		return assetPic;
	}
	public void setAssetPic(String assetPic) {
		this.assetPic = assetPic;
	}
	
	@Column(name="OVERHAUL_RATE",nullable=true,length=400)
	public String getOverhaulRate() {
		return overhaulRate;
	}
	public void setOverhaulRate(String overhaulRate) {
		this.overhaulRate = overhaulRate;
	}
	
	@Column(name="COMPLETE_TRANS_ASSET_TYPE",nullable=true,length=400)
	public String getCompleteTransAssetType() {
		return completeTransAssetType;
	}
	public void setCompleteTransAssetType(String completeTransAssetType) {
		this.completeTransAssetType = completeTransAssetType;
	}
	
	@Column(name="OPERATE_PROJECT_ASSET",nullable=true,length=400)
	public String getOperateProjectAsset() {
		return operateProjectAsset;
	}
	public void setOperateProjectAsset(String operateProjectAsset) {
		this.operateProjectAsset = operateProjectAsset;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="OPERATE_PROJECT_ASSET_DATE",nullable=true)
	public Date getOperateProjectAssetDate() {
		return operateProjectAssetDate;
	}
	public void setOperateProjectAssetDate(Date operateProjectAssetDate) {
		this.operateProjectAssetDate = operateProjectAssetDate;
	}
	
	@Column(name="PROJECT_APP_DOC_NO",nullable=true,length=400)
	public String getProjectAppDocNo() {
		return projectAppDocNo;
	}
	public void setProjectAppDocNo(String projectAppDocNo) {
		this.projectAppDocNo = projectAppDocNo;
	}
	
	@Column(name="PROJECT_NO",nullable=true,length=2000)
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	
	@Column(name="CONTRACT",nullable=true,length=2000)
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Column(name="DATA_LIST",nullable=true,length=100)
	public String getDataList() {
		return dataList;
	}
	public void setDataList(String dataList) {
		this.dataList = dataList;
	}
	@Column(name="EQUIPMENT_LIST",nullable=true,length=100)
	public String getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(String equipmentList) {
		this.equipmentList = equipmentList;
	}
	@Column(name="OVERHAUL_FINAL_PRICE",nullable=true,length=100)
	public Double getOverhaulFinalPrice() {
		return overhaulFinalPrice;
	}
	public void setOverhaulFinalPrice(Double overhaulFinalPrice) {
		this.overhaulFinalPrice = overhaulFinalPrice;
	}
	
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
	
	@Column(name="DEPRECIATION_MeTHOD",nullable=true)
	public String getDepreciationMethod() {
		return depreciationMethod;
	}
	public void setDepreciationMethod(String depreciationMethod) {
		this.depreciationMethod = depreciationMethod;
	}
	
	@Column(name="MAIN_TYPE_CODE_ID",nullable=true)
	public String getMainTypeCodeId() {
		return mainTypeCodeId;
	}
	public void setMainTypeCodeId(String mainTypeCodeId) {
		this.mainTypeCodeId = mainTypeCodeId;
	}
	
	@Column(name="SUB_TYPE_CODE_ID",nullable=true)
	public String getSubTypeCodeId() {
		return subTypeCodeId;
	}
	public void setSubTypeCodeId(String subTypeCodeId) {
		this.subTypeCodeId = subTypeCodeId;
	}
	
	@Column(name="DETAIL_TYPE_CODE_ID",nullable=true)
	public String getDetailTypeCodeId() {
		return detailTypeCodeId;
	}
	public void setDetailTypeCodeId(String detailTypeCodeId) {
		this.detailTypeCodeId = detailTypeCodeId;
	}
	
	@Column(name="PROJECT_CONTRACT_NO",nullable=true)
	public String getProjectContractNo() {
		return projectContractNo;
	}
	public void setProjectContractNo(String projectContractNo) {
		this.projectContractNo = projectContractNo;
	}
	
	@Column(name="OWNERSHIP_PER",nullable=true)
	public String getOwnershipPer() {
		return ownershipPer;
	}
	public void setOwnershipPer(String ownershipPer) {
		this.ownershipPer = ownershipPer;
	}
	
	@Column(name="USE_PER",nullable=true)
	public String getUsePer() {
		return usePer;
	}
	public void setUsePer(String usePer) {
		this.usePer = usePer;
	}
	
	@Column(name="LOCATION_CODE",nullable=true)
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	
	@Column(name="OPERATE_PROJECT_ASSET_ACCOUNT",nullable=true)
	public Double getOperateProjectAssetAccount() {
		return operateProjectAssetAccount;
	}
	public void setOperateProjectAssetAccount(Double operateProjectAssetAccount) {
		this.operateProjectAssetAccount = operateProjectAssetAccount;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="asset")
	public Set<AssetRecord> getAssetRecords() {
		return assetRecords;
	}
	public void setAssetRecords(Set<AssetRecord> assetRecords) {
		this.assetRecords = assetRecords;
	}
	
	
	
	
}
