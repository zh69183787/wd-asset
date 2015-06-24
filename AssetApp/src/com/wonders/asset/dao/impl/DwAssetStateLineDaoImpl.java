package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetStateLineDao;
import com.wonders.asset.model.dw.DwAssetStateLine;

@SuppressWarnings("unchecked")
public class DwAssetStateLineDaoImpl extends
		BaseDaoImpl<DwAssetStateLine, String> implements DwAssetStateLineDao {

	public DwAssetStateLineDaoImpl() {
		super(DwAssetStateLine.class);
	}

	@Override
	public List<DwAssetStateLine> findAllByOrder() {
		String hql = "from DwAssetStateLine t order by to_number(t.lineId) ASC";
		return getHibernateTemplate().find(hql);
	}

}
