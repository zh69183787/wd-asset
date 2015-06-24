package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Department;

public interface DepartmentDao extends BaseDao<Department, String>{


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
	public List<Department> findByCodeOrName(String id,String code,String name,String version);
	
	/**
	 * 保存
	 * @param department
	 */
	public void save(Department department);
	
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
	public List<Department> findByVersion(String version);
	
	/**
	 * 批量保存
	 */
	public void saveAll(List<Department> departments);
	
	/**
	 * 发布
	 * @param version 版本号
	 */
	public void publish(String version);
	
	/**
	 * 查询
	 * @param id 主键
	 * @return 部门
	 */
	public Department findById(String id);
	
	/**
	 * 查询发布
	 * @param id
	 * @return
	 */
	public List<Department> findByPublish();
	
	/**
	 * 根据code查询发布中的数据
	 * @param code
	 * @return
	 */
	public Department findByCodeWithPublish(String codeId);
	
	public Department findByCodeIdWithPublish(String codeId);
	
	/**
	 * 查询最新的版本
	 * @return
	 */
	public String findLastestVersion();
	
	/**
	 * 查询当前版本下最大的codeId
	 * @param version
	 * @return
	 */
	public String findMaxCodeId(String version);
}
