package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetTask;
import com.wonders.asset.model.Attach;

public interface AssetTaskDao extends BaseDao<AssetTask, String> {
	
	public int findSumOfTaskCheckByTaskId(String taskId);
	
	public void saveAttach(Attach attach);
	
	public List<Attach> findAttachByGroupId(String groupId);
	
	public Attach findAttachById(Long id);
	
	public void deleteAttachById(Long id);
	
	public List<Object[]> findCheckInfoAndAssetByTaskId(String id,int startIndex,int pageSize);

    void batchInsert();

    void batchUpdate();

    void clear();

    public float getAssetCompleteRate();

	public List<Object[]> getAssetTaskByYear();
}
