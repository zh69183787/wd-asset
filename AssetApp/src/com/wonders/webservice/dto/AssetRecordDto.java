package com.wonders.webservice.dto;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.wonders.utils.DateAdapter;
import org.hibernate.annotations.GenericGenerator;

import com.wonders.asset.model.Asset;

/**
 * Created by HH on 2015/1/16.
 */
@Entity
@Table(name = "T_ASSET_RECORD_TMP")  
@XmlRootElement(name = "AssetRecord")  
@SuppressWarnings("serial")
public class AssetRecordDto {
	
	private String id;
	private String projectName;
	private String maintainContent;
	private String maintainCost;
	private String assetType;  //operateProjectAsset
	private String projectAppNo;
	private Date finishDate;
	private String finishPrice;
	private String assetRecordID;
	private String assetNo; 
	private String assetCodeType;
	private Date createDate;
	private Date updateDate;
	private Date uploadDate;
	
	@Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @Column(name = "ASSET_RECORD_ID", unique = true, nullable = false, length = 32) 
	public String getAssetRecordID() {
		return assetRecordID;
	}

	public void setAssetRecordID(String assetRecordID) {
		this.assetRecordID = assetRecordID;
	}

	@Column(name = "FINISH_DATE")
    @XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement(name = "operateProjectAssetDate")   
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	@Column(name = "FINISH_PRICE")
	@XmlElement(name = "operateProjectAssetAccount")   
	public String getFinishPrice() {
		return finishPrice;
	}

	public void setFinishPrice(String finishPrice) {
		this.finishPrice = finishPrice;
	}
	
	@Column(name = "UPLOAD_DATE", length =7)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
	
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="UPDATE_DATE")   
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name="PROJECT_APP_NO")
	@XmlElement(name = "projectAppDocNo")
	public String getProjectAppNo() {
		return projectAppNo;
	}
	public void setProjectAppNo(String projectAppNo) {
		this.projectAppNo = projectAppNo;
	}
	@Column(name="MAINTAIN_CONTENT")
	public String getMaintainContent() {
		return maintainContent;
	}
	public void setMaintainContent(String maintainContent) {
		this.maintainContent = maintainContent;
	}
	@Column(name="PROJECT_NAME")
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {   
		this.projectName = projectName;
	}

	
    @Column(name = "ID")  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="ASSET_NO")
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	@Column(name="MAINTAIN_COST")
	public String getMaintainCost() {
		return maintainCost;
	}
	public void setMaintainCost(String maintainCost) {
		this.maintainCost = maintainCost;
	}
	@Column(name="ASSET_TYPE")
	@XmlElement(name = "operateProjectAsset")
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	@Column(name="ASSET_CODE_TYPE",nullable=true,length=1)  
	public String getAssetCodeType() {
		return assetCodeType;
	}  
	public void setAssetCodeType(String assetCodeType) {
		this.assetCodeType = assetCodeType;
	}
	
}
