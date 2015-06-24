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
 * 资产使用情况
 * @author Kai Yao
 * @date 2013-11-12
 */
@Entity
@Table(name = "DW_HOME_PAGE_STAT")
@SuppressWarnings("serial")
public class DwHomePageStat implements Serializable {

	private String id;
	private String projectCount; // 建设项目总数
	private String projectPrice; // 建设项目总概算
	private String assetProjectCount; // 实物资产入册项目数
	private String assetCount;	// 实物资产入册项数
	private String assetContractPrice;	// 实物资产入册合同价
	private String accessAssetProjectCount; // 价值资产入册项目数
	private String accessAssetCount; // 价值资产入册项数
	private String accessAssetPrice; // 价值资产入册原值

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

	@Column(name = "PROJECT_COUNT", length = 50)
	public String getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(String projectCount) {
		this.projectCount = projectCount;
	}

	@Column(name = "PROJECT_PRICE", length = 50)
	public String getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(String projectPrice) {
		this.projectPrice = projectPrice;
	}

	@Column(name = "ASSET_COUNT", length = 50)
	public String getAssetCount() {
		return assetCount;
	}

	public void setAssetCount(String assetCount) {
		this.assetCount = assetCount;
	}

	@Column(name = "ACCESS_ASSET_COUNT", length = 50)
	public String getAccessAssetCount() {
		return accessAssetCount;
	}

	public void setAccessAssetCount(String accessAssetCount) {
		this.accessAssetCount = accessAssetCount;
	}

	@Column(name = "ACCESS_ASSET_PRICE", length = 50)
	public String getAccessAssetPrice() {
		return accessAssetPrice;
	}

	public void setAccessAssetPrice(String accessAssetPrice) {
		this.accessAssetPrice = accessAssetPrice;
	}

	@Column(name = "ASSET_PROJECT_COUNT", length = 50)
	public String getAssetProjectCount() {
		return assetProjectCount;
	}

	public void setAssetProjectCount(String assetProjectCount) {
		this.assetProjectCount = assetProjectCount;
	}

	@Column(name = "ASSET_CONTRACT_PRICE", length = 50)
	public String getAssetContractPrice() {
		return assetContractPrice;
	}

	public void setAssetContractPrice(String assetContractPrice) {
		this.assetContractPrice = assetContractPrice;
	}

	@Column(name = "ACCESS_ASSET_PROJECT_COUNT", length = 50)
	public String getAccessAssetProjectCount() {
		return accessAssetProjectCount;
	}

	public void setAccessAssetProjectCount(String accessAssetProjectCount) {
		this.accessAssetProjectCount = accessAssetProjectCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
