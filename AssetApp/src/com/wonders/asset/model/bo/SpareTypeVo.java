package com.wonders.asset.model.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
public class SpareTypeVo implements Serializable {
	private String Id; 
	private String code;
	private String name;
	private SpareTypeVo parent;
	private String version;
	private String allCode;
	private String remark;
	private String codeId;
	private String publish;
	
	private List<SpareTypeVo> children = new ArrayList<SpareTypeVo>();
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getAllCode() {
		return allCode;
	}
	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public List<SpareTypeVo> getChildren() {
		return children;
	}
	public void setChildren(List<SpareTypeVo> children) {
		this.children = children;
	}
	public SpareTypeVo getParent() {
		return parent;
	}
	public void setParent(SpareTypeVo parent) {
		this.parent = parent;
	}

}
