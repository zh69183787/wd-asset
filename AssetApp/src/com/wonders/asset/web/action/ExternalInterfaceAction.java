package com.wonders.asset.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.AssetMaintenanceCost;
import com.wonders.asset.model.AssetOverhaul;
import com.wonders.asset.service.AssetMaintenanceCostService;
import com.wonders.asset.service.AssetOverhaulService;
import com.wonders.asset.service.XmlDataResolveService;
import com.wonders.framework.util.ServiceProvider;

/**
 * 对外接口action类
 * @author Kai Yao
 * @date 2013-11-25
 */
public class ExternalInterfaceAction extends AbstractBaseAction{

	/**
	 * 日志对象
	 */
	public static final Logger logger = Logger.getLogger("XmlDataResolveAction");
	
	private XmlDataResolveService xmlDataResolveService;
	private AssetOverhaulService assetOverhaulService;
	private AssetMaintenanceCostService assetMaintenanceCostService;
	private File upload;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public XmlDataResolveService getXmlDataResolveService() {
		return xmlDataResolveService;
	}

	public void setXmlDataResolveService(XmlDataResolveService xmlDataResolveService) {
		this.xmlDataResolveService = xmlDataResolveService;
	}
	
	public AssetOverhaulService getAssetOverhaulService() {
		return assetOverhaulService;
	}

	public void setAssetOverhaulService(AssetOverhaulService assetOverhaulService) {
		this.assetOverhaulService = assetOverhaulService;
	}

	public AssetMaintenanceCostService getAssetMaintenanceCostService() {
		return assetMaintenanceCostService;
	}

	public void setAssetMaintenanceCostService(
			AssetMaintenanceCostService assetMaintenanceCostService) {
		this.assetMaintenanceCostService = assetMaintenanceCostService;
	}

	/**
	 * 远程调用xml解析的接口
	 * @return
	 * @throws Exception
	 */
	public void doResolve() throws Exception {
		
		String dataOfXmlString = getRequestParameter("dataOfXmlString");
		String type = getRequestParameter("type");
		
		String result = ServiceProvider.getService(XmlDataResolveService.class).parseDataOfXml(dataOfXmlString, type);
		renderJson(result);
	}
	
	/**
	 * 测试接口action
	 * @throws Exception
	 */
	public void test() throws Exception {
		InputStreamReader inputSr = new InputStreamReader(new FileInputStream(upload),"UTF-8");
		Document doc = null;
		SAXReader reader = new SAXReader(); 
		try {
			doc = reader.read(inputSr);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		String type = root.attributeValue("type");
		String result = ServiceProvider.getService(XmlDataResolveService.class).parseDataOfXml(doc.asXML(), type);
		renderJson(result);
	}
	
	/**
	 * 根据assetNo查找资产大修更新改造
	 * @throws Exception
	 */
	public void findAssetOverHaul() throws Exception{
		String assetNo = getRequestParameter("assetNo");
		List<AssetOverhaul> result = ServiceProvider.getService(AssetOverhaulService.class).findByAssetNo(assetNo);
		renderJson(result);
	}
	
	/**
	 * 根据assetNo查找日常维护费用数据
	 * @throws Exception
	 */
	public void findAssetMaintenanceCost() throws Exception{
		String assetNo = getRequestParameter("assetNo");
		List<AssetMaintenanceCost> result = ServiceProvider.getService(AssetMaintenanceCostService.class).findByAssetNo(assetNo);
		renderJson(result);
	}
}
