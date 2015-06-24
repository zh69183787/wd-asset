package com.wonders.asset.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.Organization;
import com.wonders.asset.service.OrganizationService;
import com.wonders.framework.util.ServiceProvider;

public class OrganizationAction extends AbstractBaseAction{

	private OrganizationService organizationService;

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	public void getAllOrganization() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		List<Organization> list = null;
		if(StringUtils.isNotEmpty(version)){
			list = ServiceProvider.getService(OrganizationService.class).findAllByVersion(version);
		}else{
			list = ServiceProvider.getService(OrganizationService.class).findByPublish();
		}
		renderJson(list);
	}
	
	public void findAllVersion() throws DocumentException, IOException{
		List<String> versions = ServiceProvider.getService(OrganizationService.class).showVersion();
		renderJson(versions);
	}
	
	public void checkUniqueness() throws DocumentException, IOException{
		String id = getRequestParameter("id");
		String code = getRequestParameter("code");
		String name = getRequestParameter("name");
		String version = getRequestParameter("version");
		boolean stats = ServiceProvider.getService(OrganizationService.class).isUnique(id,code, name,version);
		if(!stats){
			renderJson("false");
		}else{
			renderJson("true");
		}
	}
	
	public void saveOrganization() throws DocumentException{
		String id = getRequestParameter("id");
		Organization organization = null;
		if(!StringUtils.isEmpty(id)){
			organization = ServiceProvider.getService(OrganizationService.class).findById(id);
		}
		String code = getRequestParameter("code");
		String name = getRequestParameter("name");
		String version = getRequestParameter("version");
		String remark = getRequestParameter("remark");
		String publish = getRequestParameter("publish");
		
		String codeId = ServiceProvider.getService(OrganizationService.class).findNextCodeId(version);
		
		if(organization==null){
			organization = new Organization();
		}
		organization.setCodeId(codeId);
		organization.setCode(code);
		organization.setName(name);
		organization.setRemarks(remark);
		organization.setVersion(version);
		organization.setPublish(publish);
		ServiceProvider.getService(OrganizationService.class).save(organization);
	}
	
	public void deleteOrganization() throws DocumentException{
		String id = getRequestParameter("id");
		ServiceProvider.getService(OrganizationService.class).deleteById(id);
	}
	
	public void copyCurrentVersion() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		String newVersion = ServiceProvider.getService(OrganizationService.class).copyCurrentVersion(version);
		renderJson(newVersion);
	}
	
	public void publish() throws DocumentException{
		String version = getRequestParameter("version");
		ServiceProvider.getService(OrganizationService.class).publish(version);
	}
}
