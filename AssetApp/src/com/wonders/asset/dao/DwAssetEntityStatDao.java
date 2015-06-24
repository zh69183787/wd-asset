package com.wonders.asset.dao;

import java.util.Date;
import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetEntityStat;

public interface DwAssetEntityStatDao extends BaseDao<DwAssetEntityStat, String>{

	/**
	 * 查询大中小类
	 * @return
	 */
	public List<DwAssetEntityStat> findAssetStatByTree();
	
	/**
	 * 查询大中小类
	 * @return
	 */
	public List<DwAssetEntityStat> findAssetStatByTreeAndYear(String start,String end);
	
	/**
	 * 查询大类
	 * @return
	 */
	public List<DwAssetEntityStat> findAssetStatMainType();
	
	/**
	 * 查询大类原值数据
	 * @return
	 */
	public List<DwAssetEntityStat> findReportMainTypeByDate(String start,String end);
	
	/**
	 * 查询大类,10条
	 * @return
	 */
	public List<DwAssetEntityStat> findAssetStatMainTypeForCHart();
	
	public List<String> findAllYearOfDwAssetEntityStat();
}
