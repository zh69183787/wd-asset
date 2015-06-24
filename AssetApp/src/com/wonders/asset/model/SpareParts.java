package com.wonders.asset.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "T_SPARE_PARTS")
@SuppressWarnings("serial")
public class SpareParts implements Serializable{
	private String id;
	private Date createTime;
	private String projectName;
	private String projectNo;
	private String projectContractNo;
	private String spareAssetNo;
	private String spareAssetDescription;
	private String at1;
	private String at2;
	private String at3;
	private String unitCode;
	private Double assetQty;
	private String finGzxh;
	private String productArea;
	private String manufacturer;
	private String supplier;
	private Date leaveFactory;
	
	private Date supplyDate;
	private String ownershipUnit;
	private String ownershipUnitName;
	private String useUnit;
	private String useUnitName;
	private String maintainDepart;
	private String maintainDepartName;
	private String locLine;
	private String locationCode;
	private Date transferDate;
	private String designUseLife;
	private String warrantyPeriod;
	private Double factoryPrice;
	private Double contractPrice;
	private Double originalValue;
	private String haveList;
	private String equipList;
	private String completeTransAssetType;
	private String operateProjectAsset;
	private Date operateProjectAssetDate;
	private Double operateProjectAssetAccount;
	private String projectAppDocNo;
	private String remarks;
	private String version;
	private Date modifyTime;
	private String publish;
	private Integer no;
	
	
	
	
	@Transient
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public SpareParts() {
		super();
	}
	public SpareParts(String id, Date createTime, String projectName,
			String projectNo, String projectContractNo, String spareAssetNo,
			String spareAssetDescription, String at1, String at2, String at3,
			String unitCode, Double assetQty, String finGzxh,
			String productArea, String manufacturer, String supplier,
			Date leaveFactory, Date supplyDate, String ownershipUnit,
			String ownershipUnitName, String useUnit, String useUnitName,
			String maintainDepart, String maintainDepartName, String locLine,
			String locationCode, Date transferDate, String designUseLife,
			String warrantyPeriod, Double factoryPrice, Double contractPrice,
			Double originalValue, String haveList, String equipList,
			String completeTransAssetType, String operateProjectAsset,
			Date operateProjectAssetDate, Double operateProjectAssetAccount,
			String projectAppDocNo, String remarks, String version,
			Date modifyTime, String publish) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.projectName = projectName;
		this.projectNo = projectNo;
		this.projectContractNo = projectContractNo;
		this.spareAssetNo = spareAssetNo;
		this.spareAssetDescription = spareAssetDescription;
		this.at1 = at1;
		this.at2 = at2;
		this.at3 = at3;
		this.unitCode = unitCode;
		this.assetQty = assetQty;
		this.finGzxh = finGzxh;
		this.productArea = productArea;
		this.manufacturer = manufacturer;
		this.supplier = supplier;
		this.leaveFactory = leaveFactory;
		this.supplyDate = supplyDate;
		this.ownershipUnit = ownershipUnit;
		this.ownershipUnitName = ownershipUnitName;
		this.useUnit = useUnit;
		this.useUnitName = useUnitName;
		this.maintainDepart = maintainDepart;
		this.maintainDepartName = maintainDepartName;
		this.locLine = locLine;
		this.locationCode = locationCode;
		this.transferDate = transferDate;
		this.designUseLife = designUseLife;
		this.warrantyPeriod = warrantyPeriod;
		this.factoryPrice = factoryPrice;
		this.contractPrice = contractPrice;
		this.originalValue = originalValue;
		this.haveList = haveList;
		this.equipList = equipList;
		this.completeTransAssetType = completeTransAssetType;
		this.operateProjectAsset = operateProjectAsset;
		this.operateProjectAssetDate = operateProjectAssetDate;
		this.operateProjectAssetAccount = operateProjectAssetAccount;
		this.projectAppDocNo = projectAppDocNo;
		this.remarks = remarks;
		this.version = version;
		this.modifyTime = modifyTime;
		this.publish = publish;
	}
	
	
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 6)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="PROJECT_NAME",nullable=true,length=240)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name="PROJECT_NO",nullable=true,length=50)
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	@Column(name="PROJECT_CONTRACT_NO",nullable=true,length=200)
	public String getProjectContractNo() {
		return projectContractNo;
	}
	public void setProjectContractNo(String projectContractNo) {
		this.projectContractNo = projectContractNo;
	}
	@Column(name="SPARE_ASSET_NO",nullable=true,length=30)
	public String getSpareAssetNo() {
		return spareAssetNo;
	}
	public void setSpareAssetNo(String spareAssetNo) {
		this.spareAssetNo = spareAssetNo;
	}
	@Column(name="SPARE_ASSET_DESCRIPTION",nullable=true,length=200)
	public String getSpareAssetDescription() {
		return spareAssetDescription;
	}
	public void setSpareAssetDescription(String spareAssetDescription) {
		this.spareAssetDescription = spareAssetDescription;
	}
	@Column(name="AT1",nullable=true,length=250)
	public String getAt1() {
		return at1;
	}
	public void setAt1(String at1) {
		this.at1 = at1;
	}
	@Column(name="AT2",nullable=true,length=250)
	public String getAt2() {
		return at2;
	}
	public void setAt2(String at2) {
		this.at2 = at2;
	}
	@Column(name="AT3",nullable=true,length=250)
	public String getAt3() {
		return at3;
	}
	public void setAt3(String at3) {
		this.at3 = at3;
	}
	@Column(name="UNIT_CODE",nullable=true,length=30)
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	@Column(name = "ASSET_QTY", precision = 20, scale = 6)
	public Double getAssetQty() {
		return assetQty;
	}
	public void setAssetQty(Double assetQty) {
		this.assetQty = assetQty;
	}
	@Column(name="FIN_GZXH",nullable=true,length=500)
	public String getFinGzxh() {
		return finGzxh;
	}
	public void setFinGzxh(String finGzxh) {
		this.finGzxh = finGzxh;
	}
	@Column(name="PRODUCT_AREA",nullable=true,length=50)
	public String getProductArea() {
		return productArea;
	}
	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	@Column(name="MANUFACTURER",nullable=true,length=200)
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	@Column(name="SUPPLIER",nullable=true,length=100)
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	@Column(name="LEAVE_FACTORY")
	public Date getLeaveFactory() {
		return leaveFactory;
	}
	public void setLeaveFactory(Date leaveFactory) {
		this.leaveFactory = leaveFactory;
	}
	@Column(name = "SUPPLY_DATE")
	public Date getSupplyDate() {
		return supplyDate;
	}
	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}
	@Column(name="OWNERSHIP_UNIT",nullable=true,length=20)
	public String getOwnershipUnit() {
		return ownershipUnit;
	}
	public void setOwnershipUnit(String ownershipUnit) {
		this.ownershipUnit = ownershipUnit;
	}
	@Column(name="OWNERSHIP_UNIT_NAME",nullable=true,length=300)
	public String getOwnershipUnitName() {
		return ownershipUnitName;
	}
	public void setOwnershipUnitName(String ownershipUnitName) {
		this.ownershipUnitName = ownershipUnitName;
	}
	@Column(name="USE_UNIT",nullable=true,length=20)
	public String getUseUnit() {
		return useUnit;
	}
	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}
	@Column(name="USE_UNIT_NAME",nullable=true,length=300)
	public String getUseUnitName() {
		return useUnitName;
	}
	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}
	@Column(name="MAINTAIN_DEPART",nullable=true,length=100)
	public String getMaintainDepart() {
		return maintainDepart;
	}
	public void setMaintainDepart(String maintainDepart) {
		this.maintainDepart = maintainDepart;
	}
	@Column(name="MAINTAIN_DEPART_NAME",nullable=true,length=300)
	public String getMaintainDepartName() {
		return maintainDepartName;
	}
	public void setMaintainDepartName(String maintainDepartName) {
		this.maintainDepartName = maintainDepartName;
	}
	@Column(name="LOC_LINE",nullable=true,length=20)
	public String getLocLine() {
		return locLine;
	}
	public void setLocLine(String locLine) {
		this.locLine = locLine;
	}
	@Column(name="LOCATION_CODE",nullable=true,length=20)
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	@Column(name = "TRANSFER_DATE")
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	@Column(name="DESIGN_USE_LIFE",nullable=true,length=50)
	public String getDesignUseLife() {
		return designUseLife;
	}
	public void setDesignUseLife(String designUseLife) {
		this.designUseLife = designUseLife;
	}
	@Column(name="WARRANTY_PERIOD")
	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	@Column(name = "FACTORY_PRICE", precision = 20, scale = 6)
	public Double getFactoryPrice() {
		return factoryPrice;
	}
	public void setFactoryPrice(Double factoryPrice) {
		this.factoryPrice = factoryPrice;
	}
	@Column(name = "CONTRACT_PRICE", precision = 20, scale = 6)
	public Double getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}
	@Column(name = "ORIGINAL_VALUE", precision = 20, scale = 6)
	public Double getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}
	@Column(name="HAVE_LIST",nullable=true,length=5)
	public String getHaveList() {
		return haveList;
	}
	public void setHaveList(String haveList) {
		this.haveList = haveList;
	}
	@Column(name="EQUIP_LIST",nullable=true,length=1000)
	public String getEquipList() {
		return equipList;
	}
	public void setEquipList(String equipList) {
		this.equipList = equipList;
	}
	@Column(name="COMPLETE_TRANS_ASSET_TYPE",nullable=true,length=20)
	public String getCompleteTransAssetType() {
		return completeTransAssetType;
	}
	public void setCompleteTransAssetType(String completeTransAssetType) {
		this.completeTransAssetType = completeTransAssetType;
	}
	@Column(name="OPERATE_PROJECT_ASSET",nullable=true,length=20)
	public String getOperateProjectAsset() {
		return operateProjectAsset;
	}
	public void setOperateProjectAsset(String operateProjectAsset) {
		this.operateProjectAsset = operateProjectAsset;
	}
	@Column(name = "OPERATE_PROJECT_ASSET_DATE")
	public Date getOperateProjectAssetDate() {
		return operateProjectAssetDate;
	}
	public void setOperateProjectAssetDate(Date operateProjectAssetDate) {
		this.operateProjectAssetDate = operateProjectAssetDate;
	}
	@Column(name = "OPERATE_PROJECT_ASSET_ACCOUNT", precision = 20, scale = 6)
	public Double getOperateProjectAssetAccount() {
		return operateProjectAssetAccount;
	}
	public void setOperateProjectAssetAccount(Double operateProjectAssetAccount) {
		this.operateProjectAssetAccount = operateProjectAssetAccount;
	}
	@Column(name="PROJECT_APP_DOC_NO",nullable=true,length=400)
	public String getProjectAppDocNo() {
		return projectAppDocNo;
	}
	public void setProjectAppDocNo(String projectAppDocNo) {
		this.projectAppDocNo = projectAppDocNo;
	}
	@Column(name="REMARKS",nullable=true,length=2000)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="VERSION",nullable=true,length=100)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFY_TIME", length = 6)
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	@Column(name="PUBLISH",nullable=true,length=1)
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
}
