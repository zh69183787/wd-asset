package com.wonders.asset.service;

import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.AssetOverhaul;

public interface AssetOverhaulService extends BaseService<AssetOverhaul, String>{

	public List<AssetOverhaul> findByAssetNo(String assetNo);
}
