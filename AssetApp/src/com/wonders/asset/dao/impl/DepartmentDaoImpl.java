package com.wonders.asset.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.asset.base.util.Pagination;
import org.hibernate.*;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DepartmentDao;
import com.wonders.asset.model.Department;

public class DepartmentDaoImpl extends BaseDaoImpl<Department, String> implements DepartmentDao{
    private org.hibernate.SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public DepartmentDaoImpl() {
		super(Department.class);
	}




	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllVerison() {
		String sql = "select t.version from T_DEPARTMENT t group by t.version order by to_number(t.version) DESC";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("version", Hibernate.STRING);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findByCodeOrName(String id,String code, String name,String version) {
		String hql="from Department t where (t.code=? or t.name=?) and t.version=? ";
		if(id!=null && !"".equals(id)){
			hql += "and t.id!=?";
		}
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, code).setString(1, name).setString(2, version);
		if(id!=null && !"".equals(id)){
			query.setString(3, id);
		}
		return query.list();
	}

	@Override
	public void save(Department department) {
		getHibernateTemplate().save(department);
	}

	@Override
	public void deleteById(String id) {
		String hql="delete Department t where t.id=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findByVersion(String version) {
		String hql="from Department t where t.version=? order by to_number(t.code) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, version);
		return query.list();
	}

	@Override
	public void saveAll(List<Department> departments) {
		Session session = getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		try {
			Transaction tx = session.beginTransaction();
			for (int i = 0; i < departments.size(); i++) {
				session.save(departments.get(i));
			}
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void publish(String version) {
		String hql="update Department t set t.publish='0' where t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
		String hql2 = "update Department t set t.publish='1'where t.version=?";
		Query query2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql2);
		query2.setString(0, version);
		query2.executeUpdate();
	}

	@Override
	public Department findById(String id) {
		return (Department) getHibernateTemplate().get(Department.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findByPublish() {
		String hql="from Department t where t.publish='1' order by to_number(t.code) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Department findByCodeWithPublish(String codeId) {
		String hql="from Department t where t.code=? and t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, codeId);
		return (Department) query.uniqueResult();
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public Department findByCodeIdWithPublish(String codeId) {
		String hql="from Department t where t.codeId=? and t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, codeId);
		List<Department> list = query.list();
		if(list==null || list.size()!=1) return null;
		return list.get(0);
	}

	@Override
	public String findLastestVersion() {
		String sql="select max(to_number(t.version)) version from t_department t";
		SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		sqlQuery.addScalar("version", Hibernate.STRING);
		return (String) sqlQuery.uniqueResult();
	}

	@Override
	public String findMaxCodeId(String version) {
		String sql="select max(to_number(t.code_id)) codeId from t_department t where t.version=?";
		SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		sqlQuery.setString(0, version);
		sqlQuery.addScalar("codeId", Hibernate.STRING);
		return (String) sqlQuery.uniqueResult();
	}

	
	
}
