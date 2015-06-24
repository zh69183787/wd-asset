package com.wonders.asset.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Department;
import com.wonders.asset.service.DepartmentService;
import com.wonders.framework.util.ServiceProvider;

public class DepartmentAction extends AbstractBaseAction{

	private DepartmentService departmentService;
	public DepartmentService getDepartmentService() {
		return departmentService;
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	public void getAllDepartment() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		List<Department> list = null;
		if(StringUtils.isEmpty(version)){
			list = ServiceProvider.getService(DepartmentService.class).findByPublish();
		}else{
			list = ServiceProvider.getService(DepartmentService.class).findAllByVersion(version);
		}
		renderJson(list);
	}
	
	public void findAllVersion() throws DocumentException, IOException{
		List<String> versions = ServiceProvider.getService(DepartmentService.class).showVersion();
		renderJson(versions);
	}
	
	public void checkUniqueness() throws DocumentException, IOException{
		String id=getRequestParameter("id");
		String code = getRequestParameter("code");
		String name = getRequestParameter("name");
		String version = getRequestParameter("version");
		
		boolean stats = ServiceProvider.getService(DepartmentService.class).isUnique(id,code, name,version);
		if(!stats){
			renderJson("false");
		}else{
			renderJson("true");
		}
	}
	
	public void saveDepartment() throws DocumentException{
		String id = getRequestParameter("id");
		Department department = ServiceProvider.getService(DepartmentService.class).findById(id);
		String code = getRequestParameter("code");
		String name = getRequestParameter("name");
		String version = getRequestParameter("version");
		String remark = getRequestParameter("remark");
		String publish = getRequestParameter("publish");
		
		String codeId = ServiceProvider.getService(DepartmentService.class).findNextCodeId(version);
		
		if(department==null){
			department = new Department();
		}
		department.setCodeId(codeId);
		department.setCode(code);
		department.setName(name);
		department.setRemarks(remark);
		department.setVersion(version);
		department.setPublish(publish);
		ServiceProvider.getService(DepartmentService.class).save(department);
	}
	
	public void deleteDepartment() throws DocumentException{
		String id = getRequestParameter("id");
		ServiceProvider.getService(DepartmentService.class).deleteById(id);
	}
	
	public void copyCurrentVersion() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		String newVersion = ServiceProvider.getService(DepartmentService.class).copyCurrentVersion(version);
		renderJson(newVersion);
	}
	
	public void publish() throws DocumentException{
		String version = getRequestParameter("version");
		ServiceProvider.getService(DepartmentService.class).publish(version);
	}
}
