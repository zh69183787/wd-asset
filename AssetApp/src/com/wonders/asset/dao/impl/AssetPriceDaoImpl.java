package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetPriceDao;
import com.wonders.asset.model.AssetPrice;

public class AssetPriceDaoImpl extends BaseDaoImpl<AssetPrice, String> implements AssetPriceDao{

	public AssetPriceDaoImpl() {
		super(AssetPrice.class);
	}

	@Override
	public List<AssetPrice> findByAssetId(String assetId) {
		String hql=" from AssetPrice t where t.asset.id='"+assetId+"' order by t.createDate desc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public void save(AssetPrice assetPrice) {
		getHibernateTemplate().save(assetPrice);
	}

	@Override
	public void saveOrUpdate(AssetPrice assetPrice) {
		getHibernateTemplate().saveOrUpdate(assetPrice);
	}

	
	
}
