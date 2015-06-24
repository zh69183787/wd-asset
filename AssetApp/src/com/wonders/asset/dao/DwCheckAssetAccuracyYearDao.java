package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwCheckAssetAccuracyYear;

public interface DwCheckAssetAccuracyYearDao extends BaseDao<DwCheckAssetAccuracyYear, String>{

	/**
	 * 查询实物资产盘点准确率统计
	 * @return
	 */
	public List<DwCheckAssetAccuracyYear> findDwCheckAssetAccuracyYear();
}
