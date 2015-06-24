package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwOverhaulBudgetYearDao;
import com.wonders.asset.model.dw.DwOverhaulBudgetYear;

public class DwOverhaulBudgetYearDaoImpl extends BaseDaoImpl<DwOverhaulBudgetYear, String>
		implements DwOverhaulBudgetYearDao {

	public DwOverhaulBudgetYearDaoImpl() {
		super(DwOverhaulBudgetYear.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwOverhaulBudgetYear> findDwOverhaulBudgetYear() {
		String hql="from DwOverhaulBudgetYear t where t.createDate=(select max(t2.createDate) from DwOverhaulBudgetYear t2) order by to_number(t.year) ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
