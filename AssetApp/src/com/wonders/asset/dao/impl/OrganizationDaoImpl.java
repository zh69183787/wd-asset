package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.OrganizationDao;
import com.wonders.asset.model.Organization;

public class OrganizationDaoImpl extends BaseDaoImpl<Organization, String> implements OrganizationDao{

	public OrganizationDaoImpl() {
		super(Organization.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> findAll() {
		String hql="from Organization t order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllVerison() {
		String sql = "select t.version from T_ORGANIZATION t group by t.version order by to_number(t.version) DESC";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("version", Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> findByCodeOrName(String id,String code, String name,String version) {
		String hql="from Organization t where (t.code=? or t.name=?) and t.version=?";
		if(id!=null && !"".equals(id)){
			hql += " and t.id!=?";
		}
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, code).setString(1, name).setString(2, version);
		if(id!=null && !"".equals(id)){
			query.setString(3, id);
		}
		return query.list();
	}

	@Override
	public void save(Organization organization) {
		getHibernateTemplate().save(organization);
	}

	@Override
	public void deleteById(String id) {
		String hql="delete Organization t where t.id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> findByVersion(String version) {
		String hql="from Organization t where t.version=? order by to_number(t.code) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, version);
		return query.list();
	}

	@Override
	public void saveAll(List<Organization> organizations) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < organizations.size(); i++) {
				session.save(organizations.get(i));
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(String version) {
		String hql="update Organization t set t.publish='0' where t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
		String hql2 = "update Organization t set t.publish='1'where t.version=?";
		Query query2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql2);
		query2.setString(0, version);
		query2.executeUpdate();
	}

	@Override
	public Organization findById(String id) {
		return (Organization) getHibernateTemplate().get(Organization.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> findByPublish() {
		String hql="from Organization t where t.publish='1' order by to_number(t.code) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Organization findByCodeWithPublish(String codeId) {
		String hql="from Organization t where t.code=? and t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, codeId);
		return (Organization) query.uniqueResult();
	}

	
	
	@Override
	public Organization findByCodeIdWithPublish(String codeId) {
		String hql="from Organization t where t.codeId=? and t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, codeId);
		List<Organization> list = query.list();
		if(list==null || list.size()!=1) return null;
		return list.get(0);
	}

	@Override
	public String findLastestVersion() {
		String sql="select max(to_number(t.version)) version from t_organization t";
		SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("version",Hibernate.STRING);
		return (String) sqlQuery.uniqueResult();
	}

	@Override
	public String findMaxCodeId(String version) {
		String sql="select max(to_number(t.code_id)) codeId from t_organization t where t.version=? ";
		SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		sqlQuery.setString(0, version);
		sqlQuery.addScalar("codeId",Hibernate.STRING);
		return (String) sqlQuery.uniqueResult();
	}

	
}
