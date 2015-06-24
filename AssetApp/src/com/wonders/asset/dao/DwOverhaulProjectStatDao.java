package com.wonders.asset.dao;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwOverhaulProjectStat;

/**
 * 大修更新改造项目总体情况 service
 * @author ycl
 *
 */
public interface DwOverhaulProjectStatDao extends BaseDao<DwOverhaulProjectStat, String>{


	/**
	 * 查询大修更新改造项目总体情况
	 * @return
	 */
	public DwOverhaulProjectStat findDwOverhaulProjectStat();
}
