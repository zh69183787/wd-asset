package com.wonders.asset.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.webservice.dto.AttachmentDto;

/**
 * TDamageAsset entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_DAMAGE_ASSET")
public class DamageAsset implements java.io.Serializable {

	// Fields

	private String damageAssetId;
	private String assetNo;
	private Double damageValue;
	private Double residualValue;
	private Double depreciationed;  
	private Short userYear;
	private Date scrapDate;
	private Date requDate;
	private String scrapReason;
	private String subAsset;
	private String applyOrg;
	private String applyDoPerson;
	private Date applyDoDate;
	private String applyOpinion;
	private String replyOpinion;
	private Date replyDate;
	private String note;
	private String publish;
	private String creator;
	private Date createTime;
	private String updater;
	private Date updateTime;  
	private String id;
    private String loanNo;
	
	private Double accumulatedDepreciation;
	private Double netValue;
	private AssetOwner assetOwner;
	private Asset asset;
	private AssetPrice assetPrice;
	private AssetState assetState;
	private Project project;
	
	private int no;
	
	private String damageType;  //类别(数据库没有)
	private String useYear;  //已使用年限(数据库没有)

	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	@Transient
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUseYear() {
		return useYear;
	}

	public void setUseYear(String useYear) {
		this.useYear = useYear;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}

	public AssetOwner getAssetOwner() {
		return assetOwner;
	}

	public void setAssetOwner(AssetOwner assetOwner) {
		this.assetOwner = assetOwner;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public AssetPrice getAssetPrice() {
		return assetPrice;
	}

	public void setAssetPrice(AssetPrice assetPrice) {
		this.assetPrice = assetPrice;
	}

	public AssetState getAssetState() {
		return assetState;
	}

	public void setAssetState(AssetState assetState) {
		this.assetState = assetState;
	}

	@Column(name = "LOAN_NO")
    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }
	
	
	// Constructors
	@Column(name = "ACCUMULATED_DEPRECIATION", precision = 20, scale = 6)
	public Double getAccumulatedDepreciation() {
		return accumulatedDepreciation;
	}

	public void setAccumulatedDepreciation(Double accumulatedDepreciation) {
		this.accumulatedDepreciation = accumulatedDepreciation;
	}
	@Column(name = "NET_VALUE", precision = 20, scale = 6)
	public Double getNetValue() {
		return netValue;
	}

	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

	/** default constructor */
	public DamageAsset() {
	}

	/** full constructor */
	public DamageAsset(String assetNo, Double damageValue,
			Double residualValue, Double depreciationed, Short userYear,
			Date scrapDate, Date requDate, String scrapReason, String subAsset,
			String applyOrg, String applyDoPerson, Date applyDoDate,
			String applyOpinion, String replyOpinion, Date replyDate,String loanNo,
			String note, String publish, String creator, Date createTime,
			String updater, Date updateTime, String id) {
        this.loanNo = loanNo;
		this.assetNo = assetNo;
		this.damageValue = damageValue;
		this.residualValue = residualValue;
		this.depreciationed = depreciationed;
		this.userYear = userYear;
		this.scrapDate = scrapDate;
		this.requDate = requDate;
		this.scrapReason = scrapReason;
		this.subAsset = subAsset;
		this.applyOrg = applyOrg;
		this.applyDoPerson = applyDoPerson;
		this.applyDoDate = applyDoDate;
		this.applyOpinion = applyOpinion;
		this.replyOpinion = replyOpinion;
		this.replyDate = replyDate;
		this.note = note;
		this.publish = publish;
		this.creator = creator;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.id = id;
	
	}

	// Property accessors
	@Id
    @GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(name = "DAMAGE_ASSET_ID", unique = true, nullable = false, length = 32)
	public String getDamageAssetId() {
		return this.damageAssetId;
	}

	public void setDamageAssetId(String damageAssetId) {
		this.damageAssetId = damageAssetId;
	}

    @Column(name = "ASSET_NO", length = 400)
	public String getAssetNo() {
		return this.assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	@Column(name = "DAMAGE_VALUE", precision = 20, scale = 6)
	public Double getDamageValue() {
		return this.damageValue;
	}

	public void setDamageValue(Double damageValue) {
		this.damageValue = damageValue;
	}

	@Column(name = "RESIDUAL_VALUE", precision = 20, scale = 6)
	public Double getResidualValue() {
		return this.residualValue;
	}

	public void setResidualValue(Double residualValue) {
		this.residualValue = residualValue;
	}

	@Column(name = "DEPRECIATIONED", precision = 20, scale = 6)
	public Double getDepreciationed() {
		return this.depreciationed;
	}

	public void setDepreciationed(Double depreciationed) {
		this.depreciationed = depreciationed;
	}

	@Column(name = "USER_YEAR", precision = 3, scale = 0)
	public Short getUserYear() {
		return this.userYear;
	}

	public void setUserYear(Short userYear) {
		this.userYear = userYear;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SCRAP_DATE", length = 7)
	public Date getScrapDate() {
		return this.scrapDate;
	}

	public void setScrapDate(Date scrapDate) {
		this.scrapDate = scrapDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REQU_DATE", length = 7)
	public Date getRequDate() {
		return this.requDate;
	}

	public void setRequDate(Date requDate) {
		this.requDate = requDate;
	}

	@Column(name = "SCRAP_REASON", length = 250)
	public String getScrapReason() {
		return this.scrapReason;
	}

	public void setScrapReason(String scrapReason) {
		this.scrapReason = scrapReason;
	}

	@Column(name = "SUB_ASSET", length = 2000)
	public String getSubAsset() {
		return this.subAsset;
	}

	public void setSubAsset(String subAsset) {
		this.subAsset = subAsset;
	}

	@Column(name = "APPLY_ORG", length = 200)
	public String getApplyOrg() {
		return this.applyOrg;
	}

	public void setApplyOrg(String applyOrg) {
		this.applyOrg = applyOrg;
	}

	@Column(name = "APPLY_DO_PERSON", length = 200)
	public String getApplyDoPerson() {
		return this.applyDoPerson;
	}

	public void setApplyDoPerson(String applyDoPerson) {
		this.applyDoPerson = applyDoPerson;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPLY_DO_DATE", length = 7)
	public Date getApplyDoDate() {
		return this.applyDoDate;
	}

	public void setApplyDoDate(Date applyDoDate) {
		this.applyDoDate = applyDoDate;
	}

	@Column(name = "APPLY_OPINION", length = 2000)
	public String getApplyOpinion() {
		return this.applyOpinion;
	}

	public void setApplyOpinion(String applyOpinion) {
		this.applyOpinion = applyOpinion;
	}

	@Column(name = "REPLY_OPINION", length = 2000)
	public String getReplyOpinion() {
		return this.replyOpinion;
	}

	public void setReplyOpinion(String replyOpinion) {
		this.replyOpinion = replyOpinion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPLY_DATE", length = 7)
	public Date getReplyDate() {
		return this.replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Column(name = "NOTE", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "PUBLISH", length = 1)
	public String getPublish() {
		return this.publish;
	}

	public void setPublish(String removed) {
		this.publish = removed;
	}

	@Column(name = "CREATOR", length = 100)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATER", length = 100)
	public String getUpdater() {
		return this.updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ID", length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}