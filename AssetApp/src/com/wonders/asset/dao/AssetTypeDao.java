package com.wonders.asset.dao;

import java.util.List;

import com.wonders.asset.base.dao.BaseDao;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.AssetType;

public interface AssetTypeDao extends BaseDao<AssetType, String> {

	/**
	 * 根据父id查询
	 * @param parentId
	 * @return
	 */
	public List<AssetType> findByParentId(String parentId,String version);
	
	/**
	 * 根据父id查询
	 * @param parentId
	 * @return
	 */
	public List<AssetType> findByParentIdWithPublish(String parentId);
	
	/**
	 * 根据类型名称查询
	 * @param name
	 * @return
	 */
	public List<AssetType> findByNameAndParentId(String name,String parentId);
	
	/**
	 * 查询
	 * @param name
	 * @param lineCodeId 
	 * @return
	 */
	public List<AssetType> findByNameAndLineCodeId(String name,String lineCodeId);
	
	public List<AssetType> findByNameWithPublish(String name);
	
	
	public List<Object[]> findByVersion(String version);
	
	public AssetType findById(String id);
	
	public List<AssetType> findByVersionAndParentId(String version,String parentId);
	
	public Object[] findByRoot(String version);
	
	public List<AssetType> findAssetTypeByVersion(String version);
	
	public void saveAll(List<AssetType> list);
	
	public List<String> findFirstLevel(String version);
	
	public String findLastestVersion();
	
	public List<String> findAllVersion();
	
	public void save(AssetType assetType);
	
	public void deleteAssetTypeById(String id);
	
	/**
	 * 查询所有资产类型
	 * @param 版本
	 */
	public void getAllAssetTypeByVersion(String version);
	
	public AssetType findRootByVersion(String version);
	
	/**
	 * 发布
	 * @param version 版本
	 */
	public void publish(String version);
	
	/**
	 * 查询
	 * @param parentId 父id
	 * @param code	代码
	 * @param version	版本
	 * @return	类型
	 */
	public List<AssetType> findByParentIdAndCodeAndVersion(String id,String parentId,String code,String version);
	
	/**
	 * 更新子类型的allCode字段
	 * @param parentId
	 * @param parentAllCode
	 */
	public void updateChildrenAllCode(String parentId,String parentAllCode);
	
	/**
	 * 查询codeId的总数
	 * @param codeId
	 * @return
	 */
	public int findCountOfCodeId(String codeId);
	
	/**
	 * 查询当前版本下的最大codeId
	 * @param version
	 * @return
	 */
	public String findMaxCodeIdByVerison(String version);
	
	/**
	 * 查询大类
	 * @param code
	 * @return
	 */
	public AssetType findMainTypeByCodeWithPublish(String code);
	
	/**
	 * 查询
	 * @param parentId 父id
	 * @param code	编码
	 * @return
	 */
	public AssetType findByParentIdAndCode(String parentId,String code);

    public AssetType findByAllCode(String allcode);

}
