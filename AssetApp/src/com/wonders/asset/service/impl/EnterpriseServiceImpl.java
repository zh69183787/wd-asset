package com.wonders.asset.service.impl;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.EnterpriseDao;
import com.wonders.asset.model.Enterprise;
import com.wonders.asset.service.EnterpriseService;

public class EnterpriseServiceImpl extends BaseServiceImpl<Enterprise,String> implements EnterpriseService {

	// 指定此类生成日志时，实例化日志对象。
	//private static Log logger = LogFactory.getLog(EnterpriseServiceImpl.class);

	private EnterpriseDao enterpriseDao;

	public EnterpriseDao getEnterpriseDao() {
		return enterpriseDao;
	}

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
		setBaseDao(enterpriseDao);
	}
	
}