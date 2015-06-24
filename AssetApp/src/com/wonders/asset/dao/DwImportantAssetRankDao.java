package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwImportantAssetRank;

public interface DwImportantAssetRankDao extends BaseDao<DwImportantAssetRank, String>{


	public List<Object> findImportantAssetRankType();
	
	public List<DwImportantAssetRank> findImportantAssetRankByType(String type);
} 
