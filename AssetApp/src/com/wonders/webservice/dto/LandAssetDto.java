package com.wonders.webservice.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wonders.utils.DateAdapter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_LANDASSET_TMP")
public class LandAssetDto implements java.io.Serializable {

	// Fields

	private String TLandassetId;
	private String inclandRequisitionArea;
	private Double landTotalFee;
	private Double landRequisitionTotalfee;
	private String hasOpenspace;
	private String openSpacearea;
	private Double inlandRequisitionTotalfee;
	private String contractNo;
	private String contractName;
	private String cardId;
	private String assetNo;
	private String ownershipUnit;
	private String project;
	private String locationNo;
	private String assetType;
	private String assetName;
	private String area;
	private String warrantsNo;
	private Double assetValue;
	private Double bookValue;
	private String landUsecertificate;
	private String ownCertificate;
	private String note;
	private String keepName;
	private Date keepDate;
	private Date rekeepDate;
	private String useName;
	private Date useDate;
	private String scrapName;
	private Date scrapDate;
	private Date createTime;
	private Date importTime;
	private String importPerson;
	private String transDep;
	private String takeOverDep;
	private String rekeepName;
	private String classificationName;
	private String unitCode;
	private String landStatus;
	private String landLocation;
	private String builderProject;
	private String landDh;
	private String state;
	private String approveNo;
	private String approveNoOrDecide;
	private String landPlaning;
	private String buildArea;
	private String landTotalArea;
	private String landRequisitionArea;
	private String updater;
	private Date updateTime;
	private String creator;
	private String publish;
	private String id;
	private Date uploadDate;
	private String useManager;
	private String lineCodeId;
	
	
	

	
	@Column(name = "LINE_CODE_ID", length = 400)
	@XmlElement(name = "lineCodeId")
	public String getLineCodeId() {
		return lineCodeId;
	}

	public void setLineCodeId(String lineCodeId) {
		this.lineCodeId = lineCodeId;
	}

	/** default constructor */
	public LandAssetDto() {
	}

	/** full constructor */
	public LandAssetDto(String inclandRequisitionArea, Double landTotalFee,
			Double landRequisitionTotalfee, String hasOpenspace,
			String openSpacearea, Double inlandRequisitionTotalfee,
			String contractNo, String contractName, String cardId,
			String assetNo, String ownershipUnit, String project,
			String locationNo, String assetType, String assetName, String area,
			String warrantsNo, Double assetValue, Double bookValue,
			String landUsecertificate, String ownCertificate, String note,
			String keepName, Date keepDate, Date rekeepDate, String useName,
			Date useDate, String scrapName, Date scrapDate, Date createTime,
			Date importTime, String importPerson, String transDep,
			String takeOverDep, String rekeepName, String classificationName,
			String unitCode, String landStatus, String landLocation,
			String builderProject, String landDh, String state,
			String approveNo, String approveNoOrDecide, String landPlaning,
			String buildArea, String landTotalArea, String landRequisitionArea,
			String updater, Date updateTime, String creator, String publish,
			String id, Date uploadDate) {
		this.inclandRequisitionArea = inclandRequisitionArea;
		this.landTotalFee = landTotalFee;
		this.landRequisitionTotalfee = landRequisitionTotalfee;
		this.hasOpenspace = hasOpenspace;
		this.openSpacearea = openSpacearea;
		this.inlandRequisitionTotalfee = inlandRequisitionTotalfee;
		this.contractNo = contractNo;
		this.contractName = contractName;
		this.cardId = cardId;
		this.assetNo = assetNo;
		this.ownershipUnit = ownershipUnit;
		this.project = project;
		this.locationNo = locationNo;
		this.assetType = assetType;
		this.assetName = assetName;
		this.area = area;
		this.warrantsNo = warrantsNo;
		this.assetValue = assetValue;
		this.bookValue = bookValue;
		this.landUsecertificate = landUsecertificate;
		this.ownCertificate = ownCertificate;
		this.note = note;
		this.keepName = keepName;
		this.keepDate = keepDate;
		this.rekeepDate = rekeepDate;
		this.useName = useName;
		this.useDate = useDate;
		this.scrapName = scrapName;
		this.scrapDate = scrapDate;
		this.createTime = createTime;
		this.importTime = importTime;
		this.importPerson = importPerson;
		this.transDep = transDep;
		this.takeOverDep = takeOverDep;
		this.rekeepName = rekeepName;
		this.classificationName = classificationName;
		this.unitCode = unitCode;
		this.landStatus = landStatus;
		this.landLocation = landLocation;
		this.builderProject = builderProject;
		this.landDh = landDh;
		this.state = state;
		this.approveNo = approveNo;
		this.approveNoOrDecide = approveNoOrDecide;
		this.landPlaning = landPlaning;
		this.buildArea = buildArea;
		this.landTotalArea = landTotalArea;
		this.landRequisitionArea = landRequisitionArea;
		this.updater = updater;
		this.updateTime = updateTime;
		this.creator = creator;
		this.publish = publish;
		this.id = id;
		this.uploadDate = uploadDate;
	}

