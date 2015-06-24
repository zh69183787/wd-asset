package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwImportantAssetRankDao;
import com.wonders.asset.model.dw.DwImportantAssetRank;

public class DwImportantAssetRankDaoImpl extends BaseDaoImpl<DwImportantAssetRank, String> implements
	DwImportantAssetRankDao {

	public DwImportantAssetRankDaoImpl() {
		super(DwImportantAssetRank.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findImportantAssetRankType() {
		String sql = "select t.type from dw_important_asset_rank t where t.create_date=(select max(t2.create_date) from dw_important_asset_rank t2)" +
				" group by t.type,t.order_ order by t.order_ ASC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwImportantAssetRank> findImportantAssetRankByType(String type) {
		String hql="from DwImportantAssetRank t where t.type='"+type+"' and t.createDate= (select max(t2.createDate) from DwImportantAssetRank t2) order by t.originalValue DESC";
		return getHibernateTemplate().find(hql);
	}

	
}
