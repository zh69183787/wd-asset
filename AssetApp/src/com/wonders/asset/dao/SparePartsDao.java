package com.wonders.asset.dao;

import java.util.Date;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.SpareParts;

public interface SparePartsDao extends BaseDao<SpareParts, String>{
	/**
	 * 同步数据插入及更新
	 * @param startDate
	 * @param endDate
	 */
	public void syncInsertData(Date startDate, Date endDate);
	public void syncUpdateData(Date startDate, Date endDate);
	
}
