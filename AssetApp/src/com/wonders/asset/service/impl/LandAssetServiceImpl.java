package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.dao.LandAssetDao;
import com.wonders.asset.model.LandAsset;
import com.wonders.asset.service.DataStoreService;
import com.wonders.asset.service.LandAssetService;
import com.wonders.webservice.dto.DamageAssetDto;
import com.wonders.webservice.dto.LandAssetDto;
import com.wonders.webservice.dto.Response;

public class LandAssetServiceImpl extends BaseServiceImpl<LandAsset, String> implements LandAssetService{

	private DataStoreService dataStoreService;
	
	private LandAssetDao landAssetDao;
	
	/**
	 * 导入数据
	 */
	@Override
	public void importData() throws JAXBException, DocumentException {
		List<Response> responseList =  dataStoreService.getDataFromStore(LandAssetDto.class);
		for(Response response : responseList){
			landAssetDao.save(response.getList());
			landAssetDao.batchInsert();
			landAssetDao.batchUpdate();
			landAssetDao.clear();
			dataStoreService.confirmData(DamageAssetDto.class,response.getId());
		}
		
	}

    @Override
    public List<Map<String, String>> findReport() {
        return landAssetDao.findReports();
    }

    public DataStoreService getDataStoreService() {
		return dataStoreService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public LandAssetDao getLandAssetDao() {
		return landAssetDao;
	}

	public void setLandAssetDao(LandAssetDao landAssetDao) {
		this.landAssetDao = landAssetDao;
        setBaseDao(landAssetDao);
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		LandAssetService service = (LandAssetService) ac.getBean("landAssetService");
		try {
			service.importData();
			System.out.println("operation over...");
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object[]> getBuildAreaByLine() {
		// TODO Auto-generated method stub
		return landAssetDao.getBuildAreaByLine();
	}

}