	// Property accessors
	@Id
	  @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")

	@Column(name = "T_LANDASSET_ID", unique = true, nullable = false, length = 32)
	public String getTLandassetId() {
		return this.TLandassetId;
	}

	public void setTLandassetId(String TLandassetId) {
		this.TLandassetId = TLandassetId;
	}

	@Column(name = "INCLAND_REQUISITION_AREA", length = 100)
	@XmlElement(name = "incLandRequisitionArea")
	public String getInclandRequisitionArea() {
		return this.inclandRequisitionArea;
	}

	public void setInclandRequisitionArea(String inclandRequisitionArea) {
		this.inclandRequisitionArea = inclandRequisitionArea;
	}

	@Column(name = "LAND_TOTAL_FEE", precision = 20, scale = 6)
	public Double getLandTotalFee() {
		return this.landTotalFee;
	}

	public void setLandTotalFee(Double landTotalFee) {
		this.landTotalFee = landTotalFee;
	}

	@Column(name = "LAND_REQUISITION_TOTALFEE", precision = 20, scale = 6)
	@XmlElement(name = "landRequisitionTotalFee")
	public Double getLandRequisitionTotalfee() {
		return this.landRequisitionTotalfee;
	}

	public void setLandRequisitionTotalfee(Double landRequisitionTotalfee) {
		this.landRequisitionTotalfee = landRequisitionTotalfee;
	}

	@Column(name = "HAS_OPENSPACE", length = 1)
	@XmlElement(name = "hasOpenSpace")
	public String getHasOpenspace() {
		return this.hasOpenspace;
	}

	public void setHasOpenspace(String hasOpenspace) {
		this.hasOpenspace = hasOpenspace;
	}

	@Column(name = "OPEN_SPACEAREA", length = 100)
	@XmlElement(name = "openSpaceArea")
	public String getOpenSpacearea() {
		return this.openSpacearea;
	}

	public void setOpenSpacearea(String openSpacearea) {
		this.openSpacearea = openSpacearea;
	}

	@Column(name = "INLAND_REQUISITION_TOTALFEE", precision = 20, scale = 6)
	@XmlElement(name = "inLandRequisitionTotalFee")
	public Double getInlandRequisitionTotalfee() {
		return this.inlandRequisitionTotalfee;
	}

	public void setInlandRequisitionTotalfee(Double inlandRequisitionTotalfee) {
		this.inlandRequisitionTotalfee = inlandRequisitionTotalfee;
	}

