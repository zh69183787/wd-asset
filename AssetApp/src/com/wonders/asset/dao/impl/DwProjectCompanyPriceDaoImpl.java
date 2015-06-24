package com.wonders.asset.dao.impl;

import java.util.List;

import com.wonders.asset.base.dao.impl.BaseDaoImpl;
import com.wonders.asset.dao.DwProjectCompanyPriceDao;
import com.wonders.asset.model.dw.DwProjectCompanyPrice;

public class DwProjectCompanyPriceDaoImpl extends BaseDaoImpl<DwProjectCompanyPrice, String>
		implements DwProjectCompanyPriceDao {

	public DwProjectCompanyPriceDaoImpl() {
		super(DwProjectCompanyPrice.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DwProjectCompanyPrice> findAllByOrder() {
		String hql="from DwProjectCompanyPrice t where t.createDate=(select max(t2.createDate) from DwProjectCompanyPrice t2 ) order by t.order_ ASC";
		return getHibernateTemplate().find(hql);
	}

	
}
