package com.wonders.asset.dao;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.Project;

public interface ProjectDao extends BaseDao<Project, String>{

	/**
	 * 根据项目编号查询项目
	 * @param projectNo
	 * @return
	 */
	public Project findByProjectNo(String projectNo);
	
	/**
	 * 根据线路查询项目
	 * @param lineId
	 * @return
	 */
	public List<Project> findByLineCodeId(String lineCodeId);
	
	/**
	 * 根据资产编号查询
	 * @param assetId
	 * @return
	 */
	public List<Project> findByAssetId(String assetId);
	
	/**
	 *  
	 * @param shortName
	 * @return
	 */
	public List<Project> findByShortName(String shortName);
	
	
	public void syncProject();
	
	/**
	 * 查询SHORT_NAME和LINE_CODE
	 * @return
	 */
	public List<Map<String,String>> findShortNameAndLineCode();
	
	
	/**
	 * 计算总投资
	 * @return
	 */
	public Double countAllInvest();
	
	/**
	 * 根据年份计算当年总投资
	 * @param year
	 * @return
	 */
	public Double countInvestByYear(String year);
}
