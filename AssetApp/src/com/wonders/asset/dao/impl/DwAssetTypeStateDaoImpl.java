package com.wonders.asset.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetTypeStateDao;
import com.wonders.asset.model.dw.DwAssetTypeState;

public class DwAssetTypeStateDaoImpl extends
		BaseDaoImpl<DwAssetTypeState, String> implements DwAssetTypeStateDao {

	public DwAssetTypeStateDaoImpl() {
		super(DwAssetTypeState.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DwAssetTypeState findLastest() {
		String hql="from DwAssetTypeState t order by t.createDate DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		List<DwAssetTypeState> list = query.list();
		if(list!=null && list.size()>0) return list.get(0);
		return  null;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public DwAssetTypeState findLastestByType(String type) {
		String hql="from DwAssetTypeState t where t.type=? and t.createDate=(select max(t2.createDate) from DwAssetTypeState t2) order by t.createDate DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, type);
		query.setMaxResults(1);
		List<DwAssetTypeState> list = query.list();
		if(list!=null && list.size()>0) return list.get(0);
		return  null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwAssetTypeState> findByCreateDate(Date date) {
		String hql="from DwAssetTypeState t where t.createDate = to_date('"+date+"','yyyy-MM-dd HH:mi:ss')";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwAssetTypeState> findByCreateDateAndType(Date date, String type) {
		String hql="from DwAssetTypeState t where t.createDate =to_date('"+date+"','yyyy-MM-dd HH:mi:ss') and t.type=? order by to_number(t.code) ASC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, type);
		return query.list();
	}


    @SuppressWarnings("unchecked")
	@Override
	public List<DwAssetTypeState> findByType(String type) {
		String hql="from DwAssetTypeState t where t.createDate=(select max(createDate) from DwAssetTypeState) and t.type=? order by to_number(t.code) ASC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, type);
		return query.list();
	}

	
	
}
