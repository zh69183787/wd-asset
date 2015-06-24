package com.wonders.asset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
/**
 * 停用
 * @author 01052621
 *
 */
@Entity
@Table(name = "T_DISABLE_ASSET", schema = "STZC")
public class DisableAsset implements java.io.Serializable{
	private int no;
	private String disableAssetId;   
	private String assetNo;
	private String creator;
	private String organ;
	private String stopReason;
	private Date stopDate;
	private String note;
	private String state;
	private String publish;
	private Date createTime;
	private String updater;
	private Date updateTime;
	private String id;
	private String loanNo;
	
	private Asset asset;
	private AssetPrice assetPrice;
	private AssetState assetState;
	private AssetOwner assetOwner;
	
	
	@Transient
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Transient
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	@Transient
	public AssetPrice getAssetPrice() {
		return assetPrice;
	}
	public void setAssetPrice(AssetPrice assetPrice) {
		this.assetPrice = assetPrice;
	}
	@Transient
	public AssetState getAssetState() {
		return assetState;
	}
	public void setAssetState(AssetState assetState) {
		this.assetState = assetState;
	}
	@Transient
	public AssetOwner getAssetOwner() {
		return assetOwner;
	}
	public void setAssetOwner(AssetOwner assetOwner) {
		this.assetOwner = assetOwner;
	}
	public DisableAsset(){
		
	}
	public DisableAsset(String disableAssetId, String assetNo, String creator,
			String organ, String stopReason, Date stopDate, String note,
			String state, String publish, Date createTime, String updater,
			Date updateTime, String id, String loanNo) {
		super();
		this.disableAssetId = disableAssetId;
		this.assetNo = assetNo;
		this.creator = creator;
		this.organ = organ;
		this.stopReason = stopReason;
		this.stopDate = stopDate;
		this.note = note;
		this.state = state;
		this.publish = publish;
		this.createTime = createTime;
		this.updater = updater;
		this.updateTime = updateTime;
		this.id = id;
		this.loanNo = loanNo;
	}
	@Id
	@GeneratedValue(generator="idGenerator")
    @GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(name = "DISABLE_ASSET_ID", unique = true, nullable = false, length = 32)
	public String getDisableAssetId() {
		return disableAssetId;
	}
	public void setDisableAssetId(String disableAssetId) {
		this.disableAssetId = disableAssetId;
	}
	
	@Column(name = "ASSET_NO", length = 30)
	public String getAssetNo() {
		return assetNo;
	}
	public void setAssetNo(String assetNo) {  
		this.assetNo = assetNo;
	}
	
	@Column(name = "CREATOR", length = 200)
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Column(name = "ORGAN", length = 200)
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	
	@Column(name = "STOP_REASON", length = 250)
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "STOP_DATE", length = 7)
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	
	@Column(name = "NOTE", length = 250)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name = "STATE", length = 1)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "PUBLISH", length = 1)
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "UPDATER", length = 100)
	public String getUpdater() {
		return updater;
	}
	
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "ID", length = 100)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "LOAN_NO")
	public String getLoanNo() {
		return loanNo;
	}
	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}
	
	

}
