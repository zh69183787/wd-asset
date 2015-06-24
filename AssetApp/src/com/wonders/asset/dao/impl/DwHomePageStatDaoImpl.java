package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwHomePageStatDao;
import com.wonders.asset.model.dw.DwHomePageStat;

public class DwHomePageStatDaoImpl extends BaseDaoImpl<DwHomePageStat, String>
		implements DwHomePageStatDao {

	public DwHomePageStatDaoImpl() {
		super(DwHomePageStat.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DwHomePageStat findLastestDwHomePageStat() {
		String hql="from DwHomePageStat t order by t.createDate DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		List<DwHomePageStat> list = query.list();
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

	
}
