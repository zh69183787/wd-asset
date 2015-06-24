package com.wonders.asset.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_ASSET_TYPE")
@SuppressWarnings("serial")
public class AssetType implements Serializable {

	private String id;
	private String name;
	private String code;
	private String version;
	private AssetType parent;
	private String allCode;
	private String useLife;
	private String overhaulFrequency;
	private String codeId;
	private Long overhaulFrequencyNum;
	
	private Set<AssetType> children = new HashSet<AssetType>();
	
	private String publish;

	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 400)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", nullable = false, length = 400)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "version", nullable = false, length = 10)
	public String getVersion() {
		return version;
	}
	
	@Column(name = "ALL_CODE", nullable = true, length = 20)
	public String getAllCode() {
		return allCode;
	}

	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	@Column(name = "PUBLISH", nullable = true, length = 1)
	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	public AssetType getParent() {
		return parent;
	}

	public void setParent(AssetType parent) {
		this.parent = parent;
	}

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<AssetType> getChildren() {
		return children;
	}

	public void setChildren(Set<AssetType> children) {
		this.children = children;
	}

	@Column(name = "USE_LIFE", nullable = true, length = 400)
	public String getUseLife() {
		return useLife;
	}

	public void setUseLife(String useLife) {
		this.useLife = useLife;
	}

	@Column(name = "OVERHAUL_FREQUENCY", nullable = true, length = 400)
	public String getOverhaulFrequency() {
		return overhaulFrequency;
	}

	public void setOverhaulFrequency(String overhaulFrequency) {
		this.overhaulFrequency = overhaulFrequency;
	}

	@Column(name = "CODE_ID", nullable = true, length = 400)
	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	@Column(name="OVERHAUL_FREQUENCY_NUM",nullable=true)
	public Long getOverhaulFrequencyNum() {
		return overhaulFrequencyNum;
	}
	public void setOverhaulFrequencyNum(Long overhaulFrequencyNum) {
		this.overhaulFrequencyNum = overhaulFrequencyNum;
	}

	
	
}
