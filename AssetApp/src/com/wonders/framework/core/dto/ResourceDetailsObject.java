package com.wonders.framework.core.dto;

import java.io.Serializable;

/**
 * 保存资源信息的业务对象
 * 
 * @author dingsheng.zhang
 * 
 */
@SuppressWarnings("serial")
public class ResourceDetailsObject implements Serializable {

	/**
	 * 父资源Id
	 */
	private Integer parentId;

	/**
	 * 资源类型
	 */
	private Integer resourceType;

	/**
	 * 资源URL
	 */
	private String resourceUrl;

	/**
	 * 描述信息
	 */
	private String description;

	/**
	 * 资源id
	 */
	private Integer resourceId;

	/**
	 * 资源名称
	 */
	private String resourceName;

	/**
	 * 得到父Id
	 * 
	 * @return
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 设置父Id
	 * 
	 * @param parentId
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 得到资源类型
	 * 
	 * @return
	 */
	public Integer getResourceType() {
		return resourceType;
	}

	/**
	 * 设置资源类型
	 * 
	 * @param resourceType
	 */
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * 得到资源url
	 * 
	 * @return
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * 设置资源url
	 * 
	 * @param resourceUrl
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	/**
	 * 得到描述信息
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述信息
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 得到资源Id
	 * 
	 * @return
	 */
	public Integer getResourceId() {
		return resourceId;
	}

	/**
	 * 设置资源Id
	 * 
	 * @param resourceId
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 得到资源名称
	 * 
	 * @return
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * 设置资源名称
	 * 
	 * @param resourceName
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}