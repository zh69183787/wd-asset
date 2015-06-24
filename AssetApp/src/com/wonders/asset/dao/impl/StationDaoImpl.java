package com.wonders.asset.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.StationDao;
import com.wonders.asset.model.Line;
import com.wonders.asset.model.Station;

public class StationDaoImpl extends BaseDaoImpl<Station, String> implements StationDao{
    private SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public StationDaoImpl() {
		super(Station.class);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Station findByCodeAndLineCodeIdWithPublish(String code,String lineCodeId) {
		String hql="from Station t where t.code=? and t.publish=? and t.line.codeId=? and t.line.publish=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, code).setString(1, "1").setString(2, lineCodeId).setString(3, "1");
		List<Station> list = query.list();
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}



	@Override
	@SuppressWarnings("unchecked")
	public Station findByCodeAndLineId(String code, String lineId) {
		String hql ="from Station t where t.code='"+code+"' and t.line.id='"+lineId+"'";
		List<Station> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()==1) return list.get(0);
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findByLineId(String lineId) {
		String hql="from Station t where t.line.id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, lineId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findByCondition(String id,String name,String code,String version,String lineId) {
		String hql="from Station t where (t.name=? or t.code=?) and t.version=? and t.line.id=?";
		if(StringUtils.isNotBlank(id)){
			hql += " and t.id!=?";
		}
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, name).setString(1, code).setString(2, version).setString(3, lineId);
		if(StringUtils.isNotBlank(id)){
			query.setString(4, id);
		}
		return query.list();
	}

	@Override
	public Station findById(String id) {
		return (Station) getHibernateTemplate().get(Station.class, id);
	}

	@Override
	public void saveStation(Station station) {
		getHibernateTemplate().save(station);
	}

	@Override
	public void deleteById(String id) {
		String hql="delete Station t where t.id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
	}

	@Override
	public void saveAll(List<Station> list) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < list.size(); i++) {
				session.save(list.get(i));
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(String version) {
		String hql="update Station t set t.publish='0' where t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
		String hql2 = "update Station t set t.publish='1'where t.version=?";
		Query query2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql2);
		query2.setString(0, version);
		query2.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> findByVersionAndCodeId(String id, String version,
			String codeId) {
		String hql="from Station t where t.version=? and t.codeId=?";
		if(StringUtils.isNotBlank(id)){
			hql += " and t.id!=?";
		}
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, version).setString(1, codeId);
		if(StringUtils.isNotBlank(id)){
			query.setString(2, id);
		}
		return query.list();
	}



	@Override
	public String findMaxCodeId(String version) {
		String sql="select max(to_number(t.code_id)) codeId from t_station t where t.version=?";
		SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		sqlQuery.setString(0, version);
		sqlQuery.addScalar("codeId", Hibernate.STRING);
		return (String) sqlQuery.uniqueResult();
	}


}
