package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.AllocateAsset;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by HH on 2014/11/9.
 */
public interface AllocateAssetDao extends BaseDao<AllocateAsset,String> {
    void clear();
    void batchUpdate();
    void batchInsert();
    public Pagination<AllocateAsset> findAllocateAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);

}
