package com.wonders.asset.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.bo.AssetTypeVo;

public interface AssetTypeService extends BaseService<Asset, String>{

	public List<AssetType> findByParentId(String parentId,String version);
	
	public List<AssetType> findByParentIdWithPublish(String parentId);
	
	public String findByVersion(String version,String openId);
	
	public List<AssetType> findByVersionAndParentId(String version,String parentId);
	
	public String copyCurrentVersion(String version) throws IllegalAccessException, InvocationTargetException;
	
	public List<String> showVersion();
	
	public AssetType findById(String id);
	
	public void save(AssetType assetType);
	
	public void update(AssetType assetType);
	
	public void deleteAssetType(String id);
	
	/**
	 * 查询所有资产类型
	 * @param 版本
	 */
	public AssetTypeVo getAllAssetTypeByVersion(String version);
	
	/**
	 * 发布
	 * @param version 版本
	 */
	public void publish(String version);
	
	/**
	 * 是否有子节点
	 * @param parentId	父id
	 * @return	true,false
	 */
	public boolean hasChild(String parentId,String version);
	
	
	/**
	 * 代码是否重复
	 * @param parentId 父id
	 * @param code	代码
	 * @param version	版本
	 * @return	true,false
	 */
	public boolean hasCode(String id,String parentId,String code,String version);
	
	/**
	 * 更新资产类型及其子类
	 * @param assetType 资产类型
	 */
	public void updateAssetTypeAndChildren(AssetType assetType);
	
	/**
	 * 编码id是否存在
	 * @param codeId
	 * @return
	 */
	public boolean isCodeIdExist(String codeId);
	
	/**
	 * 查询当前版本下的codeId
	 * @param version
	 * @return
	 */
	public String findNextCodeId(String version);
	
	
	/**
	 * 查询
	 * @param codeId 编码id
	 * @return
	 */
	public AssetType findMainTypeByCode(String codeId);
	
	/**
	 * 查询
	 * @param parentId 父id
	 * @param code	编码
	 * @return
	 */
	public AssetType findByParentIdAndCode(String parentId,String code);
	
	public AssetType findMainTypeByCodeWithPublish(String code);

	public AssetType findByAllCode(String allCode);


}
