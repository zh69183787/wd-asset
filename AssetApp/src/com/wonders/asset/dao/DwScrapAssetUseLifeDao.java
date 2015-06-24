package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwScrapAssetUseLife;

public interface DwScrapAssetUseLifeDao extends BaseDao<DwScrapAssetUseLife, String>{


	/**
	 * 查询报废资产实际寿命统计
	 * @return
	 */
	public DwScrapAssetUseLife findDwScrapAssetUseLife(String year,String subTypeId);
	
	/**
	 * 查询报废资产实际寿命统计的所有中类
	 * @param year
	 * @return
	 */
	public List<DwScrapAssetUseLife> findAllDwScrapUseLifeSubTypeName(String year);
	
}
