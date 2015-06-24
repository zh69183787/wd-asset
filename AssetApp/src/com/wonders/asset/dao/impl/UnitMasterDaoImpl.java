package com.wonders.asset.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wonders.asset.base.util.Pagination;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.UnitMasterDao;
import com.wonders.asset.model.UnitMaster;
import org.hibernate.SessionFactory;

public class UnitMasterDaoImpl extends BaseDaoImpl<UnitMaster, String> implements UnitMasterDao{
    private org.hibernate.SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public UnitMasterDaoImpl() {
		super(UnitMaster.class);
	}

	@Override
	public UnitMaster findByName(String name) {
		String hql="from UnitMaster t where t.name='"+name+"'";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		List<UnitMaster> list = session.createQuery(hql).setMaxResults(1).list();
		session.close();
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

    @Override
    public List<UnitMaster> findAllTheUnitMaster() {
        String hql="from UnitMaster";

        return getHibernateTemplate().find(hql);
    }


}
