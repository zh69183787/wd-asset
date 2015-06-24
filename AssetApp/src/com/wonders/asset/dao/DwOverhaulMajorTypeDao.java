package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwOverhaulMajorType;

/**
 * 大修更新改造专业分布及变化 
 * @author ycl
 *
 */
public interface DwOverhaulMajorTypeDao extends BaseDao<DwOverhaulMajorType, String>{


	/**
	 * 查询大修更新改造项目总体情况
	 * @return
	 */
	public List<DwOverhaulMajorType> findDwOverhaulMajorType(String year);
	
	
	/**
	 * 查询
	 * @return
	 */
	public List<DwOverhaulMajorType> findLastestDwOverhaulMajorType();
}
