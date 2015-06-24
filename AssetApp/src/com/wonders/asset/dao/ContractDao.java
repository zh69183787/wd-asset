package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.Contract;

public interface ContractDao extends BaseDao<Contract, String>{

	public Contract findByContractNo(String contractNo);
	
	public void syncContract();
}
