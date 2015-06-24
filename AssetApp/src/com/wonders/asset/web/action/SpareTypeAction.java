package com.wonders.asset.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.SpareType;
import com.wonders.asset.model.bo.SpareTypeVo;
import com.wonders.asset.service.AssetTypeService;
import com.wonders.asset.service.SpareTypeService;
import com.wonders.framework.util.ServiceProvider;

public class SpareTypeAction extends AbstractBaseAction{
	/**
	 * 获取所有备品备件
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void getAllSpareType() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		SpareTypeVo vo = ServiceProvider.getService(SpareTypeService.class).getAllSpareTypeByVersion(version);
		renderJson(vo);
	}
	
	/**
	 * 查询所有版本
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void showSpareVersion() throws DocumentException, IOException{
		List<String> versions = ServiceProvider.getService(SpareTypeService.class).showSpareVersion();
		renderJson(versions);
	}
	
	/**
	 * 保存新增
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void saveSpareType() throws DocumentException, IOException{
		String errorInfo ="";
		String parentId = getRequestParameter("parentId");
		String code = getRequestParameter("code");
		String allCode = getRequestParameter("allCode");
		String version = getRequestParameter("version");
		String name = getRequestParameter("name");
//		String codeId = getRequestParameter("codeId");
		String remark = getRequestParameter("remark");
		String publish = getRequestParameter("publish");
		
		
		String codeId = ServiceProvider.getService(SpareTypeService.class).findNextCodeId(version);
		if(allCode!=null && !"".equals(allCode)){
			if(allCode.length()==1){
				allCode = code;
			}else{
				allCode = allCode+code;
			}
		}
		boolean saveStatus = ServiceProvider.getService(SpareTypeService.class).hasCode("",parentId, code, version);
		if(saveStatus){
			errorInfo = "类型代码重复，无法新增！";
			renderJson(errorInfo);
		}else{
			SpareType spareType = new SpareType();
			spareType.setCode(code);
			spareType.setAllCode(allCode);
			spareType.setVersion(version);
			spareType.setName(name);
			spareType.setRemark(remark);	
			spareType.setParent(ServiceProvider.getService(SpareTypeService.class).findById(parentId));
			spareType.setCodeId(codeId);
			spareType.setPublish(publish);
			ServiceProvider.getService(SpareTypeService.class).save(spareType);
			renderJson("success");
		}
	}
	public void updateSpareType() throws DocumentException, IOException{
		String id = getRequestParameter("id");
		String code = getRequestParameter("code");
		String name = getRequestParameter("name");
		String remark = getRequestParameter("remark");
		
		SpareType saved = ServiceProvider.getService(SpareTypeService.class).findById(id);
		
		if(saved!=null){
			boolean saveStatus = ServiceProvider.getService(SpareTypeService.class).hasCode(id,saved.getParent().getId(), code, saved.getVersion());
			if(saveStatus){
				renderJson("类型代码重复，无法新增！");
			}else{
				saved.setCode(code);
				saved.setName(name);
				saved.setRemark(remark);
				
				String allCode = saved.getAllCode();
				if(allCode.length()==1){
					allCode = code;
				}else{
					allCode = allCode.substring(0,allCode.length()-2)+code;
					//allCode = allCode+code;
				}
				saved.setAllCode(allCode);
				ServiceProvider.getService(SpareTypeService.class).updateSpareTypeAndChildren(saved);
				renderJson("success");
			}
			
		}
	}
	public void deleteSpareType() throws DocumentException, IOException{
		String id = getRequestParameter("id");
		String version = getRequestParameter("version");
		boolean status = ServiceProvider.getService(SpareTypeService.class).hasChild(id, version);
		if(!status){
			ServiceProvider.getService(SpareTypeService.class).deleteSpareType(id);
			renderJson("success");
		}else{
			renderJson("该类型下已有子类，无法删除！");
		}
	}
	/**
	 * 复制当前版本并生成新的版本
	 * @throws DocumentException
	 * @throws IOException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void copySpareCurrentVersion() throws DocumentException, IOException, IllegalAccessException, InvocationTargetException{
		String version = getRequestParameter("version");
		String newVersion = ServiceProvider.getService(SpareTypeService.class).copyCurrentVersion(version);
		renderJson(newVersion);
	}
	
	/**
	 * 发布版本
	 * @throws DocumentException
	 */
	public void sparePublish() throws DocumentException {
		String version = getRequestParameter("version");
		ServiceProvider.getService(SpareTypeService.class).sparePublish(version);
	}
}
