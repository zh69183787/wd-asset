package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AssetOwnerDao;
import com.wonders.asset.model.AssetOwner;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AssetOwnerDaoImpl extends BaseDaoImpl<AssetOwner, String> implements AssetOwnerDao{
    private org.hibernate.SessionFactory sessionFactory2;

    public SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public AssetOwnerDaoImpl() {
		super(AssetOwner.class);
	}

	@Override
	public AssetOwner findById(String id) {
		
		return (AssetOwner) getHibernateTemplate().get(AssetOwner.class, id);
	}

	@Override
	public void saveOrUpdate(AssetOwner assetOwner) {
		getHibernateTemplate().saveOrUpdate(assetOwner);
	}




}
