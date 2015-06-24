package com.wonders.asset.dao.impl;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AssetStateDao;
import com.wonders.asset.model.AssetState;
//import org.apache.cxf.service.invoker.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class AssetStateDaoImpl extends BaseDaoImpl<AssetState, String> implements
		AssetStateDao {
    private org.hibernate.SessionFactory sessionFactory2;

    public org.hibernate.SessionFactory getSessionFactory2() {
        return sessionFactory2;
    }

    public void setSessionFactory2(org.hibernate.SessionFactory sessionFactory2) {
        this.sessionFactory2 = sessionFactory2;
    }

    public AssetStateDaoImpl() {
		super(AssetState.class);
	}

	@Override
	public void saveOrUpdate(AssetState assetState) {
		getHibernateTemplate().saveOrUpdate(assetState);
	}




}
