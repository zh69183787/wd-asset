package com.wonders.asset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="T_ASSET_STATE")
@SuppressWarnings("serial")
public class AssetState extends SystemInformation{

	private String assetId;
	private String useState;
	private String state;
	private String nameplateSite;
	
	@Column(name="ASSET_ID",nullable=true,length=40)
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	
	@Column(name="USE_STATE",nullable=true,length=4)
	public String getUseState() {
		return useState;
	}
	public void setUseState(String useState) {
		this.useState = useState;
	}
	
	@Column(name="STATE",nullable=true,length=4)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="NAMEPLATE_SITE",nullable=true,length=400)
	public String getNameplateSite() {
		return nameplateSite;
	}
	public void setNameplateSite(String nameplateSite) {
		this.nameplateSite = nameplateSite;
	}
	
	
}
