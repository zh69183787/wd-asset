package com.wonders.asset.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwAssetEntityStatDao;
import com.wonders.asset.model.dw.DwAssetEntityStat;

@SuppressWarnings("unchecked")
public class DwAssetEntityStatDaoImpl extends BaseDaoImpl<DwAssetEntityStat, String> implements
		DwAssetEntityStatDao {

	public DwAssetEntityStatDaoImpl() {
		super(DwAssetEntityStat.class);
	}

	@Override
	public List<DwAssetEntityStat> findAssetStatByTree() {
		String sql = "select {stat}.ID AS {stat.id},{stat}.CODE AS {stat.code},{stat}.P_CODE AS {stat.pcode},"
				+ "{stat}.MAIN_TYPE_CODE AS {stat.mainTypeCode},{stat}.SUB_TYPE_CODE AS {stat.subTypeCode},"
				+ "{stat}.DETAIL_TYPE_CODE AS {stat.detailTypeCode},{stat}.NAME AS {stat.name},{stat}.COUNT AS {stat.count},"
				+ "{stat}.ORIGINAL_VALUE AS {stat.originalValue}, {stat}.CREATE_DATE AS {stat.createDate}"
				//+ " from dw_asset_entity_stat {stat} "
				+ " from (select * from dw_asset_entity_stat t2 where t2.create_date=(select max(t3.create_date) from dw_asset_entity_stat t3)) {stat}"
				//+" where {stat}.create_date = (select max(t.create_date) from dw_asset_entity_stat t)"
				+ " connect by prior {stat}.CODE = {stat}.P_CODE start with {stat}.P_CODE = '00' "
				+ " order SIBLINGS by {stat}.CODE";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity("stat", DwAssetEntityStat.class);
		List<DwAssetEntityStat> list = query.list();
		return list;
	}
	
	
	
	@Override
	public List<DwAssetEntityStat> findAssetStatByTreeAndYear(String start,String end) {
		String sql = "select {stat}.ID AS {stat.id},{stat}.CODE AS {stat.code},{stat}.P_CODE AS {stat.pcode},"
			+ "{stat}.MAIN_TYPE_CODE AS {stat.mainTypeCode},{stat}.SUB_TYPE_CODE AS {stat.subTypeCode},"
			+ "{stat}.DETAIL_TYPE_CODE AS {stat.detailTypeCode},{stat}.NAME AS {stat.name},{stat}.COUNT AS {stat.count},"
			+ "{stat}.ORIGINAL_VALUE AS {stat.originalValue}, {stat}.CREATE_DATE AS {stat.createDate}"
			//+ " from dw_asset_entity_stat {stat} "
			+ " from (select * from dw_asset_entity_stat t2 where t2.create_date=(select max(t3.create_date) from dw_asset_entity_stat t3 where t3.create_date  >=to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t3.create_date<=to_date('"+end+"','yyyy-mm-dd hh24:mi:ss'))) {stat}"
			//+" where {stat}.create_date = (select max(t.create_date) from dw_asset_entity_stat t)"
			+ " connect by prior {stat}.CODE = {stat}.P_CODE start with {stat}.P_CODE = '00' "
			+ " order SIBLINGS by {stat}.CODE";
		Session session = this.getHibernateTemplate().getSessionFactory()
				.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity("stat", DwAssetEntityStat.class);
		List<DwAssetEntityStat> list = query.list();
		return list;
	}

	@Override
	public List<DwAssetEntityStat> findAssetStatMainType() {
		String hql = "from DwAssetEntityStat t where t.pcode='00'" +
				" and t.createDate = (select max(t2.createDate) from DwAssetEntityStat t2)"+
				" order by to_number(t.code) asc";
		return getHibernateTemplate().find(hql);
	}

	
	
	@Override
	public List<DwAssetEntityStat> findReportMainTypeByDate(String start,String end) {
		String hql = "from DwAssetEntityStat t where t.pcode='00'" +
				" and t.createDate = (select max(t2.createDate) from DwAssetEntityStat t2 where t2.createDate>=to_date('"+start+"','yyyy-mm-dd hh24:mi:ss') and t2.createDate<=to_date('"+end+"','yyyy-mm-dd hh24:mi:ss'))"+
				" order by to_number(t.code) asc";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<DwAssetEntityStat> findAssetStatMainTypeForCHart() {
		String hql="from DwAssetEntityStat t where t.pcode='00' order by t.originalValue DESC";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		return query.list();
	}

	@Override
	public List<String> findAllYearOfDwAssetEntityStat() {
		String sql="select to_char(t.create_date,'yyyy') year from DW_ASSET_ENTITY_STAT t group by to_char(t.create_date,'yyyy') order by to_char(t.create_date,'yyyy') desc";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	
}
