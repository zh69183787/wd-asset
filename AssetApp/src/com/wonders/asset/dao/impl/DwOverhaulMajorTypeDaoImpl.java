package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwOverhaulMajorTypeDao;
import com.wonders.asset.model.dw.DwOverhaulMajorType;

public class DwOverhaulMajorTypeDaoImpl extends
		BaseDaoImpl<DwOverhaulMajorType, String> implements
		DwOverhaulMajorTypeDao {

	public DwOverhaulMajorTypeDaoImpl() {
		super(DwOverhaulMajorType.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DwOverhaulMajorType> findDwOverhaulMajorType(String year) {
		String hql="from DwOverhaulMajorType t where t.year='"+year+"' order by t.typeId ASC";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwOverhaulMajorType> findLastestDwOverhaulMajorType() {
		String hql="from DwOverhaulMajorType t where t.createDate=(select max(t2.createDate) from DwOverhaulMajorType t2) order by t.typeId ASC";
		return getHibernateTemplate().find(hql);
	}
	
	
}
