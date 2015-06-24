package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetOwnerOrganizationDao;
import com.wonders.asset.model.dw.DwAssetOwnerOrganizationUnit;

@SuppressWarnings("unchecked")
public class DwAssetOwnerOrganizationDaoImpl extends BaseDaoImpl implements
		DwAssetOwnerOrganizationDao {

	public DwAssetOwnerOrganizationDaoImpl() {
		super(DwAssetOwnerOrganizationUnit.class);
	}

	@Override
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganization() {
		String hql="from DwAssetOwnerOrganizationUnit t order by to_number(t.orgId) asc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganizationByDate(
			String start, String end) {
		String hql="from DwAssetOwnerOrganizationUnit t where " +
				" t.createDate = (select max(t2.createDate) from DwAssetOwnerOrganizationUnit t2 where t2.createDate>=to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t2.createDate<=to_date('"+end+"','yyyy-mm-dd hh24:mi:ss') )"+
				" order by to_number(t.orgId) asc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<String> findAllYearOfDwAssetOwnerOrganizationUnit() {
		String sql="select to_char(t.create_date,'yyyy') year from DW_ASSET_OWNER_ORG_UNIT t group by to_char(t.create_date,'yyyy') order by to_char(t.create_date,'yyyy') desc";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	


	

}
