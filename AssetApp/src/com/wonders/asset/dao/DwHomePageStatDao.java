package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwHomePageStat;

public interface DwHomePageStatDao extends BaseDao<DwHomePageStat, String>{

	/**
	 * 查询最新的
	 * @return
	 */
	public DwHomePageStat findLastestDwHomePageStat();
}
