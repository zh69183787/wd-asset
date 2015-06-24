package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.AssetProjectRelationDao;
import com.wonders.asset.model.AssetProjectRelation;

public class AssetProjectRelationDaoImpl extends BaseDaoImpl<AssetProjectRelation,String> implements AssetProjectRelationDao{

	public AssetProjectRelationDaoImpl() {
		super(AssetProjectRelation.class);
	}

}
