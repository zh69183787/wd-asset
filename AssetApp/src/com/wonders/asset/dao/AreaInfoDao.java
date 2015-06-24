package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AreaInfo;

public interface AreaInfoDao extends BaseDao<AreaInfo, String>{

	void batchInsert();

    void batchUpdate();

    void clear();
}
