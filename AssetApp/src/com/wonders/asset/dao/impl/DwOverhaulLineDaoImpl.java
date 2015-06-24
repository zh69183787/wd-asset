package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwOverhaulLineDao;
import com.wonders.asset.model.dw.DwOverhaulLine;

public class DwOverhaulLineDaoImpl extends
		BaseDaoImpl<DwOverhaulLine, String> implements
		DwOverhaulLineDao {

	public DwOverhaulLineDaoImpl() {
		super(DwOverhaulLine.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DwOverhaulLine> findDwOverhaulLine(String year) {
		String hql="from DwOverhaulLine t where t.year='"+year+"' order by t.lineId ASC";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwOverhaulLine> findLastestDwOverhaulLine() {
		String hql="from DwOverhaulLine t where t.createDate = (select max(t2.createDate) from DwOverhaulLine t2) " +
				" order by to_number(t.order_) ASC";
		return getHibernateTemplate().find(hql);
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DwOverhaulLine> findDwOverhaulLineByYear(String year) {
		String hql="from DwOverhaulLine t where t.year = ? " +
				" and t.createDate=(select max(t2.createDate) from DwOverhaulLine t2 where t2.year=?)"+
				" ORDER BY t.order_ ASC " +
			" order by to_number(t.order_) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).setString(0, year).setString(1, year);
		return query.list();
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<String> findDwOverhaulLineYear() {
		String sql="select t.year from DW_OVERHAUL_LINE t group by t.year ORDER BY t.year desc";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).addScalar("year", Hibernate.STRING);
		return query.list();
	}

	
	
}
