package com.wonders.asset.web.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.wonders.asset.base.util.Pagination;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

import org.dom4j.DocumentException;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetType;
import com.wonders.asset.model.bo.AssetTypeVo;
import com.wonders.asset.service.AssetTypeService;
import com.wonders.framework.util.ServiceProvider;


public class AssetTypeAction extends AbstractBaseAction{

	
	
	public void findAssetType() throws DocumentException, IOException{


		String parentId = getRequestParameter("parentId");
		List<AssetType> list = ServiceProvider.getService(AssetTypeService.class).findByParentIdWithPublish(parentId);
		JsonConfig jsonConfig = basicJsonCfg.copy();
		
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
		    public boolean apply(Object source, String name, Object value) {
		        if(name.equals("parent") || name.equals("children") ) {
		            return true;
		        } else {
		            return false;
		        }
		    }
		});
		jsonConfig.registerPropertyExclusions(AssetType.class, new String[]{"children"});
		renderJson(list,jsonConfig);
	}
	
	
	/**
	 * 根据版本显示资产类型
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	public void findAssetTypeByVersion() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		String openId = getRequestParameter("openId");
		String str = ServiceProvider.getService(AssetTypeService.class).findByVersion(version,openId);
		renderJson(str);
	}
	
	/**
	 * 复制当前版本并生成新版本
	 * @throws DocumentException 
	 * @throws IOException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void copyCurrentVersion() throws DocumentException, IOException, IllegalAccessException, InvocationTargetException{
		String version = getRequestParameter("version");
		String newVersion = ServiceProvider.getService(AssetTypeService.class).copyCurrentVersion(version);
		renderJson(newVersion);
	}
	
	/**
	 * 查询所有版本
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void showVersion() throws IOException, DocumentException{
		List<String> versions = ServiceProvider.getService(AssetTypeService.class).showVersion();
		renderJson(versions);
	}
	
	/**
	 * 保存新增
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void saveAssetType() throws DocumentException, IOException{
		
		String errorInfo ="";
		String parentId = getRequestParameter("parentId");
		String code = getRequestParameter("code");
		String allCode = getRequestParameter("allCode");
		String version = getRequestParameter("version");
		String name = getRequestParameter("name");
		String useLife = getRequestParameter("useLife");
		String overhaulFrequency = getRequestParameter("overhaulFrequency");
		String publish = getRequestParameter("publish");
		
		
		String codeId = ServiceProvider.getService(AssetTypeService.class).findNextCodeId(version);
		if(allCode!=null && !"".equals(allCode)){
			if(allCode.length()==1){
				allCode = code;
			}else{
				allCode = allCode+code;
			}
		}
		boolean saveStatus = ServiceProvider.getService(AssetTypeService.class).hasCode("",parentId, code, version);
		if(saveStatus){
			errorInfo = "类型代码重复，无法新增！";
			renderJson(errorInfo);
		}else{
			AssetType assetType = new AssetType();
			assetType.setCode(code);
			assetType.setAllCode(allCode);
			assetType.setVersion(version);
			assetType.setName(name);
			assetType.setUseLife(useLife);
			assetType.setOverhaulFrequency(overhaulFrequency);
			assetType.setParent(ServiceProvider.getService(AssetTypeService.class).findById(parentId));
			assetType.setCodeId(codeId);
			assetType.setPublish(publish);
			ServiceProvider.getService(AssetTypeService.class).save(assetType);
			renderJson("success");
		}
		
	}
	
	public void updateAssetType() throws DocumentException, IOException{
		String id = getRequestParameter("id");
		String code = getRequestParameter("code");
		String name = getRequestParameter("name");
		String useLife = getRequestParameter("useLife");
		String overhaulFrequency = getRequestParameter("overhaulFrequency");
		AssetType saved = ServiceProvider.getService(AssetTypeService.class).findById(id);
		
		if(saved!=null){
			boolean saveStatus = ServiceProvider.getService(AssetTypeService.class).hasCode(id,saved.getParent().getId(), code, saved.getVersion());
			if(saveStatus){
				renderJson("类型代码重复，无法新增！");
			}else{
				saved.setCode(code);
				saved.setName(name);
				saved.setUseLife(useLife);
				saved.setOverhaulFrequency(overhaulFrequency);
				String allCode = saved.getAllCode();
				if(allCode.length()<=2){
					allCode = code;
				}else{
					allCode = allCode.substring(0,allCode.length()-2)+code;
				}
				saved.setAllCode(allCode);
				ServiceProvider.getService(AssetTypeService.class).updateAssetTypeAndChildren(saved);
				renderJson("success");
			}
			
		}
		
	}
	
	public void deleteAssetType() throws DocumentException, IOException{
		String id = getRequestParameter("id");
		String version = getRequestParameter("version");
		boolean status = ServiceProvider.getService(AssetTypeService.class).hasChild(id, version);
		if(!status){
			ServiceProvider.getService(AssetTypeService.class).deleteAssetType(id);
			renderJson("success");
		}else{
			renderJson("该类型下已有子类，无法删除！");
		}
	}
	
	public void getAllAssetType() throws DocumentException, IOException{
		String version = getRequestParameter("version");
		AssetTypeVo vo = ServiceProvider.getService(AssetTypeService.class).getAllAssetTypeByVersion(version);
		renderJson(vo);
		
	}
	
	/**
	 * 发布版本
	 * @throws DocumentException 
	 */
	public void publish() throws DocumentException{
		String version = getRequestParameter("version");
		ServiceProvider.getService(AssetTypeService.class).publish(version);
	}

}
