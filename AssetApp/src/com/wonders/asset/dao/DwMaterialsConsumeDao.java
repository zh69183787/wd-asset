package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwMaterialsConsume;

/**
 * 物资消耗及人工情况
 * @author ycl
 *
 */
public interface DwMaterialsConsumeDao extends BaseDao<DwMaterialsConsume, String>{

	/**
	 * 查询物资消耗及人工情况
	 * @param year
	 * @return
	 */
	public List<DwMaterialsConsume> findDwMaterialsConsume(String year);
	
}
