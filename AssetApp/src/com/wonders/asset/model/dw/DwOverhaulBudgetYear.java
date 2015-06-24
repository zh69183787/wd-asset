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
 * 大修/更新改造项目投资年变化情况
 * 
 * @author Kai Yao
 * @date 2013-11-20
 */
@Entity
@Table(name = "DW_OVERHAUL_BUDGET_YEAR")
@SuppressWarnings("serial")
public class DwOverhaulBudgetYear implements Serializable {

	private String id;
	private String year;
	private Double price;
	private Double contractPrice;
	private Double contractPayPrice; //合同支付数
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

	@Column(name = "YEAR", length = 20)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "CONTRACT_PRICE")
	public Double getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(Double contractPrice) {
		this.contractPrice = contractPrice;
	}

	@Column(name = "CONTRACT_PAY_PRICE")
	public Double getContractPayPrice() {
		return contractPayPrice;
	}

	public void setContractPayPrice(Double contractPayPrice) {
		this.contractPayPrice = contractPayPrice;
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
