package com.wonders.asset.service;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.AssetPrice;

public interface AssetPriceService extends BaseService<AssetPrice, String>{

	public AssetPrice findLastestAssetPriceByAssetId(String assetId);
}
