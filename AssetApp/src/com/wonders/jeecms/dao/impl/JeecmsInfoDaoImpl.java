package com.wonders.jeecms.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.wonders.jeecms.dao.JeecmsInfoDao;

public class JeecmsInfoDaoImpl implements JeecmsInfoDao{
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	@Resource(name="hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> findWsLeaders(){
		String sql = "select login_name from v_ws_leaders";
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		return query.addScalar("login_name", Hibernate.STRING).list();
	}
}
