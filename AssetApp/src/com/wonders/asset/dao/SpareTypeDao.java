package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.SpareParts;
import com.wonders.asset.model.SpareType;

public interface SpareTypeDao extends BaseDao<SpareType, String>{
	public SpareType findRootByVersion(String version);	
	public List<String> findAllVersion();
	public void sparePublish(String version);
	/**
	 * 根据父ID查询
	 * @param parentId
	 * @param version
	 * @return
	 */
	public List<SpareType> findByParentId(String parentId,String version);
	
	public void deleteSpareTypeById(String id);
	
	/**
	 * 查询当前版本下最大的codeId
	 * @param version
	 * @return
	 */
	public String findMaxCodeIdByVerison(String version);
	
	public List<SpareType> findByParentIdAndCodeAndVersion(String id,String parentId,String code,String version);
	public SpareType findById(String id);
	public void save(SpareType spareType);
	
	/**
	 * 更新子类型的allCode字段
	 * @param parentId
	 * @param parentAllCode
	 */
	public void updateChildrenAllCode(String parentId,String parentAllCode);
	
	public List<SpareType> findSpareTypeByVersion(String version);
	public String findLastestVersion();
	public void saveAll(List<SpareType> list);
	
	public List<SpareType> find(SpareType example);
	
}
