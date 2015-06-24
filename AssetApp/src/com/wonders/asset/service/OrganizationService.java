package com.wonders.asset.service;

import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.Organization;

public interface OrganizationService extends BaseService<Organization, String>{

	/**
	 * 查询
	 * @param version 版本
	 * @return 部门
	 */
	public List<Organization> findAllByVersion(String version);
	
	/**
	 * 查询所有版本号
	 * @return
	 */
	public List<String> showVersion();
	
	/**
	 * 检查唯一性
	 * @param code 代码
	 * @param name	名称
	 * @return	true,false
	 */
	public boolean isUnique(String id,String code,String name,String version);
	
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
	 * 复制当前版本数据
	 * @param version
	 * @return 复制的版本
	 */
	public String copyCurrentVersion(String version);
	
	public void publish(String version);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 组织
	 */
	public Organization findById(String id);
	
	
	/**
	 * 查询发布中的数据
	 * @return
	 */
	public List<Organization> findByPublish();
	
	/**
	 * 查询下一个codeId
	 * @param version
	 * @return
	 */
	public String findNextCodeId(String version);

}
