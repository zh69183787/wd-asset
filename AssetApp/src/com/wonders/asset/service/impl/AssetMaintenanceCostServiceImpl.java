package com.wonders.asset.service.impl;

import java.util.List;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.AssetMaintenanceCostDao;
import com.wonders.asset.model.AssetMaintenanceCost;
import com.wonders.asset.service.AssetMaintenanceCostService;

public class AssetMaintenanceCostServiceImpl extends BaseServiceImpl<AssetMaintenanceCost,String> implements AssetMaintenanceCostService {

	private AssetMaintenanceCostDao assetMaintenanceCostDao;

	public AssetMaintenanceCostDao getAssetMaintenanceCostDao() {
		return assetMaintenanceCostDao;
	}

	public void setAssetMaintenanceCostDao(
			AssetMaintenanceCostDao assetMaintenanceCostDao) {
		this.assetMaintenanceCostDao = assetMaintenanceCostDao;
		setBaseDao(assetMaintenanceCostDao);
	}

	@Override
	public List<AssetMaintenanceCost> findByAssetNo(String assetNo) {
		List<AssetMaintenanceCost> list = assetMaintenanceCostDao.findByAssetNo(assetNo);
		return list;
	}
	
	
}