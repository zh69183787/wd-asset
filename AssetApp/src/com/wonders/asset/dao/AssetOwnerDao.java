package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.AssetOwner;

import java.util.List;

public interface AssetOwnerDao extends BaseDao<AssetOwner, String>{

	public AssetOwner findById(String id);
	
	public void saveOrUpdate(AssetOwner assetOwner);

}
