package com.wonders.asset.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * THouseAsset entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_HOUSE_ASSET")
public class HouseAsset implements java.io.Serializable {

	// Fields

	private String houseAssetId;
	private String contractNo;
	private String cardId;
	private String project;
	private String assetNo;
	private String assetName;
	private String assetType;
	private String locationNo;
	private String locationName;
	private String unit;
	private Long quantity;
	private Date completionDate;
	private Long assetLifeYear;
	private String certificateNo;
	private Double projectValue;
	private String ancillaryFacilities;
	private String contractUnit;
	private String designUnit;
	private String constructionUnit;
	private String supervisingCompany;
	private String ownershipUnit;
	private String warrantsNo;
	private Double assetValue;
	private Date transferTime;
	private String transDep;
	private String transDepleader;
	private String takeoverLeader;
	private Date importTime;
	private String importPerson;
	private String note;
	private String keepName;
	private Date keepDate;
	private String rekeepName;
	private Date rekeepDate;
	private String useName;
	private Date useDate;
	private String scrapName;
	private Date scrapDate;
	private String classificationName;
	private String state;
	private String line;
	private String builderProject;
	private String station;
	private Double buildArea;
	private Long groundFloor;
	private Long undergroundFloor;
	private String completedLicense;
	private String planLicense;
	private String propertyRightNo;
	private Date createTime;
	private String updater;
	private Date updateTime;
	private String creator;
	private String publish;
	private String id;
	private String houseType;
	
	private List<AreaInfo> areaInfos;

	// Constructors

	/** default constructor */
	public HouseAsset() {
	}

	/** full constructor */
	public HouseAsset(String contractNo, String cardId, String project,
			String assetNo, String assetName, String assetType,
			String locationNo, String unit, Long quantity, Date completionDate,
			Long assetLifeYear, String certificateNo, Double projectValue,
			String ancillaryFacilities, String contractUnit, String designUnit,
			String constructionUnit, String supervisingCompany,
			String ownershipUnit, String warrantsNo, Double assetValue,
			Date transferTime, String transDep, String transDepleader,
			String takeoverLeader, Date importTime, String importPerson,
			String note, String keepName, Date keepDate, String rekeepName,
			Date rekeepDate, String useName, Date useDate, String scrapName,
			Date scrapDate, String classificationName, String state,
			String line, String builderProject, String station,
			Double buildArea, Long groundFloor, Long undergroundFloor,
			String completedLicense, String planLicense,
			String propertyRightNo, Date createTime, String updater,
			Date updateTime, String creator, String publish, String id,String houseType) {
		this.contractNo = contractNo;
		this.cardId = cardId;
		this.project = project;
		this.assetNo = assetNo;
		this.assetName = assetName;
		this.assetType = assetType;
		this.locationNo = locationNo;
		this.unit = unit;
		this.quantity = quantity;
		this.completionDate = completionDate;
		this.assetLifeYear = assetLifeYear;
		this.certificateNo = certificateNo;
		this.projectValue = projectValue;
		this.ancillaryFacilities = ancillaryFacilities;
		this.contractUnit = contractUnit;
		this.designUnit = designUnit;
		this.constructionUnit = constructionUnit;
		this.supervisingCompany = supervisingCompany;
		this.ownershipUnit = ownershipUnit;
		this.warrantsNo = warrantsNo;
		this.assetValue = assetValue;
		this.transferTime = transferTime;
		this.transDep = transDep;
		this.transDepleader = transDepleader;
		this.takeoverLeader = takeoverLeader;
		this.importTime = importTime;
		this.importPerson = importPerson;
		this.note = note;
		this.keepName = keepName;
		this.keepDate = keepDate;
		this.rekeepName = rekeepName;
		this.rekeepDate = rekeepDate;
		this.useName = useName;
		this.useDate = useDate;
		this.scrapName = scrapName;
		this.scrapDate = scrapDate;
		this.classificationName = classificationName;
		this.state = state;
		this.line = line;
		this.builderProject = builderProject;
		this.station = station;
		this.buildArea = buildArea;
		this.groundFloor = groundFloor;
		this.undergroundFloor = undergroundFloor;
		this.completedLicense = completedLicense;
		this.planLicense = planLicense;
		this.propertyRightNo = propertyRightNo;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.creator = creator;
		this.publish = publish;
		this.id = id;
		this.houseType = houseType;
	}

