package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetCheckinfo;

/**
 * Created by Administrator on 2014/11/8.
 */
public interface AssetCheckinfoDao extends BaseDao<AssetCheckinfo, String> {

    void batchInsert();

    void batchUpdate();

    void clear();

	List<Object[]> getAssetTaskByYear();
}
