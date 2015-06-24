package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwOverhaulBudgetYear;

public interface DwOverhaulBudgetYearDao extends BaseDao<DwOverhaulBudgetYear, String>{


	/**
	 * 大修更新改造项目投资年变化
	 * @return
	 */
	public List<DwOverhaulBudgetYear> findDwOverhaulBudgetYear();
}
