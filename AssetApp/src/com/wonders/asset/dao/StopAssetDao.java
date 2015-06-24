package com.wonders.asset.dao;

import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.StopAsset;

/**
 * Created by HH on 2014/11/7.
 */
public interface StopAssetDao extends BaseDao<StopAsset, String> {
    void clear();
    void batchUpdate();
    void batchInsert();
    public Pagination<StopAsset> findStopAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize); 
}
