package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwStopStateAssetValue;

public interface DwStopStateAssetValueDao extends BaseDao<DwStopStateAssetValue, String>{

	/**
	 * 停用资产
	 */
	public List<DwStopStateAssetValue> findDwStopStateAssetValue();
}
