package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.dw.DwAssetOwnerOrganizationUnit;

@SuppressWarnings("unchecked")
public interface DwAssetOwnerOrganizationDao extends BaseDao{

	/**
	 * 查询权属单位
	 * @return
	 */
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganization() ;
	
	
	/**
	 * 显示权属单位-资产价值统计
	 * @return
	 */
	public List<DwAssetOwnerOrganizationUnit> findAssetOwnerOrganizationByDate(String start,String end);

	
	public List<String> findAllYearOfDwAssetOwnerOrganizationUnit();
}
