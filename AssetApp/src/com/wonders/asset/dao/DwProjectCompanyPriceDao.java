package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwProjectCompanyPrice;

public interface DwProjectCompanyPriceDao extends BaseDao<DwProjectCompanyPrice, String>{

	public List<DwProjectCompanyPrice> findAllByOrder();
}