	@Column(name = "CONTRACT_NO", length = 50)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_NAME", length = 200)
	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	@Column(name = "CARD_ID", length = 20)
	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Column(name = "ASSET_NO", length = 30)
	public String getAssetNo() {
		return this.assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	@Column(name = "OWNERSHIP_UNIT", length = 200)
	public String getOwnershipUnit() {
		return this.ownershipUnit;
	}

	public void setOwnershipUnit(String ownershipUnit) {
		this.ownershipUnit = ownershipUnit;
	}

	@Column(name = "PROJECT", length = 200)
	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Column(name = "LOCATION_NO", length = 20)
	public String getLocationNo() {
		return this.locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	@Column(name = "ASSET_TYPE", length = 20)
	public String getAssetType() {
		return this.assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	@Column(name = "ASSET_NAME", length = 200)
	public String getAssetName() {
		return this.assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	@Column(name = "AREA", length = 50)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
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

	@Column(name = "BOOK_VALUE", precision = 20, scale = 6)
	public Double getBookValue() {
		return this.bookValue;
	}

	public void setBookValue(Double bookValue) {
		this.bookValue = bookValue;
	}

	@Column(name = "LAND_USECERTIFICATE", length = 20)
	@XmlElement(name = "landUseCertificate")
	public String getLandUsecertificate() {
		return this.landUsecertificate;
	}

	public void setLandUsecertificate(String landUsecertificate) {
		this.landUsecertificate = landUsecertificate;
	}

	@Column(name = "OWN_CERTIFICATE", length = 20)
	public String getOwnCertificate() {
		return this.ownCertificate;
	}

	public void setOwnCertificate(String ownCertificate) {
		this.ownCertificate = ownCertificate;
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
    @XmlJavaTypeAdapter(DateAdapter.class)
	public Date getKeepDate() {
		return this.keepDate;
	}

	public void setKeepDate(Date keepDate) {
		this.keepDate = keepDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REKEEP_DATE", length = 7)
    @XmlJavaTypeAdapter(DateAdapter.class)
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
    @XmlJavaTypeAdapter(DateAdapter.class)
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
    @XmlJavaTypeAdapter(DateAdapter.class)
	public Date getScrapDate() {
		return this.scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "IMPORT_TIME", length = 7)
    @XmlJavaTypeAdapter(DateAdapter.class)
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

	@Column(name = "TRANS_DEP", length = 200)
	public String getTransDep() {
		return this.transDep;
	}

	public void setTransDep(String transDep) {
		this.transDep = transDep;
	}

	@Column(name = "TAKE_OVER_DEP", length = 200)
	public String getTakeOverDep() {
		return this.takeOverDep;
	}

	public void setTakeOverDep(String takeOverDep) {
		this.takeOverDep = takeOverDep;
	}

	@Column(name = "REKEEP_NAME", length = 200)
	public String getRekeepName() {
		return this.rekeepName;
	}

	public void setRekeepName(String rekeepName) {
		this.rekeepName = rekeepName;
	}

	@Column(name = "CLASSIFICATION_NAME", length = 200)
	public String getClassificationName() {
		return this.classificationName;
	}

	public void setClassificationName(String classificationName) {
		this.classificationName = classificationName;
	}

	@Column(name = "UNIT_CODE", length = 200)
	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	@Column(name = "LAND_STATUS", length = 200)
	public String getLandStatus() {
		return this.landStatus;
	}

	public void setLandStatus(String landStatus) {
		this.landStatus = landStatus;
	}

	@Column(name = "LAND_LOCATION", length = 200)
	public String getLandLocation() {
		return this.landLocation;
	}

	public void setLandLocation(String landLocation) {
		this.landLocation = landLocation;
	}

	@Column(name = "BUILDER_PROJECT", length = 200)
	public String getBuilderProject() {
		return this.builderProject;
	}

	public void setBuilderProject(String builderProject) {
		this.builderProject = builderProject;
	}

	@Column(name = "LAND_DH", length = 20)
	public String getLandDh() {
		return this.landDh;
	}

	public void setLandDh(String landDh) {
		this.landDh = landDh;
	}

	@Column(name = "STATE", length = 4000)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "APPROVE_NO", length = 100)
	public String getApproveNo() {
		return this.approveNo;
	}

	public void setApproveNo(String approveNo) {
		this.approveNo = approveNo;
	}

	@Column(name = "APPROVE_NO_OR_DECIDE", length = 100)
	public String getApproveNoOrDecide() {
		return this.approveNoOrDecide;
	}

	public void setApproveNoOrDecide(String approveNoOrDecide) {
		this.approveNoOrDecide = approveNoOrDecide;
	}

	@Column(name = "LAND_PLANING", length = 2000)
	public String getLandPlaning() {
		return this.landPlaning;
	}

	public void setLandPlaning(String landPlaning) {
		this.landPlaning = landPlaning;
	}

	@Column(name = "BUILD_AREA", length = 100)
	public String getBuildArea() {
		return this.buildArea;
	}

	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}

	@Column(name = "LAND_TOTAL_AREA", length = 100)
	public String getLandTotalArea() {
		return this.landTotalArea;
	}

	public void setLandTotalArea(String landTotalArea) {
		this.landTotalArea = landTotalArea;
	}

	@Column(name = "LAND_REQUISITION_AREA", length = 100)
	public String getLandRequisitionArea() {
		return this.landRequisitionArea;
	}

	public void setLandRequisitionArea(String landRequisitionArea) {
		this.landRequisitionArea = landRequisitionArea;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOAD_DATE", length = 7)
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Column(name = "USE_MANAGER", length = 200)
	public String getUseManager() {
		return useManager;
	}

	public void setUseManager(String useManager) {
		this.useManager = useManager;
	}

}
