package com.wonders.asset.service;

import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.AssetMaintenanceCost;

public interface AssetMaintenanceCostService extends BaseService<AssetMaintenanceCost, String>{

	public List<AssetMaintenanceCost> findByAssetNo(String assetNo);
}
