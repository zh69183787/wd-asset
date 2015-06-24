package com.wonders.asset.service.impl;

import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.wonders.asset.dao.AssetMaintenanceCostDao;
import com.wonders.asset.dao.AssetOverhaulDao;
import com.wonders.asset.dao.TaskInventoryDao;
import com.wonders.asset.model.AssetMaintenanceCost;
import com.wonders.asset.model.AssetOverhaul;
import com.wonders.asset.model.TaskInventory;
import com.wonders.asset.service.XmlDataResolveService;
import com.wonders.framework.util.DateUtils;

/**
 * xml解析入库服务实现类
 * @author Kai Yao
 * @date 2013-11-15
 */
@SuppressWarnings("unchecked")
public class XmlDataResolveServiceImpl implements XmlDataResolveService {
	
	private AssetOverhaulDao assetOverhaulDao;
	
	private AssetMaintenanceCostDao assetMaintenanceCostDao;
	
	private TaskInventoryDao taskInventoryDao;
	
	public AssetOverhaulDao getAssetOverhaulDao() {
		return assetOverhaulDao;
	}

	public void setAssetOverhaulDao(AssetOverhaulDao assetOverhaulDao) {
		this.assetOverhaulDao = assetOverhaulDao;
	}

	public AssetMaintenanceCostDao getAssetMaintenanceCostDao() {
		return assetMaintenanceCostDao;
	}

	public void setAssetMaintenanceCostDao(
			AssetMaintenanceCostDao assetMaintenanceCostDao) {
		this.assetMaintenanceCostDao = assetMaintenanceCostDao;
	}

	public TaskInventoryDao getTaskInventoryDao() {
		return taskInventoryDao;
	}

	public void setTaskInventoryDao(TaskInventoryDao taskInventoryDao) {
		this.taskInventoryDao = taskInventoryDao;
	}

	@Override
	public String parseDataOfXml(String dataOfXmlString, String type) {
		Document doc = null;
		String result = "";
		try {
			doc = DocumentHelper.parseText(dataOfXmlString);
		
			Element root = doc.getRootElement();
			
			Element listEle = (Element)doc.selectSingleNode("/root/list");
			List<Element> childElements = listEle.elements();
			
			String rootDate = root.attributeValue("date");
			//String rootType = root.attributeValue("type");
			Date importDate = DateUtils.String2Date(rootDate, "yyyy-MM-dd");
			if("newAssetOverhaul".equals(type)){
				parseAssetOverHaul(childElements, importDate);
				result += "新增资产大修更新改造数据 " + childElements.size() + " 条";
			}else if("newAssetMaintenanceCost".equals(type)){
				parseAssetMaintenanceCost(childElements, importDate);
				result += "新增资产日常维护费用数据 " + childElements.size() + " 条";
			}else if("TaskInventory".equals(type)){
				parseTaskInventory(childElements, importDate);
				result += "新增或更新资产盘点数据 " + childElements.size() + " 条";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result += type + "解析出现异常，导入失败";
			return result;
		}
			
		return result;
	}
	
	public void parseAssetOverHaul(List<Element> childElements, Date importDate){
		Date createDate = new Date();
		for (Element child : childElements) {
			AssetOverhaul assetOverhaul = new AssetOverhaul();
			assetOverhaul.setImportDate(importDate);
			assetOverhaul.setAssetNo(child.elementText("assetNo"));
			assetOverhaul.setAssetName(child.elementText("assetName"));
			assetOverhaul.setProjectNo(child.elementText("projectNo"));
			assetOverhaul.setProjectName(child.elementText("projectName"));
			assetOverhaul.setCostTime(DateUtils.String2Date(child.elementText("costTime"), "yyyy-MM"));
			assetOverhaul.setMaintenanceCost(Double.parseDouble(child.elementText("maintenanceCost")));
			assetOverhaul.setCostHour(Double.parseDouble(child.elementText("costHour")));
			
			assetOverhaul.setCreateDate(createDate);
			assetOverhaul.setCreateUser("管理员");
			assetOverhaulDao.insert(assetOverhaul);
		}
	}
	
	public void parseAssetMaintenanceCost(List<Element> childElements, Date importDate){
		Date createDate = new Date();
		for (Element child : childElements) {
			AssetMaintenanceCost assetMaintenanceCost = new AssetMaintenanceCost();
			assetMaintenanceCost.setImportDate(importDate);
			assetMaintenanceCost.setAssetNo(child.elementText("assetNo"));
			assetMaintenanceCost.setAssetName(child.elementText("assetName"));
			assetMaintenanceCost.setCostTime(DateUtils.String2Date(child.elementText("costTime"), "yyyy-MM"));
			assetMaintenanceCost.setMaintenanceCost(Double.parseDouble(child.elementText("maintenanceCost")));
			assetMaintenanceCost.setCostHour(Double.parseDouble(child.elementText("costHour")));
			
			assetMaintenanceCost.setCreateDate(createDate);
			assetMaintenanceCost.setCreateUser("管理员");
			assetMaintenanceCostDao.insert(assetMaintenanceCost);
		}
	}
	
	public void parseTaskInventory(List<Element> childElements, Date importDate){
		Date createDate = new Date();
		for (Element child : childElements) {
			TaskInventory taskInventory = new TaskInventory();
			taskInventory.setTaskId(child.elementText("taskId"));
			taskInventory.setTaskName(child.elementText("taskName"));
			taskInventory.setCheckPersionList(child.elementText("checkPersionList"));
			taskInventory.setTaskUser(child.elementText("taskUser"));
			taskInventory.setStartTime(DateUtils.String2Date(child.elementText("startTime"), "yyyy-MM-dd"));
			taskInventory.setEndTime(DateUtils.String2Date(child.elementText("endTime"), "yyyy-MM-dd"));
			taskInventory.setTaskMemo(child.elementText("taskMemo"));
			taskInventory.setRealityTime(DateUtils.String2Date(child.elementText("realityTime"), "yyyy-MM-dd HH:mm:ss"));
			taskInventory.setTaskStatus(child.elementText("taskStatus"));
			taskInventory.setCompleterRate(child.elementText("completerRate"));
			taskInventory.setErrorNum (child.elementText("errorNum"));
			taskInventory.setOperateDate(DateUtils.String2Date(child.elementText("operateDate"), "yyyy-MM-dd HH:mm:ss"));
			taskInventory.setCreateDate(createDate);
			
			taskInventoryDao.insert(taskInventory);
		}
	}

}
