package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwProjectPriceByYear;

public interface DwProjectPriceByYearDao extends BaseDao<DwProjectPriceByYear, String>{

	public List<DwProjectPriceByYear> findAllByOrder();
}
