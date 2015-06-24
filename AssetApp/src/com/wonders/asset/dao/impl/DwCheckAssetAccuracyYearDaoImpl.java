package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwCheckAssetAccuracyYearDao;
import com.wonders.asset.model.dw.DwCheckAssetAccuracyYear;

public class DwCheckAssetAccuracyYearDaoImpl extends BaseDaoImpl<DwCheckAssetAccuracyYear, String>
		implements DwCheckAssetAccuracyYearDao {

	public DwCheckAssetAccuracyYearDaoImpl() {
		super(DwCheckAssetAccuracyYear.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwCheckAssetAccuracyYear> findDwCheckAssetAccuracyYear() {
		String hql="from DwCheckAssetAccuracyYear t order by to_number(t.year) ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
