package com.wonders.api;

import com.wonders.api.dto.AssetInfo;
import com.wonders.asset.base.web.AbstractBaseAction;
import com.wonders.asset.model.HouseAsset;
import com.wonders.asset.service.*;
import com.wonders.framework.util.ServiceProvider;
import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBException;

/**
 * Created by HH on 2014/11/20.
 */
public class AnalysisOfXmlAction extends AbstractBaseAction {
	
	private DisableAssetService disableAssetService;   
	private StopAssetService stopAssetService;
	private BorrowAssetService borrowAssetService;
	private HouseAssetService houseAssetService;
	private LandAssetService landAssetService;
	private AllocateAssetService allocateAssetService;
	private DamageAssetService damageAssetService;
	private AssetTaskService assetTaskService;
	private AssetRecordService assetRecordService;
	private DataStoreService dataStoreService;
	private String content;

	public void setData() throws Exception {
		dataStoreService.setDataInStore(content);
	}

	public void importData() {
		try {
			disableAssetService.importData(); 
			assetRecordService.importData();     
			borrowAssetService.importData();
			houseAssetService.importData();
			landAssetService.importData();
			stopAssetService.importData();
			allocateAssetService.importData();
			damageAssetService.importData();
			assetTaskService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void importDisableAsset() {
		try {
			disableAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void importAssetRecord() {
		try {
			assetRecordService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public void importBorrowAsset() {  
		try {
			borrowAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void importHouseAsset() {
		try {
			houseAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void importLandAsset() {
		try {
			landAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void importStopAsset() {
		try {
			stopAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {   
			e.printStackTrace();
		}
	}

	public void importAllocateAsset() {
		try {
			allocateAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void importDamageAsset() {
		try {
			damageAssetService.importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public void importAssetTask() {
		try {
			ServiceProvider.getService(AssetTaskService.class).importData();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public DisableAssetService getDisableAssetService() {
		return disableAssetService;
	}

	public void setDisableAssetService(DisableAssetService disableAssetService) {
		this.disableAssetService = disableAssetService;
	}
	
	public StopAssetService getStopAssetService() {
		return stopAssetService;
	}

	public void setStopAssetService(StopAssetService stopAssetService) {
		this.stopAssetService = stopAssetService;
	}

	public BorrowAssetService getBorrowAssetService() {
		return borrowAssetService;
	}

	public void setBorrowAssetService(BorrowAssetService borrowAssetService) {
		this.borrowAssetService = borrowAssetService;
	}

	public HouseAssetService getHouseAssetService() {
		return houseAssetService;
	}

	public void setHouseAssetService(HouseAssetService houseAssetService) {
		this.houseAssetService = houseAssetService;
	}

	public LandAssetService getLandAssetService() {
		return landAssetService;
	}

	public void setLandAssetService(LandAssetService landAssetService) {
		this.landAssetService = landAssetService;
	}

	public AllocateAssetService getAllocateAssetService() {
		return allocateAssetService;
	}

	public void setAllocateAssetService(
			AllocateAssetService allocateAssetService) {
		this.allocateAssetService = allocateAssetService;
	}

	public DamageAssetService getDamageAssetService() {
		return damageAssetService;
	}

	public void setDamageAssetService(DamageAssetService damageAssetService) {
		this.damageAssetService = damageAssetService;
	}

	public AssetTaskService getAssetTaskService() {
		return assetTaskService;
	}

	public void setAssetTaskService(AssetTaskService assetTaskService) {
		this.assetTaskService = assetTaskService;
	}

	public DataStoreService getDataStoreService() {
		return dataStoreService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public AssetRecordService getAssetRecordService() {
		return assetRecordService;
	}

	public void setAssetRecordService(AssetRecordService assetRecordService) {
		this.assetRecordService = assetRecordService;
	}
	
	
}
