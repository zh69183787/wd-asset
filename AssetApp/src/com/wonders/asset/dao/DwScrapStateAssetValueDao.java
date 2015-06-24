package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwScrapStateAssetValue;

public interface DwScrapStateAssetValueDao extends BaseDao<DwScrapStateAssetValue, String>{


	/**
	 * 查询报废资产
	 * @return
	 */
	public List<DwScrapStateAssetValue> findDwScrapStateAssetValue();
}
