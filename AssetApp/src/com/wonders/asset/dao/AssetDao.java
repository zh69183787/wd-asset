package com.wonders.asset.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Asset;



/**
 * 资产的Dao接口
 */
public interface AssetDao extends BaseDao<Asset, String> {

	
	public List<Object[]> findImportData(int pageNo,int pageSize);
	
	public void saveOrUpdate(Asset asset);
	
	public void saveAll(List<Asset> assets);
	
	public List<Object[]> findBeforeLastExecuteDate(Date lastExecuteDate);
	
	public Asset findByAssetCode(String assetCode);
	
	public void changeSessionLanguage() ;

	public List<Object[]> findBeforeLastExecuteDateWithAssetNo();

    public Pagination findSomeAssetInfo(AssetInfo assetInfo, int pageNo, int pageSize);
    
    public Integer getAllAssetCount();
    
    /**
     * 根据类型计算资产数量，更新，大修或新增等
     * @param type
     * @return
     */
    public Integer getAssetCountByType(String type);

	public int getOverhaulAsset(String detailTypeName);

	public List<Object[]> getOverhaulAssetData();

	public List<Object[]> getAssetDamage();

	public Integer getDamageAssetCount();

	public Integer getOutOfServiceAssetCount();

	public List<Map> assetDamageChangeChart();

	public List<Map> assetDamageProfessionChart();

	public List<Map> assetDamageDepartmentChart();

	public List<Map> assetDamageLineChart();
	
	/**
	 * 资产移交
	 * @return
	 */

	public List<Object[]> findAssetTransfer(String start, String end,boolean hasCount);
	public List<Object[]> getAssetTransfer(String start, String end,boolean hasCount);



}
