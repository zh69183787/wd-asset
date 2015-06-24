package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwOverhaulProjectStatDao;
import com.wonders.asset.model.dw.DwOverhaulProjectStat;

public class DwOverhaulProjectStatDaoImpl extends
		BaseDaoImpl<DwOverhaulProjectStat, String> implements
		DwOverhaulProjectStatDao {

	public DwOverhaulProjectStatDaoImpl() {
		super(DwOverhaulProjectStat.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public DwOverhaulProjectStat findDwOverhaulProjectStat() {
		String hql="from DwOverhaulProjectStat t order by t.createDate DESC";
		List<DwOverhaulProjectStat> list = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setMaxResults(1).list();
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}
	
	
}
