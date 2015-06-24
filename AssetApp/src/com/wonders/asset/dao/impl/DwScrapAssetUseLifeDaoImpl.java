package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwScrapAssetUseLifeDao;
import com.wonders.asset.model.dw.DwScrapAssetUseLife;

public class DwScrapAssetUseLifeDaoImpl extends
		BaseDaoImpl<DwScrapAssetUseLife, String> implements
		DwScrapAssetUseLifeDao {

	public DwScrapAssetUseLifeDaoImpl() {
		super(DwScrapAssetUseLife.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public DwScrapAssetUseLife findDwScrapAssetUseLife(String year,String subTypeId) {
		String hql="";
		if(subTypeId==null || "".equals(subTypeId)){
			hql = "from DwScrapAssetUseLife t  where t.year='"+year+"' order by t.year DESC, to_number(t.mainTypeId) ASC,to_number(t.subTypeId) ASC";
			
		}else{
			hql = "from DwScrapAssetUseLife t where t.year='"+year+"' and t.subTypeId='"+subTypeId+"' order by t.year DESC, to_number(t.mainTypeId) ASC,to_number(t.subTypeId) ASC";
		}
		List<DwScrapAssetUseLife> list = getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DwScrapAssetUseLife> findAllDwScrapUseLifeSubTypeName(String year) {
		String hql="from DwScrapAssetUseLife t  where t.year='"+year+"' " +
				" and t.createDate=(select max(t2.createDate) from DwScrapAssetUseLife t2 where t2.year='"+year+"') order by to_number(t.mainTypeId) ASC,to_number(t.subTypeId) ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
