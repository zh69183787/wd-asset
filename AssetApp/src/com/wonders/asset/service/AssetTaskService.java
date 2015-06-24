package com.wonders.asset.service;

import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.AssetTask;
import com.wonders.asset.model.Attach;
import org.dom4j.DocumentException;

import javax.xml.bind.JAXBException;


public interface AssetTaskService extends BaseService<AssetTask, String>{
	
	public int findSumOfTaskCheckByTaskId(String taskId);
	
	public AssetTask findById(String id);
	
	public void saveAttach(Attach attach);
	
	public List<Attach> findAttachByGroupId(String groupId);
	
	public Attach findAttachById(Long id);
	
	public void deleteAttachById(Long id);

	public List<Object[]> findCheckInfoAndAssetByTaskId(String id,int startIndex,int pageSize);
	
    void importData() throws JAXBException, DocumentException;

    public float getAssetTaskCompleteRate();

	public List<Object[]> getAssetTaskByYear();
}
