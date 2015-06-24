package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetStateLine;

public interface DwAssetStateLineDao extends BaseDao<DwAssetStateLine, String>{

	public List<DwAssetStateLine> findAllByOrder();
	
}
