package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.LandAsset;

import java.util.List;
import java.util.Map;

public interface LandAssetDao extends BaseDao<LandAsset, String>{

	void clear();
    void batchUpdate();
    void batchInsert();
    public List<Map<String,String>> findReports();
	List<Object[]> getBuildAreaByLine();
}
