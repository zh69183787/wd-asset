package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetState;

/**
 * Created by HH on 2014/11/5.
 */
public interface AssetStateDao extends BaseDao<AssetState, String> {
    public void saveOrUpdate(AssetState assetState);

}
