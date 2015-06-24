package com.wonders.asset.model.dw;

import java.io.Serializable;
import java.util.Date;

/**
 * 重要资产线路分布
 * 
 * @author Kai Yao
 * @date 2013-12-10
 */
//@Entity
//@Table(name = "DW_IMPORTANT_ASSET_LINE")
@SuppressWarnings("serial")
public class DwImportantAssetLine implements Serializable {

//	private String id;
	private String lineId;
	private String name;
	private String shortName;
	private Long count;
	private Double price;
	private Date createDate;

//	@Id
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
//	@Column(name = "ID", nullable = false, length = 40)
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}

//	@Column(name = "LINE_ID", length = 20)
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

//	@Column(name = "NAME", length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Column(name = "SHORT_NAME", length = 40)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

//	@Column(name = "COUNT")
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

//	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

//	@Temporal(TemporalType.DATE)
//	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
