package com.wonders.asset.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetLineValueDao;
import com.wonders.asset.model.dw.DwAssetLineValue;

@SuppressWarnings("unchecked")
public class DwAssetLineValueDaoImpl extends BaseDaoImpl implements
		DwAssetLineValueDao {

	public DwAssetLineValueDaoImpl() {
		super(DwAssetLineValue.class);
	}

	@Override
	public List<DwAssetLineValue> findAssetLine() {
		String hql = "from DwAssetLineValue t where t.createDate= (select max(t2.createDate) from DwAssetLineValue t2) order by t.lineId";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwAssetLineValue> findAssetLineByDate(String start,String end) {
		String hql = "from DwAssetLineValue t where t.createDate= " +
				"(select max(t2.createDate) from DwAssetLineValue t2 where t2.createDate>=to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t2.createDate<=('"+end+"','yyyy-mm-dd hh24:mi:ss')) " +
				" order by t.lineId";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<String> findAllYearOfDwAssetLineValue() {
		String sql="select to_char(t.create_date,'yyyy') year from DW_ASSET_LINE_VALUE t group by to_char(t.create_date,'yyyy') order by to_char(t.create_date,'yyyy') desc";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	
	
}
