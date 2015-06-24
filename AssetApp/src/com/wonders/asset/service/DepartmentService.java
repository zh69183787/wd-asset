package com.wonders.asset.service;

import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Department;

public interface DepartmentService extends BaseService<Department, String>{


	
	/**
	 * 查询
	 * @param version 版本
	 * @return 部门
	 */
	public List<Department> findAllByVersion(String version);
	
	/**
	 * 查询发布中的部门
	 * @return
	 */
	public List<Department> findByPublish();
	
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
	 * @param department
	 */
	public void save(Department department);
	
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
	 * @return 部门
	 */
	public Department findById(String id);
	
	/**
	 * 查询下一个codeId
	 * @param version
	 * @return
	 */
	public String findNextCodeId(String version);
}
