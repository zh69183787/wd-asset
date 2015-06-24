package com.wonders.asset.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.SpareTypeDao;
import com.wonders.asset.model.Asset;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.SpareType;
import com.wonders.asset.model.bo.AssetTypeVo;
import com.wonders.asset.model.bo.SpareTypeVo;
import com.wonders.asset.service.SpareTypeService;
import com.wonders.framework.util.SortUtils;

public class SpareTypeServiceImpl extends BaseServiceImpl<SpareType,String> implements SpareTypeService{
	private SpareTypeDao spareTypeDao;
	

	public SpareTypeDao getSpareTypeDao() {
		return spareTypeDao;
	}

	public void setSpareTypeDao(SpareTypeDao spareTypeDao) {
		this.spareTypeDao = spareTypeDao;
	}

	@Override
	public SpareTypeVo getAllSpareTypeByVersion(String version) {
		SpareType spareType = spareTypeDao.findRootByVersion(version);
		SpareTypeVo vo = this.convertBoToVo(spareType);		//顶层。
		if(spareType!=null){
			List<SpareType> type1List = this.getSortChildren(spareType.getChildren());			//所有大类
			vo.setChildren(this.converBoListToVoList(type1List));
			if(type1List!=null && type1List.size()>0){
				for(int i=0,len=type1List.size(); i<len; i++){							//循环中类
					SpareType type1 = type1List.get(i);
					List<SpareType> type2List = this.getSortChildren(type1.getChildren());
					vo.getChildren().get(i).setChildren(this.converBoListToVoList(type2List));
					if(type2List!=null && type2List.size()>0){
						for(int j=0,len2=type2List.size(); j<len2; j++){			//循环小类
							SpareType type2 = type2List.get(j);
							List<SpareType> type3List = this.getSortChildren(type2.getChildren());								
							vo.getChildren().get(i).getChildren().get(j).setChildren(this.converBoListToVoList(type3List));
						}
					}
				}
			}
		}
		return vo;
	}
	
	public SpareTypeVo convertBoToVo(SpareType bo){
		if(bo!=null){
			SpareTypeVo vo = new SpareTypeVo();
			vo.setId(bo.getId());
			vo.setCode(bo.getCode());
			vo.setName(getFormatedText(bo.getName()));
			vo.setAllCode(bo.getAllCode());
			vo.setVersion(bo.getVersion());
			vo.setPublish(bo.getPublish());
			vo.setRemark(bo.getRemark());
			vo.setPublish(bo.getPublish());			
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
	
	public List<SpareType> getSortChildren(Set<SpareType> children) {
		List<SpareType> sortList = null;
		if (children != null && children.size() > 0) {
			sortList = new ArrayList<SpareType>();
			Map<Integer, SpareType> map = new HashMap<Integer, SpareType>();
			Iterator<SpareType> it = children.iterator();
			int[] array = new int[children.size()];
			int i = 0;
			while (it.hasNext()) {
				SpareType spareType = it.next();
				map.put(Integer.valueOf(spareType.getCode()), spareType);
				array[i] = Integer.valueOf(spareType.getCode());
				i++;
			}
			
			array = SortUtils.quickSort(array, 0, array.length-1);
			for(int j=0,len=array.length;j<len; j++){
				sortList.add(map.get(array[j]));
			}
			
		}
		return sortList;
	}
	
	public List<SpareTypeVo> converBoListToVoList(List<SpareType> boList){
		List<SpareTypeVo>  voList = null;
		if(boList!=null && boList.size()>0){
			voList = new ArrayList<SpareTypeVo>();
			for(int i=0,len=boList.size(); i<len; i++){
				voList.add(this.convertBoToVo(boList.get(i)));
			}
		}
		return voList;
	}

	@Override
	public List<String> showSpareVersion() {
		return spareTypeDao.findAllVersion();
	}

	@Override
	public void sparePublish(String version) {
		spareTypeDao.sparePublish(version);		
	}

	@Override
	public boolean hasChild(String parentId, String version) {
		List<SpareType> list = spareTypeDao.findByParentId(parentId, version);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public void deleteSpareType(String id) {
		spareTypeDao.deleteSpareTypeById(id);
	}

	@Override
	public String findNextCodeId(String version) {
		String codeId = this.spareTypeDao.findMaxCodeIdByVerison(version);
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
	public boolean hasCode(String id, String parentId, String code,
			String version) {
		List<SpareType> list = spareTypeDao.findByParentIdAndCodeAndVersion(id,parentId, code, version);
		if(list!=null && list.size()>0) return true;
		return false;
	}

	@Override
	public SpareType findById(String id) {
		return spareTypeDao.findById(id);
	}

	@Override
	public void save(SpareType spareType) {
		spareTypeDao.save(spareType);	
	}

	@Override
	public void updateSpareTypeAndChildren(SpareType spareType) {
		try {
			spareTypeDao.update(spareType);
			
			spareTypeDao.updateChildrenAllCode(spareType.getId(),spareType.getAllCode());
			List<SpareType> list = spareTypeDao.findByParentId(spareType.getId(), spareType.getVersion());
			if(list!=null && list.size()>0){
				for(int i=0,len=list.size(); i<len; i++){
					SpareType temp = list.get(i);
					spareTypeDao.updateChildrenAllCode(temp.getId(),temp.getAllCode());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String copyCurrentVersion(String version)
			throws IllegalAccessException, InvocationTargetException {
		if(version==null || "".equals(version)) return null;
		List<SpareType> currentVersionList = spareTypeDao.findSpareTypeByVersion(version);
		List<SpareType> newVersionList = new ArrayList<SpareType>();
		
		String newVersion = (Integer.valueOf(spareTypeDao.findLastestVersion())+1)+"";
		
		Map<String,SpareType> map = new HashMap<String, SpareType>();
		
		if(currentVersionList!=null && currentVersionList.size()>0){
			for(int i=0,len=currentVersionList.size(); i<len; i++){
				SpareType temp = new SpareType();
				SpareType current = currentVersionList.get(i);
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
				SpareType spareType = map.get(key);
				
				if(spareType.getParent()!=null){
					spareType.setParent(map.get(spareType.getParent().getId()));
				}
				newVersionList.add(spareType);
			}
			
		}
		spareTypeDao.saveAll(newVersionList);
		return newVersion;
	}

	@Override
	public List<SpareType> find(SpareType example) {
		
		return spareTypeDao.find(example);
	}
	
	
	

}
