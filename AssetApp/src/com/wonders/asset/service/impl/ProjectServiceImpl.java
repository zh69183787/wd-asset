package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.ContractDao;
import com.wonders.asset.dao.ProjectDao;
import com.wonders.asset.model.Project;
import com.wonders.asset.service.ProjectService;

public class ProjectServiceImpl extends BaseServiceImpl<Project, String> implements ProjectService{
	private ProjectDao projectDao;
	private ContractDao contractDao;

	public ContractDao getContractDao() {
		return contractDao;
	}

	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		setBaseDao(projectDao);
	}

	@Override
	public List<Project> findByLineCodeId(String lineCodeId) {
		return projectDao.findByLineCodeId(lineCodeId);
	}

	@Override
	public List<Project> findByAsset(String assetId) {
		
		return projectDao.findByAssetId(assetId);
	}

	@Override
	public Project findByProjectNo(String projectNo) {
		
		return projectDao.findByProjectNo(projectNo);
	}
	
	@Override
	public Project findByShortName(String projectName) {
		List<Project> list = projectDao.findByShortName(projectName);
		if(list!=null && list.size()>0){
			if(list.size()>1){
				System.out.println(projectName+"有"+list.size()+"个项目");
			}
			return list.get(0);
		}
		return null;
	}

	@Override
	public void syncProjectAndContract() {
		projectDao.syncProject();
		contractDao.syncContract();
	}

	@Override
	public List<Map<String,String>> findShortNameAndLineCode() {
		return projectDao.findShortNameAndLineCode();
	}

	
}
