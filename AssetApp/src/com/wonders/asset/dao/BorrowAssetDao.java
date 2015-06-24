package com.wonders.asset.dao;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.BorrowAsset;

public interface BorrowAssetDao extends BaseDao<BorrowAsset, String>{

	void clear();
    void batchUpdate();
    void batchInsert();
    public Pagination<BorrowAsset> findBorrowAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}
