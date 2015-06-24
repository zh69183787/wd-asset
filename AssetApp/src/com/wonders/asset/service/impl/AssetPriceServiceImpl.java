package com.wonders.asset.service.impl;

import java.util.List;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.AssetPriceDao;
import com.wonders.asset.model.AssetPrice;
import com.wonders.asset.service.AssetPriceService;

public class AssetPriceServiceImpl extends BaseServiceImpl<AssetPrice,String> implements AssetPriceService {

	private AssetPriceDao assetPriceDao;
	
	public AssetPriceDao getAssetPriceDao() {
		return assetPriceDao;
	}
	public void setAssetPriceDao(AssetPriceDao assetPriceDao) {
		this.assetPriceDao = assetPriceDao;
		setBaseDao(assetPriceDao);
	}

	@Override
	public AssetPrice findLastestAssetPriceByAssetId(String assetId) {
		List<AssetPrice> list = assetPriceDao.findByAssetId(assetId);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

	
	
}