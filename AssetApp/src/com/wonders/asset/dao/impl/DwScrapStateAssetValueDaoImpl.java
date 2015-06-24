package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwScrapStateAssetValueDao;
import com.wonders.asset.model.dw.DwScrapStateAssetValue;

public class DwScrapStateAssetValueDaoImpl extends
		BaseDaoImpl<DwScrapStateAssetValue, String> implements
		DwScrapStateAssetValueDao {

	public DwScrapStateAssetValueDaoImpl() {
		super(DwScrapStateAssetValue.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwScrapStateAssetValue> findDwScrapStateAssetValue() {
		String hql="from DwScrapStateAssetValue t order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
