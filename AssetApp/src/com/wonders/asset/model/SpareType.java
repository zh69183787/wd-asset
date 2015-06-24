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
@Table(name = "T_SPARE_TYPE")
@SuppressWarnings("serial")
public class SpareType implements Serializable{
	private String Id; 
	private String code;
	private String name;
	private SpareType parent;
	private String version;
	private String allCode;
	private String remark;
	private String codeId;
	private String publish;
	
	private Set<SpareType> children = new HashSet<SpareType>();
	
	@Id
	@GeneratedValue(generator="system.uuid")
	@GenericGenerator(name="system.uuid",strategy="uuid")
	@Column(name = "ID", nullable = false, length = 40)
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	@Column(name = "CODE", nullable = false, length = 400)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "NAME", nullable = false, length = 400)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	@Column(name = "VERSION", nullable = false, length = 5)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "ALL_CODE", nullable = false, length = 40)
	public String getAllCode() {
		return allCode;
	}
	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}
	
	@Column(name = "REMARK", nullable = false, length = 1000)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CODE_ID", nullable = false, length = 400)
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	@Column(name = "PUBLISH", nullable = false, length = 1)
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "parent", fetch = FetchType.LAZY)
	public Set<SpareType> getChildren() {
		return children;
	}
	public void setChildren(Set<SpareType> children) {
		this.children = children;
	}
	
	@ManyToOne//(fetch=FetchType.LAZY,cascade=CascadeType.ALL,optional=false)
	@JoinColumn(name = "PARENT_ID")//,nullable=true)
	public SpareType getParent() {
//		if(parent == null)
//			parent = new SpareType();
		return parent;
	}
	public void setParent(SpareType parent) {
		this.parent = parent;
	}
	
	
	
	
	
	
}
