package com.wonders.asset.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetProjectRelation;
import com.wonders.asset.model.AssetRecord;

public interface AssetService extends BaseService<Asset, String>{

	public void importData() throws ParseException;
	
	public Pagination<AssetProjectRelation> findProjectRecord(Map<String,String> filter,Map<String, String> sorting,int startIndex,int pageSize); 
	
	/**
	 * 解析资产excel并入库
	 * @param fullFileName
	 */
	public void assetBatchUpload(String fullFileName);
	
	/**
	 * 查询
	 * @return
	 */
	public List<Object[]> findBeforeLastExecuteDate(Date lastExecuteDate);
	
	
	public void transAssetObjectToAssetInfo(Date lastExecuteDate) ;

    public void transAssetObjectToAssetInfo() ;
	
	/**
	 * 就为了把确定的assetNO的资产导入库中
	 */
   public void  transAssetObjectToAssetInfoWithAssetNo();
    /**
     * 查询出资产信息
     */
   public Pagination findAssetInfo(AssetInfo assetInfo, int pageNo, int pageSize);
   
   public AssetRecord findAssetRecordById(String id);
	
   public void saveOrUpdate(AssetRecord assetRecord);
   
   public Pagination findAssetRecordByProjectAppNo(
			String projectAppNo, Integer pageSize, Integer currentPageNo);
   
   public Pagination findAssetRecordByAssetId(String assetId, int startIndex, int pageSize);
}
