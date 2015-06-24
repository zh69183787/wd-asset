package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetImportantRatio;

public interface DwAssetImportantRatioDao extends BaseDao<DwAssetImportantRatio, String>{

	/**
	 * 查询最新重要资产
	 * @return
	 */
	public List<DwAssetImportantRatio> findLastestDwImportantRation();
	
} 
