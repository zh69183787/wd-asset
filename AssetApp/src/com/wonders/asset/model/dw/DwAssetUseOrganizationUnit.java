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
 * 使用单位资产价值统计
 * @author ycl
 *
 */
@Entity
@Table(name = "DW_ASSET_USE_ORGANIZATION_UNIT")
@SuppressWarnings("serial")
public class DwAssetUseOrganizationUnit implements Serializable {

	private String id;
	private String order_;
	private String orgId;
	private String name;
	private String shortName;
	private Long count;
	private Double originalValue; 
	private String year;
	private String version;
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

	@Column(name = "ORDER_", nullable = true, length = 40)
	public String getOrder_() {
		return order_;
	}

	public void setOrder_(String order_) {
		this.order_ = order_;
	}

	@Column(name = "ORG_ID", length = 40)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "NAME", nullable = true, length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SHORT_NAME", length = 40)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "COUNT", nullable = true)
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Column(name = "ORIGINAL_VALUE", nullable = true)
	public Double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}

	@Column(name = "YEAR", nullable = true,length=40)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "VERSION", nullable = true,length=80)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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
