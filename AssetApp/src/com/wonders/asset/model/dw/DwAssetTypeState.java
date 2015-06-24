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
 * 资产分类统计
 * 
 * @author Kai Yao
 * @date 2013-12-13
 */
@Entity
@Table(name = "DW_ASSET_TYPE_STATE")
@SuppressWarnings("serial")
public class DwAssetTypeState implements Serializable {

	private String id;
	private String code;
	private String name;
	private Double usePrice;
	private Double stopPrice;
	private Double scrapPrice;
	private Double planScrapPrice;
	private Double discardPrice;
	private String type;
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

	@Column(name = "CODE", length = 40)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "SCRAP_PRICE")
	public Double getScrapPrice() {
		return scrapPrice;
	}

	public void setScrapPrice(Double scrapPrice) {
		this.scrapPrice = scrapPrice;
	}

	@Column(name = "DISCARD_PRICE")
	public Double getDiscardPrice() {
		return discardPrice;
	}

	public void setDiscardPrice(Double discardPrice) {
		this.discardPrice = discardPrice;
	}

	@Column(name = "TYPE", length = 40)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
