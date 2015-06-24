package com.wonders.asset.dao;


import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetMaintenanceCost;

/**
 * 设备DAO接口
 */

public interface AssetMaintenanceCostDao extends BaseDao<AssetMaintenanceCost, String> {
	
	public List<AssetMaintenanceCost> findByAssetNo(String assetNo);
}