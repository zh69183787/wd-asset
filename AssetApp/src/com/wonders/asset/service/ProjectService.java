package com.wonders.asset.service;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.Project;

public interface ProjectService extends BaseService<Project, String>{

	/**
	 * 根据线路查询项目
	 * @param lineId
	 * @return
	 */
	public List<Project> findByLineCodeId(String lineCodeId);
	
	/**
	 * 根据资产id查询
	 * @param assetId
	 * @return
	 */
	public List<Project> findByAsset(String assetId);
	
	/**
	 * 查询
	 * @param projectNo 项目编号
	 * @return
	 */
	public Project findByProjectNo(String projectNo);
	
	/**
	 * 查询
	 * @param projectName 项目名称
	 * @return
	 */
	public Project findByShortName(String projectName);
	
	public void syncProjectAndContract();
	
	/**
	 * 查询所有的小线，即SHORT_NAME字段
	 * @return
	 */
	public List<Map<String, String>> findShortNameAndLineCode();
	
}
