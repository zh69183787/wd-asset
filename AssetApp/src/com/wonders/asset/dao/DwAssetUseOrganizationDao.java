package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetUseOrganizationUnit;

@SuppressWarnings("unchecked")
public interface DwAssetUseOrganizationDao extends BaseDao{

	/**
	 * 查询使用单位
	 * @return
	 */
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganization() ;
	
	
	/**
	 * 显示使用单位-资产价值统计
	 * @return
	 */
	public List<DwAssetUseOrganizationUnit> findAssetUseOrganizationByDate(String start,String end);

	public List<String> findAllYearOfDwAssetUseOrganizationUnit();
}
