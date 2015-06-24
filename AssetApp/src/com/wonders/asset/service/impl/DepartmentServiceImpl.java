package com.wonders.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.DepartmentDao;
import com.wonders.asset.model.Department;
import com.wonders.asset.service.DepartmentService;

public class DepartmentServiceImpl extends BaseServiceImpl<Department, String> implements DepartmentService{
	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
		setBaseDao(departmentDao);
	}

	
	


	
	
	@Override
	public List<Department> findAllByVersion(String version) {
		
		return departmentDao.findByVersion(version);
	}

	
	
	@Override
	public List<Department> findByPublish() {
		return departmentDao.findByPublish();
	}

	@Override
	public List<String> showVersion() {
		return departmentDao.findAllVerison();
	}

	@Override
	public boolean isUnique(String id,String code, String name,String version) {
		List<Department> departments = departmentDao.findByCodeOrName(id,code, name,version);
		if(departments==null || departments.size()<1) return true;
		return false;
	}

	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}

	@Override
	public void deleteById(String id) {
		departmentDao.deleteById(id);
	}

	@Override
	public String copyCurrentVersion(String version) {
		if(version==null || "".equals(version)) return null;
		List<Department> departments = departmentDao.findByVersion(version);
		String newVersion = (Integer.valueOf(departmentDao.findLastestVersion())+1)+"";
		List<Department> toBeSave = null;
		if(departments!=null && departments.size()>0){
			toBeSave = new ArrayList<Department>();
			for(int i=0,len=departments.size(); i<len; i++){
				Department temp = new Department();
				BeanUtils.copyProperties(departments.get(i), temp);
				temp.setId("");
				temp.setPublish("0");		//复制的都是未发布的版本
				temp.setVersion(newVersion);
				toBeSave.add(temp);
			}
		}
		departmentDao.saveAll(toBeSave);
		return newVersion;
	}

	@Override
	public void publish(String version) {
		departmentDao.publish(version);
	}

	@Override
	public Department findById(String id) {
		return departmentDao.findById(id);
	}

	@Override
	public String findNextCodeId(String version) {
		String codeId = this.departmentDao.findMaxCodeId(version);
		String result = null;
		if(StringUtils.isNotEmpty(codeId)){
			try {
				result = (Integer.valueOf(codeId)+1)+"";
				return result;
			} catch (NumberFormatException e) {
				return result;
			}
		}
		return result;
	}

	

	
	
}
