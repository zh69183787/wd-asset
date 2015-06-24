package com.wonders.asset.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.asset.base.util.Pagination;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.LineDao;
import com.wonders.asset.model.Line;

public class LineDaoImpl extends BaseDaoImpl<Line, String> implements LineDao{
    private org.hibernate.SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public LineDaoImpl() {
		super(Line.class);
	}

    @Override
    public Pagination findAllTheLine(int page, int pageSize) {
        String sql="select * from (select id,name from T_LINE)";
        List<Object[]> result=sessionFactory2.getCurrentSession().createSQLQuery(sql) .setFirstResult(page).setMaxResults(pageSize).list();
        List<Line> list=new ArrayList<Line>();
        for(Object[]o:result){
            Line line=new Line();
            line.setId((String)o[0]);
            line.setName((String)o[1]);
            list.add(line);
        }
        Pagination pagination=new Pagination();
        pagination.setPageNo(page);
        pagination.setPageSize(pageSize);
        pagination.setResult(list);
        pagination.setPageSize(pageSize);
        return pagination;
    }

    @Override
	@SuppressWarnings("unchecked")
	public Line findByCode(String code) {
		String hql="from Line t where t.code='"+code+"'";
		List<Line> lines = getHibernateTemplate().find(hql);
		if(lines!=null && lines.size()==1){
			return lines.get(0);
		}
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findAllByPublish(String publish) {
		String hql="from Line t where t.publish=? order by to_number(t.code) ASC";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, publish);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findAllByOrder() {
		String hql="from Line t order by to_number(t.code) ASC";
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllVersion() {
		String sql = "select t.version from T_LINE t group by t.version order by to_number(t.version) DESC";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addScalar("version", Hibernate.STRING);
		return query.list();
	}

	@Override
	public void saveLine(Line line) {
		getHibernateTemplate().save(line);
	}

	@Override
	public Line findById(String id) {
		return (Line) getHibernateTemplate().get(Line.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findByCondition(String id,String name,String code,String version) {
		String hql="from Line t where (t.name=? or t.code=?) and t.version=?";
		if(StringUtils.isNotBlank(id)){
			hql += " and t.id!=?";
		}
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, name).setString(1, code).setString(2, version);
		if(StringUtils.isNotBlank(id)){
			query.setString(3, id);
		}
		return query.list();
	}

	@Override
	public void deleteById(String id) {
		String hql ="delete Line t where t.id=?";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findByVersion(String version) {
		String hql ="from Line t where t.version=? order by TO_NUMBER(t.code) ASC";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setString(0, version);
		return query.list();
	}

	@Override
	public void publish(String version) {
		String hql="update Line t set t.publish='0' where t.publish='1'";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
		String hql2 = "update Line t set t.publish='1'where t.version=?";
		Query query2 = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql2);
		query2.setString(0, version);
		query2.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Line findByCodeIdWithPublish(String codeId) {
		String hql="from Line t where t.publish='1' and t.codeId=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, codeId);
		List<Line> list = query.list();
		if(list==null || list.size()!=1) return null;
		return list.get(0);
	}

	@Override
	public Line findByCodeWithPublish(String code) {
		String hql="from Line t where t.publish='1' and t.code=?";
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, code);
		return (Line) query.uniqueResult();
	}

	@Override
	public int findLasetVersion() {
		String sql="select max(to_number(t.version)) version from T_Line t";
		SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.addScalar("version", Hibernate.INTEGER);
		int result = (Integer) query.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Line> findByVersionAndCodeId(String id, String version,String codeId) {
		String hql="from Line t where t.version=? and t.codeId=?";
		if(StringUtils.isNotEmpty(id)){
			hql += " and t.id !=?";
		}
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0, version).setString(1, codeId);
		if(StringUtils.isNotEmpty(id)){
			query.setString(2, id);
		}
		return query.list();
	}

	@Override
	public String findMaxCodeId(String version) {
		String sql="select max(to_number(t.code_id)) codeId from T_Line t where t.version=?";
		SQLQuery sqlQuery = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		sqlQuery.setString(0, version);
		sqlQuery.addScalar("codeId", Hibernate.STRING);
		return (String) sqlQuery.uniqueResult();
	}
	
	
}
