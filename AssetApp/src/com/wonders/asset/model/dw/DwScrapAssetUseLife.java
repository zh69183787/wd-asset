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
 * 报废资产实际寿命统计
 * 
 * @author ycl
 * @date 2013-11-19
 */
@Entity
@Table(name = "DW_SCRAP_ASSET_USE_LIFE")
@SuppressWarnings("serial")
public class DwScrapAssetUseLife implements Serializable {

	private String id;
	private String year;
	private String mainTypeId;	//资产大类id
	private String subTypeId;	//资产小类id
	private String mainTypeName;	//资产大类名称
	private String subTypeName;		//资产中类名称
	private String aheadOfScrapCount;	//提前报废个数
	private String normalUseCount;		//正常使用个数
	private String normalScrapCount;	//正常报废个数
	private String delayScrapCount;		//延期报废个数
	private String createUser;		//创建人
	private Date createDate;		//创建时间

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

	@Column(name = "YEAR", nullable = true, length = 4)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "MAIN_TYPE_ID", nullable = true, length = 40)
	public String getMainTypeId() {
		return mainTypeId;
	}

	public void setMainTypeId(String mainTypeId) {
		this.mainTypeId = mainTypeId;
	}

	@Column(name = "SUB_TYPE_ID", nullable = true, length = 40)
	public String getSubTypeId() {
		return subTypeId;
	}

	public void setSubTypeId(String subTypeId) {
		this.subTypeId = subTypeId;
	}

	@Column(name = "MAIN_TYPE_NAME", nullable = true, length = 200)
	public String getMainTypeName() {
		return mainTypeName;
	}

	public void setMainTypeName(String mainTypeName) {
		this.mainTypeName = mainTypeName;
	}

	@Column(name = "SUB_TYPE_NAME", nullable = true, length = 200)
	public String getSubTypeName() {
		return subTypeName;
	}

	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	@Column(name = "AHEAD_OF_SCRAP_COUNT", nullable = true, length = 40)
	public String getAheadOfScrapCount() {
		return aheadOfScrapCount;
	}

	public void setAheadOfScrapCount(String aheadOfScrapCount) {
		this.aheadOfScrapCount = aheadOfScrapCount;
	}

	@Column(name = "NORMAL_USE_COUNT", nullable = true, length = 40)
	public String getNormalUseCount() {
		return normalUseCount;
	}

	public void setNormalUseCount(String normalUseCount) {
		this.normalUseCount = normalUseCount;
	}

	@Column(name = "NORMAL_SCRAP_COUNT", nullable = true, length = 40)
	public String getNormalScrapCount() {
		return normalScrapCount;
	}

	public void setNormalScrapCount(String normalScrapCount) {
		this.normalScrapCount = normalScrapCount;
	}

	@Column(name = "DELAY_SCRAP_COUNT", nullable = true, length = 40)
	public String getDelayScrapCount() {
		return delayScrapCount;
	}

	public void setDelayScrapCount(String delayScrapCount) {
		this.delayScrapCount = delayScrapCount;
	}

	@Column(name = "CREATE_USER", nullable = true, length = 40)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
