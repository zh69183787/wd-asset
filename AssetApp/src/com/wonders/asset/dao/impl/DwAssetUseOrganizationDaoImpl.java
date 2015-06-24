package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetUseOrganizationDao;
import com.wonders.asset.model.dw.DwAssetUseOrganizationUnit;

@SuppressWarnings("unchecked")
public class DwAssetUseOrganizationDaoImpl extends BaseDaoImpl implements
		DwAssetUseOrganizationDao {

	public DwAssetUseOrganizationDaoImpl() {
		super(DwAssetUseOrganizationUnit.class);
	}

	@Override
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganization() {
		String hql="from DwAssetUseOrganizationUnit t where t.createDate = (select max(t2.createDate) from DwAssetUseOrganizationUnit t2) order by to_number(t.order_) asc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganizationByDate(
			String start, String end) {
		String hql="from DwAssetUseOrganizationUnit t where t.createDate = " +
				"(select max(t2.createDate) from DwAssetUseOrganizationUnit t2 where t2.createDate>=to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t2.createDate<=to_date('"+end+"','yyyy-mm-dd hh24:mi:ss')) " +
				" order by to_number(t.order_) asc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<String> findAllYearOfDwAssetUseOrganizationUnit() {
		String sql="select to_char(t.create_date,'yyyy') year from DW_ASSET_USE_ORGANIZATION_UNIT t group by to_char(t.create_date,'yyyy') order by to_char(t.create_date,'yyyy') desc";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}


	

}
