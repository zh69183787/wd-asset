package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.Organization;

public interface OrganizationDao extends BaseDao<Organization, String>{

	/**
	 * 查询所有
	 */
	public List<Organization> findAll();
	
	/**
	 * 查询所有版本号
	 * @return 版本号
	 */
	public List<String> findAllVerison();

	
	/**
	 * 查询
	 * @param id 主键
	 * @param name	名称
	 * @return	部门
	 */
	public List<Organization> findByCodeOrName(String id,String code,String name,String version);
	
	/**
	 * 保存
	 * @param Organization
	 */
	public void save(Organization organization);
	
	/**
	 * 删除
	 * @param id 主键
	 */
	public void deleteById(String id);
	
	/**
	 * 查询
	 * @param version 当前版本
	 * @return
	 */
	public List<Organization> findByVersion(String version);
	
	/**
	 * 批量保存
	 */
	public void saveAll(List<Organization> organizations);
	
	/**
	 * 发布
	 * @param version 版本号
	 */
	public void publish(String version);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 组织
	 */
	public Organization findById(String id);
	
	public List<Organization> findByPublish();
	
	/**
	 * 根据code_id查询发布中状态的数据
	 */
	public Organization findByCodeWithPublish(String codeId);
	
	/**
	 * 根据code_id查询
	 * @param codeId
	 * @return
	 */
	public Organization findByCodeIdWithPublish(String codeId);
	
	/**
	 * 查询最新的版本
	 * @return
	 */
	public String findLastestVersion();
	
	/**
	 * 查询最大的codeId
	 * @param version
	 * @return
	 */
	public String findMaxCodeId(String version);
	
}
