package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwStopStateAssetValueDao;
import com.wonders.asset.model.dw.DwStopStateAssetValue;

public class DwStopStateAssetValueDaoImpl extends
		BaseDaoImpl<DwStopStateAssetValue, String> implements
		DwStopStateAssetValueDao {

	public DwStopStateAssetValueDaoImpl() {
		super(DwStopStateAssetValue.class);
	}
	
	@Override
	public List<DwStopStateAssetValue> findDwStopStateAssetValue() {
		String hql="from DwStopStateAssetValue t order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}
	
}
