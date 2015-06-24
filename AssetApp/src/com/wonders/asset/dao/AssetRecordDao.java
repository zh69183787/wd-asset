package com.wonders.asset.dao;

import java.util.List;
import java.util.Map;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.AssetRecord;



/**
 * 资产履历的Dao接口
 */
public interface AssetRecordDao extends BaseDao<AssetRecord,String> {
	public void saveOrUpdate(AssetRecord assetRecord);
	public AssetRecord findAssetRecordById(String id);
	public Pagination findAssetRecordByProjectAppNo(String projectAppNo, Integer pageSize, Integer currentPageNo);
	
	void clear();  
    void batchUpdate();
    void batchInsert();
    void updateAssetCodeType(String assetCodeType,List<String> assetNoList);
    public Pagination<AssetRecord> findRecordAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}
