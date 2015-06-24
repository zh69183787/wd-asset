package com.wonders.asset.dao;


import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.Enterprise;

/**
 * 企业的Dao接口
 * @author Kai Yao
 */

public interface EnterpriseDao extends BaseDao<Enterprise, String> {
	
	public List<Enterprise> findByNameAndType(String name,String type);
	
	public List<Enterprise> findByNameAndTypeWithPublish(String name,String type);
	
}