package com.wonders.asset.model.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AssetTypeVo implements Serializable {

	private String id;
	private String name;				//简称
	private String fullName;			//全称
	private String code;
	private String version;
	private AssetTypeVo parent;
	private String allCode;
	private String useLife;
	private String overhaulFrequency;
	private String codeId;
	
	private List<AssetTypeVo> children = new ArrayList<AssetTypeVo>();
	
	private String publish;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getVersion() {
		return version;
	}
	
	public String getAllCode() {
		return allCode;
	}

	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public AssetTypeVo getParent() {
		return parent;
	}

	public void setParent(AssetTypeVo parent) {
		this.parent = parent;
	}

	public List<AssetTypeVo> getChildren() {
		return children;
	}

	public void setChildren(List<AssetTypeVo> children) {
		this.children = children;
	}
	
	public String getUseLife() {
		return useLife;
	}

	public void setUseLife(String useLife) {
		this.useLife = useLife;
	}

	public String getOverhaulFrequency() {
		return overhaulFrequency;
	}

	public void setOverhaulFrequency(String overhaulFrequency) {
		this.overhaulFrequency = overhaulFrequency;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	

}
