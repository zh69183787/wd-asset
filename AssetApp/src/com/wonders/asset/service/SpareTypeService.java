package com.wonders.asset.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.SpareType;
import com.wonders.asset.model.bo.SpareTypeVo;

public interface SpareTypeService extends BaseService<SpareType, String>{
	/**
	 * 查询所有备品备件类型
	 * @param 版本
	 */
	public SpareTypeVo getAllSpareTypeByVersion(String version);
	/**
	 * 查询版本
	 * @return
	 */
	public List<String> showSpareVersion();
	
	/**
	 * 发布版本
	 * @param version
	 */
	public void sparePublish(String version);
	
	/**
	 * 判断是否有子节点
	 * @param parentId
	 * @param version
	 * @return
	 */
	public boolean hasChild(String parentId,String version);
	
	public void deleteSpareType(String id);
	
	/**
	 * 查询当前版本下的codeId
	 * @param version
	 * @return
	 */
	public String findNextCodeId(String version);
	
	/**
	 * 验证code是否重复
	 * @param id
	 * @param parentId
	 * @param code
	 * @param version
	 * @return 
	 */
	public boolean hasCode(String id,String parentId,String code,String version);
	
	public SpareType findById(String id);
	
	public void save(SpareType spareType);
	
	/**
	 * 复制并添加新版本
	 * @param version
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public String copyCurrentVersion(String version) throws IllegalAccessException, InvocationTargetException;
	
	/**
	 * 更新备品备件类型及其子类
	 * @param assetType
	 */
	public void updateSpareTypeAndChildren(SpareType spareType);
	
	
	public List<SpareType> find(SpareType example);

}
