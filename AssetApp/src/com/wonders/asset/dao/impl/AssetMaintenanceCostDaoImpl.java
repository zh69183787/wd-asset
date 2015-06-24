package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetMaintenanceCostDao;
import com.wonders.asset.model.AssetMaintenanceCost;

/**
 * AssetMaintenanceCostDao的Hibernate实现
 * 
 */
public class AssetMaintenanceCostDaoImpl extends BaseDaoImpl<AssetMaintenanceCost, String>
		implements AssetMaintenanceCostDao {

	public AssetMaintenanceCostDaoImpl() {
		super(AssetMaintenanceCost.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AssetMaintenanceCost> findByAssetNo(String assetNo) {
		String hql="from AssetMaintenanceCost where assetNo = :assetNo ";
		Query query = getSession().createQuery(hql)
			.setParameter("assetNo", assetNo);
		return query.list();
	}

}