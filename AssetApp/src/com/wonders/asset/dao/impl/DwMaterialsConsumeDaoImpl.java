package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwMaterialsConsumeDao;
import com.wonders.asset.model.dw.DwMaterialsConsume;

public class DwMaterialsConsumeDaoImpl extends
		BaseDaoImpl<DwMaterialsConsume, String> implements
		DwMaterialsConsumeDao {

	public DwMaterialsConsumeDaoImpl() {
		super(DwMaterialsConsume.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwMaterialsConsume> findDwMaterialsConsume(String year) {
		String hql="from DwMaterialsConsume t where t.year='"+year+"' order by t.typeId ASC";
		return getHibernateTemplate().find(hql);
	}
	
	
}
