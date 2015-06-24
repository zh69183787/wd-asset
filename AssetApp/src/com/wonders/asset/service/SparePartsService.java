package com.wonders.asset.service;

import java.util.Date;
import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.SpareParts;

public interface SparePartsService extends BaseService<SpareParts, String>{
	/**
	 * 同步数据插入及更新
	 * @param startDate
	 * @param endDate
	 */
	public void syncInsertData(Date startDate, Date endDate);
	public void syncUpdateData(Date startDate, Date endDate);
}
