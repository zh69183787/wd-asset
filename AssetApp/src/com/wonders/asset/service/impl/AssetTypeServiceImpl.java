package com.wonders.asset.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.wonders.asset.base.util.Pagination;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.AssetTypeDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.Station;
import com.wonders.asset.model.bo.AssetTypeVo;
import com.wonders.asset.service.AssetTypeService;
import com.wonders.framework.util.SortUtils;

public class AssetTypeServiceImpl extends BaseServiceImpl<Asset,String> implements AssetTypeService{

	private AssetTypeDao assetTypeDao;

	public AssetTypeDao getAssetTypeDao() {
		return assetTypeDao;
	}

	public void setAssetTypeDao(AssetTypeDao assetTypeDao) {
		this.assetTypeDao = assetTypeDao;
		setBaseDao(baseDao);
	}

	@Override
	public List<AssetType> findByParentId(String parentId,String version) {
		
		return assetTypeDao.findByParentId(parentId,version);
	}

	
	
	
	@Override
	public List<AssetType> findByParentIdWithPublish(String parentId) {
		
		return assetTypeDao.findByParentIdWithPublish(parentId);
	}

	@Override
	public String findByVersion(String version,String openId) {
		
		try {
			List<Object[]> list = assetTypeDao.findByVersion(version);
			Object[] root = assetTypeDao.findByRoot(version); 
			list.add(0, root);
			List<String> openIdList = getAllOpenId(openId,version);
			String str = "";
			String data_str = "";
			if(list!=null && list.size()>0 && openIdList!=null && openIdList.size()>0){
				str += "{\"data\":[{\"state\":\"open\",";
				int len = 0;
				for(int i=0;i<list.size();i++){
					int cLen = list.get(i)[4].toString().length()/2;
					if(len < cLen) len = cLen; 
					data_str = "\"data\":\""+list.get(i)[3].toString()+"\",\"metadata\":{\"id\":\""+list.get(i)[0]+"\",\"name\":\""+list.get(i)[3]+"\",\"parentId\":\""+list.get(i)[1]+"\",\"code\":\""+list.get(i)[2]+"\",\"allCode\":\""+list.get(i)[4]+"\"}";
					if(openIdList!=null && openIdList.size()>1){
						for(int j=0;j<openIdList.size();j++){
							if(openIdList.get(j).equals(list.get(i)[1])){
								data_str = "\"state\":\"open\","+data_str;
								break;
							}
						}
					}else{
						/*if(Integer.valueOf(list.get(i)[0].toString().length())==1){
							data_str = "\"state\":\"open\","+data_str;
						}*/
					}
					
					if(i==0){//根节点
						str += data_str;
					}else if(String.valueOf(list.get(i)[1]).equals(String.valueOf(list.get(i-1)[1]))){//同级节点
						str += "},{"+data_str;
					}else if(list.get(i)[1].toString().equals(list.get(i-1)[0])){//下级节点
						str += ",\"children\":[{"+data_str;
					}else{//上级同辈节点
						for(int j=0;j<(list.get(i-1)[4].toString().length()/2-list.get(i)[4].toString().length()/2);j++){
							str += "}]";
						}
						str += "},{"+data_str;
					}
				}
				
				/*if(list.get(list.size()-1)[4].toString().length()==2){
					len = len - 1;  
				}
				if(list.get(list.size()-1)[4].toString().length()==6){
					len = len + 1;  
				}*/
				
				len = list.get(list.size()-1)[4].toString().length()/2+1;  
				
				for(int j=0;j < len; j++){			//有几层
					str += "}]";
				}
				str += "}";
			}
			return str;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 取得所有祖辈节点（包括自己）
	 * @param openId
	 * @return
	 */
	private List<String> getAllOpenId(String openId,String version){
		List<String> openIdList = new ArrayList<String>();
		if(openId==null || "".equals(openId)){
			openIdList = assetTypeDao.findFirstLevel(version);
			return openIdList;
		}
		AssetType bo = assetTypeDao.findById(openId);
		if(bo!=null && bo.getParent()!=null && bo.getParent().getId()!=null){
			if(!"0".equals(bo.getParent().getId())){
				openIdList = getAllOpenId(bo.getParent().getId(),version);
			}
			openIdList.add(openId);
		}
		return openIdList;
	}

	@Override
	public List<AssetType> findByVersionAndParentId(String version,
			String parentId) {
		return assetTypeDao.findByVersionAndParentId(version, parentId);
	}

	@Override
	public String copyCurrentVersion(String version) throws IllegalAccessException, InvocationTargetException {
		
		if(version==null || "".equals(version)) return null;
		List<AssetType> currentVersionList = assetTypeDao.findAssetTypeByVersion(version);
		List<AssetType> newVersionList = new ArrayList<AssetType>();
		
		String newVersion = (Integer.valueOf(assetTypeDao.findLastestVersion())+1)+"";
		
		Map<String,AssetType> map = new HashMap<String, AssetType>();
		
		if(currentVersionList!=null && currentVersionList.size()>0){
			for(int i=0,len=currentVersionList.size(); i<len; i++){
				AssetType temp = new AssetType();
				AssetType current = currentVersionList.get(i);
				/*temp.setCode(current.getCode());
				temp.setName(current.getName());
				temp.setVersion(newVersion);
				temp.setAllCode(current.getAllCode());
				temp.setId(UUID.randomUUID().toString());
				temp.setParent(current.getParent());*/
				
				BeanUtils.copyProperties(temp, current);
				temp.setPublish("0");
				temp.setVersion(newVersion);
				temp.setChildren(null);
				temp.setId(UUID.randomUUID().toString());
				
				map.put(current.getId(), temp);
			}
			
			Iterator<String> keySetIterator = map.keySet().iterator();
			while (keySetIterator.hasNext()) {	//循环map
				
				String key = keySetIterator.next();		//key 就是上一套数据的id
				AssetType assetType = map.get(key);
				
				if(assetType.getParent()!=null){
					assetType.setParent(map.get(assetType.getParent().getId()));
				}
				newVersionList.add(assetType);
			}
			
		}
		assetTypeDao.saveAll(newVersionList);
		return newVersion;
	}

	@Override
	public List<String> showVersion() {
		
		return assetTypeDao.findAllVersion();
	}

	@Override
	public AssetType findById(String id) {
		
		return assetTypeDao.findById(id);
	}

	@Override
	public void save(AssetType assetType) {
		assetTypeDao.save(assetType);
	}

	@Override
	public void update(AssetType assetType) {
		assetTypeDao.update(assetType);
	}

	@Override
	public void deleteAssetType(String id) {
		assetTypeDao.deleteAssetTypeById(id);
	}

	@Override
	public AssetTypeVo getAllAssetTypeByVersion(String version) {
		
			AssetType assetType = assetTypeDao.findRootByVersion(version);
			AssetTypeVo vo = this.convertBoToVo(assetType);		//顶层。
			if(assetType!=null){
				List<AssetType> type1List = this.getSortChildren(assetType.getChildren());			//所有大类
				vo.setChildren(this.converBoListToVoList(type1List));
				if(type1List!=null && type1List.size()>0){
					for(int i=0,len=type1List.size(); i<len; i++){							//循环中类
						AssetType type1 = type1List.get(i);
						List<AssetType> type2List = this.getSortChildren(type1.getChildren());
						vo.getChildren().get(i).setChildren(this.converBoListToVoList(type2List));
						if(type2List!=null && type2List.size()>0){
							for(int j=0,len2=type2List.size(); j<len2; j++){			//循环小类
								AssetType type2 = type2List.get(j);
								List<AssetType> type3List = this.getSortChildren(type2.getChildren());								
								vo.getChildren().get(i).getChildren().get(j).setChildren(this.converBoListToVoList(type3List));
							}
						}
					}
				}
			}
			return vo;
	}
		
		
	public AssetTypeVo findAndSetChildren(AssetTypeVo assetTypeVo){
		if(assetTypeVo!=null && assetTypeVo.getId()!=null ){
			List<AssetType> list = assetTypeDao.findByParentId(assetTypeVo.getId(), assetTypeVo.getVersion());
			List<AssetTypeVo> voList = null;
			if(list!=null && list.size()>0){
				voList = new ArrayList<AssetTypeVo>();
				for(int i=0,len=list.size(); i<len; i++){
					AssetType at = list.get(i);
					AssetTypeVo vo = new AssetTypeVo();
					vo.setId(at.getId());
					vo.setName(at.getName());
					vo.setVersion(at.getVersion());
					vo.setPublish(at.getPublish());
					vo.setAllCode(at.getAllCode());
					this.findAndSetChildren(vo);
					voList.add(vo);
				}
			}
			assetTypeVo.setChildren(voList);
			return assetTypeVo;
		}else{
			return null;
		}
		
	}
	

	public List<AssetType> getSortChildren(Set<AssetType> children) {
		List<AssetType> sortList = null;
		if (children != null && children.size() > 0) {
			sortList = new ArrayList<AssetType>();
			Map<Integer, AssetType> map = new HashMap<Integer, AssetType>();
			Iterator<AssetType> it = children.iterator();
			int[] array = new int[children.size()];
			int i = 0;
			while (it.hasNext()) {
				AssetType assetType = it.next();
				map.put(Integer.valueOf(assetType.getCode()), assetType);
				array[i] = Integer.valueOf(assetType.getCode());
				i++;
			}
			
			array = SortUtils.quickSort(array, 0, array.length-1);
			for(int j=0,len=array.length;j<len; j++){
				sortList.add(map.get(array[j]));
			}
			
		}
		return sortList;
	}
	
	public AssetTypeVo convertBoToVo(AssetType bo){
		if(bo!=null){
			AssetTypeVo vo = new AssetTypeVo();
			vo.setId(bo.getId());
			vo.setName(getFormatedText(bo.getName()));
			vo.setFullName(bo.getName());
			vo.setAllCode(bo.getAllCode());
			vo.setVersion(bo.getVersion());
			vo.setPublish(bo.getPublish());
			vo.setCode(bo.getCode());
			vo.setOverhaulFrequency(bo.getOverhaulFrequency());
			vo.setUseLife(bo.getUseLife());
			vo.setCodeId(bo.getCodeId());
			return vo;
		}else{
			return null;
		}
	}
	
	public String getFormatedText(String text){
		if(text==null || "".equals(text)) return text;
		if(text.length()>17) return (text.substring(0,17)+"...");
		return text;
	}
	
	public List<AssetTypeVo> converBoListToVoList(List<AssetType> boList){
		List<AssetTypeVo>  voList = null;
		if(boList!=null && boList.size()>0){
			voList = new ArrayList<AssetTypeVo>();
			for(int i=0,len=boList.size(); i<len; i++){
				voList.add(this.convertBoToVo(boList.get(i)));
			}
		}
		return voList;
	}

	@Override
	public void publish(String version) {
		assetTypeDao.publish(version);
	}

	@Override
	public boolean hasChild(String parentId,String version) {
		List<AssetType> list = assetTypeDao.findByParentId(parentId, version);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public boolean hasCode(String id,String parentId, String code, String version) {
		List<AssetType> list = assetTypeDao.findByParentIdAndCodeAndVersion(id,parentId, code, version);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public void updateAssetTypeAndChildren(AssetType assetType) {
		try {
			assetTypeDao.update(assetType);
			
			assetTypeDao.updateChildrenAllCode(assetType.getId(),assetType.getAllCode());
			List<AssetType> list = assetTypeDao.findByParentId(assetType.getId(), assetType.getVersion());
			if(list!=null && list.size()>0){
				for(int i=0,len=list.size(); i<len; i++){
					AssetType temp = list.get(i);
					assetTypeDao.updateChildrenAllCode(temp.getId(),temp.getAllCode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isCodeIdExist(String codeId) {
		int count = assetTypeDao.findCountOfCodeId(codeId);
		if(count>0) return true;
		return false;
	}

	@Override
	public String findNextCodeId(String version) {
		String codeId = this.assetTypeDao.findMaxCodeIdByVerison(version);
		String result = null;
		if(StringUtils.isNotEmpty(codeId)){
			try {
				result = (Integer.valueOf(codeId)+1)+"";
				return result;
			} catch (NumberFormatException e) {
				return result;
			} 
		}
		return null;
	}

	@Override
	public AssetType findMainTypeByCode(String codeId) {
		
		return assetTypeDao.findById(codeId);
	}

	@Override
	public AssetType findByParentIdAndCode(String parentId, String code) {
		
		return assetTypeDao.findByParentIdAndCode(parentId, code);
	}

	@Override
	public AssetType findMainTypeByCodeWithPublish(String code) {
		return assetTypeDao.findMainTypeByCodeWithPublish(code);
	}

	@Override
	public AssetType findByAllCode(String allCode) {
		return assetTypeDao.findByAllCode(allCode);
	}





}
