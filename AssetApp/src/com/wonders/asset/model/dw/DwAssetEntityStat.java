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
 * 资产实物统计
 * @author Kai Yao
 * @date 2013-11-07
 */
@Entity
@Table(name = "DW_ASSET_ENTITY_STAT")
@SuppressWarnings("serial")
public class DwAssetEntityStat implements Serializable {

	private String id;
	private String code;
	private String name;
	private String pcode;
	private String mainTypeCode;
	private String subTypeCode;
	private String detailTypeCode;
	private Date createDate;
	private Long count;
	private Double originalValue;

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

	@Column(name = "CODE", nullable = false, length = 40)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "P_CODE", length = 40)
	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Column(name = "NAME", nullable = true, length = 400)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MAIN_TYPE_CODE", length = 40)
	public String getMainTypeCode() {
		return mainTypeCode;
	}

	public void setMainTypeCode(String mainTypeCode) {
		this.mainTypeCode = mainTypeCode;
	}

	@Column(name = "SUB_TYPE_CODE", length = 40)
	public String getSubTypeCode() {
		return subTypeCode;
	}

	public void setSubTypeCode(String subTypeCode) {
		this.subTypeCode = subTypeCode;
	}

	@Column(name = "DETAIL_TYPE_CODE", length = 40)
	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "COUNT")
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Column(name = "ORIGINAL_VALUE")
	public Double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}
	
}
