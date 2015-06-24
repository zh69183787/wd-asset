package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetImportantRatioDao;
import com.wonders.asset.model.dw.DwAssetImportantRatio;

public class DwAssetImportantRatioDaoImpl extends
		BaseDaoImpl<DwAssetImportantRatio, String> implements
		DwAssetImportantRatioDao {

	public DwAssetImportantRatioDaoImpl() {
		super(DwAssetImportantRatio.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwAssetImportantRatio> findLastestDwImportantRation() {
		String hql="from DwAssetImportantRatio t where " +
				" t.createDate=( select max(t2.createDate) from DwAssetImportantRatio t2)" +
				" order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
