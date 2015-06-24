package com.wonders.asset.dao;

import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.model.DisableAsset;
/**
 * Created by HH on 2015/01/15.
 */
public interface DisableAssetDao extends BaseDao<DisableAsset, String>{
	void clear();
    void batchUpdate();
    void batchInsert();
    public Pagination<DisableAsset> findDisableAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize); 
}
