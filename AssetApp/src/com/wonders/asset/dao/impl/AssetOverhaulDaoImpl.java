package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetOverhaulDao;
import com.wonders.asset.model.AssetOverhaul;

/**
 * AssetOverhaulDao的Hibernate实现
 * 
 */
public class AssetOverhaulDaoImpl extends BaseDaoImpl<AssetOverhaul, String>
		implements AssetOverhaulDao {

	public AssetOverhaulDaoImpl() {
		super(AssetOverhaul.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AssetOverhaul> findByAssetNo(String assetNo) {
		String hql="from AssetOverhaul where assetNo = :assetNo ";
		Query query = getSession().createQuery(hql)
			.setParameter("assetNo", assetNo);
		return query.list();
	}

}