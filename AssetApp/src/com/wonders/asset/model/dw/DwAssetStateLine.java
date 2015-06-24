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
 * 线路资产状态统计
 * @author Kai Yao
 * @date 2013-11-13
 */
@Entity
@Table(name = "Dw_Asset_State_Line")
@SuppressWarnings("serial")
public class DwAssetStateLine implements Serializable {

	private String id;
	private String lineId;
	private String name;
	private String shortName;
	private Long useCount;
	private Long stopCount;
	private Long planScrapCount;
	private Double usePrice;
	private Double stopPrice;
	private Double planScrapPrice;
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

	@Column(name = "LINE_ID", length = 20)
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	@Column(name = "NAME", length = 200)
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

	@Column(name = "USE_COUNT")
	public Long getUseCount() {
		return useCount;
	}

	public void setUseCount(Long useCount) {
		this.useCount = useCount;
	}

	@Column(name = "STOP_COUNT")
	public Long getStopCount() {
		return stopCount;
	}

	public void setStopCount(Long stopCount) {
		this.stopCount = stopCount;
	}

	@Column(name = "PLAN_SCRAP_COUNT")
	public Long getPlanScrapCount() {
		return planScrapCount;
	}

	public void setPlanScrapCount(Long planScrapCount) {
		this.planScrapCount = planScrapCount;
	}

	@Column(name = "USE_PRICE")
	public Double getUsePrice() {
		return usePrice;
	}

	public void setUsePrice(Double usePrice) {
		this.usePrice = usePrice;
	}

	@Column(name = "STOP_PRICE")
	public Double getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(Double stopPrice) {
		this.stopPrice = stopPrice;
	}

	@Column(name = "PLAN_SCRAP_PRICE")
	public Double getPlanScrapPrice() {
		return planScrapPrice;
	}

	public void setPlanScrapPrice(Double planScrapPrice) {
		this.planScrapPrice = planScrapPrice;
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
