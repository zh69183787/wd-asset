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
 * 资产项目线路价值统计
 * @author ycl
 *
 */
@Entity
@Table(name = "DW_ASSET_PROJECT_LINE_VALUE")
@SuppressWarnings("serial")
public class DwAssetProjectLineValue implements Serializable {

	private String id;
	private String lineName;
	private String lineId;
	private String projectName;
	private String projectId;
	private String shortName;
	
	private Double investEstimate;		//投资概算
	private Double finalPrice;		//决算价
	private Long count;				//资产项数
	private Double originalValue;	//原值
    private Double investAdded;	//增加投资
    private Double reserveEstimation;	//扣减预留预估
    private Double tax;	//增值税及其他项目扣减
	private Date createDate;
	
	private Double currentAsset;  //
	private Double fixedAssetValue;
	private Double spareParts; //备品备件
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

	@Column(name="LINE_NAME",nullable=true,length=200)
	public String getLineName() {
		return lineName;
	}
	
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name="LINE_ID",nullable=true,length=40)
	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	@Column(name="PROJECT_NAME",nullable=true,length=800)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name="PROJECT_ID",nullable=true,length=40)
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name="SHORT_NAME",length=40)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name="INVEST_ESTIMATE",nullable=true)
	public Double getInvestEstimate() {
		return investEstimate;
	}

	public void setInvestEstimate(Double investEstimate) {
		this.investEstimate = investEstimate;
	}

	@Column(name="FINAL_PRICE",nullable=true)
	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	@Column(name="COUNT",nullable=true)
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Column(name="ORIGINAL_VALUE",nullable=true)
	public Double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE",nullable=true)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


    @Column(name="INVEST_ADDED",nullable=true)
    public Double getInvestAdded() {
        return investAdded == null ? 0 : investAdded;
    }

    public void setInvestAdded(Double investAdded) {
        this.investAdded = investAdded;
    }

    @Column(name="RESERVE_ESTIMATION",nullable=true)
    public Double getReserveEstimation() {
        return reserveEstimation == null ? 0 : reserveEstimation;
    }

    public void setReserveEstimation(Double reserveEstimation) {
        this.reserveEstimation = reserveEstimation;
    }

    @Column(name="TAX",nullable=true)
    public Double getTax() {
        return tax == null ? 0 : tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Column(name="CURRENT_ASSET",nullable=true)
	public Double getCurrentAsset() {
		return currentAsset;
	}

	public void setCurrentAsset(Double currentAsset) {
		this.currentAsset = currentAsset;
	}

	@Column(name="FIXED_ASSET_VALUE",nullable=true)
	public Double getFixedAssetValue() {
		return fixedAssetValue;
	}

	public void setFixedAssetValue(Double fixedAssetValue) {
		this.fixedAssetValue = fixedAssetValue;
	}
    
    @Column(name="SPARE_PARTS",nullable=true)
	public Double getSpareParts() {
		return spareParts  == null ? 0 : spareParts;
	}

	public void setSpareParts(Double spareParts) {
		this.spareParts = spareParts;
	}
}
