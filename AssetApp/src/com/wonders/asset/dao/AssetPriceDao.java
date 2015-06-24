package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetPrice;

public interface AssetPriceDao extends BaseDao<AssetPrice,String>{

	public List<AssetPrice> findByAssetId(String assetId);
	
	public void save(AssetPrice assetPrice);
	
	public void saveOrUpdate(AssetPrice assetPrice);
}
