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
 * 资产重要指标情况
 * @author Kai Yao
 * @date 2014-1-10
 */
@Entity
@Table(name = "DW_ASSET_IMPORTANT_RATIO")
@SuppressWarnings("serial")
public class DwAssetImportantRatio implements Serializable {

	private String id;
	private String code;
	private String name;
	private Double useRatio;
	private Double intactRatio;
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

	@Column(name = "CODE", nullable = false, length = 40)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = true, length = 400)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "USE_RATIO")
	public Double getUseRatio() {
		return useRatio;
	}

	public void setUseRatio(Double useRatio) {
		this.useRatio = useRatio;
	}

	@Column(name = "INTACT_RATIO")
	public Double getIntactRatio() {
		return intactRatio;
	}

	public void setIntactRatio(Double intactRatio) {
		this.intactRatio = intactRatio;
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
