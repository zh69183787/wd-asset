package com.wonders.asset.dao;


import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetOverhaul;

/**
 * 设备DAO接口
 */

public interface AssetOverhaulDao extends BaseDao<AssetOverhaul, String> {
	
	public List<AssetOverhaul> findByAssetNo(String assetNo);
}