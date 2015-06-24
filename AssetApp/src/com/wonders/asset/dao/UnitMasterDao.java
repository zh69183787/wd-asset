package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.UnitMaster;

import java.util.List;

public interface UnitMasterDao extends BaseDao<UnitMaster, String>{

	public UnitMaster findByName(String name);


    public List<UnitMaster> findAllTheUnitMaster();
	
}
