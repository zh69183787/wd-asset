package com.wonders.asset.service.impl;

import java.util.Date;
import java.util.List;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.SparePartsDao;
import com.wonders.asset.model.SpareParts;
import com.wonders.asset.service.SparePartsService;

public class SparePartsServiceImpl extends BaseServiceImpl<SpareParts,String> implements SparePartsService{
	private SparePartsDao sparePartsDao;

	public SparePartsDao getSparePartsDao() {
		return sparePartsDao;
	}

	public void setSparePartsDao(SparePartsDao sparePartsDao) {
		this.sparePartsDao = sparePartsDao;
		setBaseDao(sparePartsDao);
	}

	@Override
	public void syncInsertData(Date startDate, Date endDate) {
		sparePartsDao.syncInsertData(startDate, endDate);
		
	}

	@Override
	public void syncUpdateData(Date startDate, Date endDate) {
		sparePartsDao.syncUpdateData(startDate, endDate);
		
	}	
}
