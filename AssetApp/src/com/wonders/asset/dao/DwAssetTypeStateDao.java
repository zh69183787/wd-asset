package com.wonders.asset.dao;

import java.util.Date;
import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetTypeState;

public interface DwAssetTypeStateDao extends BaseDao<DwAssetTypeState, String>{

	/**
	 * 查询时间最新的一条数据
	 * @return
	 */
	public DwAssetTypeState findLastest();
	
	/**
	 * 查询时间最新的一条数据
	 * @return
	 */
	public DwAssetTypeState findLastestByType(String type);
	
	/**
	 * 根据创建时间查询
	 * @param date
	 * @return
	 */
	public List<DwAssetTypeState> findByCreateDate(Date date);
	
	/**
	 * 根据创建时间查询
	 * @param date
	 * @return
	 */
	public List<DwAssetTypeState> findByCreateDateAndType(Date date,String type);
	
	public List<DwAssetTypeState> findByType(String type);
}
