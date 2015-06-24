package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwProjectPriceByYearDao;
import com.wonders.asset.model.dw.DwProjectPriceByYear;

public class DwProjectPriceByYearDaoImpl extends BaseDaoImpl<DwProjectPriceByYear, String>
		implements DwProjectPriceByYearDao {

	public DwProjectPriceByYearDaoImpl() {
		super(DwProjectPriceByYear.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwProjectPriceByYear> findAllByOrder() {
		String hql="from DwProjectPriceByYear t where t.createDate=(select max(t2.createDate) from DwProjectPriceByYear t2) order by to_number(t.year) asc";
		return getHibernateTemplate().find(hql);
	}

	
}