	// Property accessors
	@Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")

	@Column(name = "HOUSE_ASSET_ID", unique = true, nullable = false, length = 100)
	public String getHouseAssetId() {
		return this.houseAssetId;
	}

	public void setHouseAssetId(String houseAssetId) {
		this.houseAssetId = houseAssetId;
	}

	@Column(name = "CONTRACT_NO", length = 50)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CARD_ID", length = 20)
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(name = "PROJECT", length = 200)
	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Column(name = "ASSET_NO", length = 30)
	public String getAssetNo() {
		return this.assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	@Column(name = "ASSET_NAME", length = 200)
	public String getAssetName() {
		return this.assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	@Column(name = "ASSET_TYPE", length = 40)
	public String getAssetType() {
		return this.assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	@Column(name = "LOCATION_NO", length = 200)
	public String getLocationNo() {
		return this.locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	@Column(name = "UNIT", length = 200)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "QUANTITY", precision = 10, scale = 0)
	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COMPLETION_DATE", length = 7)
	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	@Column(name = "ASSET_LIFE_YEAR", precision = 10, scale = 0)
	public Long getAssetLifeYear() {
		return this.assetLifeYear;
	}

	public void setAssetLifeYear(Long assetLifeYear) {
		this.assetLifeYear = assetLifeYear;
	}

	@Column(name = "CERTIFICATE_NO", length = 30)
	public String getCertificateNo() {
		return this.certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@Column(name = "PROJECT_VALUE", precision = 20, scale = 6)
	public Double getProjectValue() {
		return this.projectValue;
	}

	public void setProjectValue(Double projectValue) {
		this.projectValue = projectValue;
	}

	@Column(name = "ANCILLARY_FACILITIES", length = 200)
	public String getAncillaryFacilities() {
		return this.ancillaryFacilities;
	}

	public void setAncillaryFacilities(String ancillaryFacilities) {
		this.ancillaryFacilities = ancillaryFacilities;
	}

	@Column(name = "CONTRACT_UNIT", length = 200)
	public String getContractUnit() {
		return this.contractUnit;
	}

	public void setContractUnit(String contractUnit) {
		this.contractUnit = contractUnit;
	}

	@Column(name = "DESIGN_UNIT", length = 200)
	public String getDesignUnit() {
		return this.designUnit;
	}

	public void setDesignUnit(String designUnit) {
		this.designUnit = designUnit;
	}

	@Column(name = "CONSTRUCTION_UNIT", length = 200)
	public String getConstructionUnit() {
		return this.constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "SUPERVISING_COMPANY", length = 200)
	public String getSupervisingCompany() {
		return this.supervisingCompany;
	}

	public void setSupervisingCompany(String supervisingCompany) {
		this.supervisingCompany = supervisingCompany;
	}

	@Column(name = "OWNERSHIP_UNIT", length = 200)
	public String getOwnershipUnit() {
		return this.ownershipUnit;
	}

	public void setOwnershipUnit(String ownershipUnit) {
		this.ownershipUnit = ownershipUnit;
	}

	@Column(name = "WARRANTS_NO", length = 20)
	public String getWarrantsNo() {
		return this.warrantsNo;
	}

	public void setWarrantsNo(String warrantsNo) {
		this.warrantsNo = warrantsNo;
	}

	@Column(name = "ASSET_VALUE", precision = 20, scale = 6)
	public Double getAssetValue() {
		return this.assetValue;
	}

	public void setAssetValue(Double assetValue) {
		this.assetValue = assetValue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRANSFER_TIME", length = 7)
	public Date getTransferTime() {
		return this.transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	@Column(name = "TRANS_DEP", length = 200)
	public String getTransDep() {
		return this.transDep;
	}

	public void setTransDep(String transDep) {
		this.transDep = transDep;
	}

	@Column(name = "TRANS_DEPLEADER", length = 200)
	public String getTransDepleader() {
		return this.transDepleader;
	}

	public void setTransDepleader(String transDepleader) {
		this.transDepleader = transDepleader;
	}

	@Column(name = "TAKEOVER_LEADER", length = 200)
	public String getTakeoverLeader() {
		return this.takeoverLeader;
	}

	public void setTakeoverLeader(String takeoverLeader) {
		this.takeoverLeader = takeoverLeader;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "IMPORT_TIME", length = 7)
	public Date getImportTime() {
		return this.importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	@Column(name = "IMPORT_PERSON", length = 200)
	public String getImportPerson() {
		return this.importPerson;
	}

	public void setImportPerson(String importPerson) {
		this.importPerson = importPerson;
	}

	@Column(name = "NOTE", length = 250)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "KEEP_NAME", length = 200)
	public String getKeepName() {
		return this.keepName;
	}

	public void setKeepName(String keepName) {
		this.keepName = keepName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "KEEP_DATE", length = 7)
	public Date getKeepDate() {
		return this.keepDate;
	}

	public void setKeepDate(Date keepDate) {
		this.keepDate = keepDate;
	}

	@Column(name = "REKEEP_NAME", length = 200)
	public String getRekeepName() {
		return this.rekeepName;
	}

	public void setRekeepName(String rekeepName) {
		this.rekeepName = rekeepName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REKEEP_DATE", length = 7)
	public Date getRekeepDate() {
		return this.rekeepDate;
	}

	public void setRekeepDate(Date rekeepDate) {
		this.rekeepDate = rekeepDate;
	}

	@Column(name = "USE_NAME", length = 200)
	public String getUseName() {
		return this.useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USE_DATE", length = 7)
	public Date getUseDate() {
		return this.useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	@Column(name = "SCRAP_NAME", length = 200)
	public String getScrapName() {
		return this.scrapName;
	}

	public void setScrapName(String scrapName) {
		this.scrapName = scrapName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCRAP_DATE", length = 7)
	public Date getScrapDate() {
		return this.scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	@Column(name = "CLASSIFICATION_NAME", length = 200)
	public String getClassificationName() {
		return this.classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	@Column(name = "STATE", length = 4000)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "LINE", length = 200)
	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "BUILDER_PROJECT", length = 200)
	public String getBuilderProject() {
		return this.builderProject;
	}

	public void setBuilderProject(String builderProject) {
		this.builderProject = builderProject;
	}

	@Column(name = "STATION", length = 200)
	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Column(name = "BUILD_AREA", precision = 20, scale = 6)
	public Double getBuildArea() {
		return this.buildArea;
	}

	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}

	@Column(name = "GROUND_FLOOR", precision = 10, scale = 0)
	public Long getGroundFloor() {
		return this.groundFloor;
	}

	public void setGroundFloor(Long groundFloor) {
		this.groundFloor = groundFloor;
	}

	@Column(name = "UNDERGROUND_FLOOR", precision = 10, scale = 0)
	public Long getUndergroundFloor() {
		return this.undergroundFloor;
	}

	public void setUndergroundFloor(Long undergroundFloor) {
		this.undergroundFloor = undergroundFloor;
	}

	@Column(name = "COMPLETED_LICENSE", length = 100)
	public String getCompletedLicense() {
		return this.completedLicense;
	}

	public void setCompletedLicense(String completedLicense) {
		this.completedLicense = completedLicense;
	}

	@Column(name = "PLAN_LICENSE", length = 100)
	public String getPlanLicense() {
		return this.planLicense;
	}

	public void setPlanLicense(String planLicense) {
		this.planLicense = planLicense;
	}

	@Column(name = "PROPERTY_RIGHT_NO", length = 100)
	public String getPropertyRightNo() {
		return this.propertyRightNo;
	}

	public void setPropertyRightNo(String propertyRightNo) {
		this.propertyRightNo = propertyRightNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATER", length = 100)
	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "CREATOR", length = 100)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "PUBLISH", length = 1)
	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	@Column(name = "ID", length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "LOCATION_NAME", length = 200)
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@OneToMany(targetEntity=AreaInfo.class)
	@JoinColumn(name = "HOUSE_ASSET_ID")
	public List<AreaInfo> getAreaInfos() {
		return areaInfos;
	}

	public void setAreaInfos(List<AreaInfo> areaInfos) {
		this.areaInfos = areaInfos;
	}

	@Column(name = "HOUSE_TYPE", length = 2)
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
	

	
}