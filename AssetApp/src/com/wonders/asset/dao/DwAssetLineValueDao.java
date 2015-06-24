package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetLineValue;

@SuppressWarnings("unchecked")
public interface DwAssetLineValueDao extends BaseDao{

	/**
	 * 查询全部
	 * @return
	 */
	public List<DwAssetLineValue> findAssetLine() ;
	
	/**
	 * 查询线路资产统计表
	 * @return
	 */
	public List<DwAssetLineValue> findAssetLineByDate(String start,String end);
	
	
	public List<String> findAllYearOfDwAssetLineValue();
}
