package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.DamageAsset;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/6.
 */
public interface DamageAssetDao extends BaseDao<DamageAsset, String> {
    void clear();
    void batchUpdate();
    void batchInsert();
    public Pagination<DamageAsset> findDamageAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}
