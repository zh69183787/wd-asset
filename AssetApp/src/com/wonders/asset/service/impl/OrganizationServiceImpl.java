package com.wonders.asset.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.OrganizationDao;
import com.wonders.asset.model.Organization;
import com.wonders.asset.service.OrganizationService;

public class OrganizationServiceImpl extends BaseServiceImpl<Organization, String> implements OrganizationService{
	private OrganizationDao organizationDao;

	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
		setBaseDao(organizationDao);
	}
	

	@Override
	public List<Organization> findAllByVersion(String version) {
		
		return organizationDao.findByVersion(version);
	}

	@Override
	public List<String> showVersion() {
		return organizationDao.findAllVerison();
	}

	@Override
	public boolean isUnique(String id,String code, String name,String version) {
		List<Organization> organizations = organizationDao.findByCodeOrName(id,code, name,version);
		if(organizations==null || organizations.size()<1) return true;
		return false;
	}

	@Override
	public void save(Organization organization) {
		organizationDao.save(organization);
	}

	@Override
	public void deleteById(String id) {
		organizationDao.deleteById(id);
	}

	@Override
	public String copyCurrentVersion(String version) {
		if(version==null || "".equals(version)) return null;
		List<Organization> organizations = organizationDao.findByVersion(version);
		
		String newVersion = (Integer.valueOf(organizationDao.findLastestVersion())+1)+"";
		List<Organization> toBeSave = null;
		if(organizations!=null && organizations.size()>0){
			toBeSave = new ArrayList<Organization>();
			for(int i=0,len=organizations.size(); i<len; i++){
				Organization temp = new Organization();
				BeanUtils.copyProperties(organizations.get(i), temp);
				temp.setId("");
				temp.setPublish("0");		//复制的都是未发布的版本
				temp.setVersion(newVersion);
				toBeSave.add(temp);
			}
		}
		organizationDao.saveAll(toBeSave);
		return newVersion;
	}

	@Override
	public void publish(String version) {
		organizationDao.publish(version);
	}

	@Override
	public Organization findById(String id) {
		return organizationDao.findById(id);
	}

	@Override
	public List<Organization> findByPublish() {
		return organizationDao.findByPublish();
	}

	@Override
	public String findNextCodeId(String version) {
		String codeId = organizationDao.findMaxCodeId(version);
		String result = null;
		if(StringUtils.isNotEmpty(codeId)){
			try {
				result = (Integer.valueOf(codeId)+1)+"";
				return result;
			} catch (NumberFormatException e) {
				return result;
			}
		}
		return result ;
	}
	
	

}
