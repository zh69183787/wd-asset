package com.wonders.asset.service.impl;

import java.util.List;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.AssetOverhaulDao;
import com.wonders.asset.model.AssetOverhaul;
import com.wonders.asset.service.AssetOverhaulService;

public class AssetOverhaulServiceImpl extends BaseServiceImpl<AssetOverhaul,String> implements AssetOverhaulService {

	private AssetOverhaulDao assetOverhaulDao;
	
	public AssetOverhaulDao getAssetOverhaulDao() {
		return assetOverhaulDao;
	}

	public void setAssetOverhaulDao(AssetOverhaulDao assetOverhaulDao) {
		this.assetOverhaulDao = assetOverhaulDao;
		setBaseDao(assetOverhaulDao);
	}

	@Override
	public List<AssetOverhaul> findByAssetNo(String assetNo) {
		List<AssetOverhaul> list = assetOverhaulDao.findByAssetNo(assetNo);
		return list;
	}

}