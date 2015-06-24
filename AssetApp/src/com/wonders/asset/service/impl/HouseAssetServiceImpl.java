package com.wonders.asset.service.impl;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wonders.asset.base.service.impl.BaseServiceImpl;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.dao.AreaInfoDao;
import com.wonders.asset.dao.HouseAssetDao;
import com.wonders.asset.model.HouseAsset;
import com.wonders.asset.service.DataStoreService;
import com.wonders.asset.service.HouseAssetService;
import com.wonders.webservice.dto.AreaInfoDto;
import com.wonders.webservice.dto.HouseAssetDto;
import com.wonders.webservice.dto.Response;

public class HouseAssetServiceImpl extends BaseServiceImpl<HouseAsset, String> implements HouseAssetService{

	private DataStoreService dataStoreService;
	
	private HouseAssetDao houseAssetDao;
	private AreaInfoDao areaInfoDao;
	
	@Override
	public void importData() throws JAXBException, DocumentException {
		List<Response> responseList =  dataStoreService.getDataFromStore(HouseAssetDto.class);
		for(Response response : responseList){
			for(Object obj : response.getList()){
				HouseAssetDto houseAssetDto = (HouseAssetDto) obj;
				List<AreaInfoDto> areaInfoList = houseAssetDto.getAreaInfoList();
				for(AreaInfoDto dto : areaInfoList){
					dto.setHouseAssetId(houseAssetDto.getId());
					dto.setUploadDate(houseAssetDto.getUploadDate());
				}
				areaInfoDao.save(areaInfoList);
			}
			houseAssetDao.save(response.getList());

			houseAssetDao.batchInsert();
			houseAssetDao.batchUpdate();

			areaInfoDao.batchInsert();
			areaInfoDao.batchUpdate();

			houseAssetDao.clear();
			areaInfoDao.clear();

			dataStoreService.confirmData(HouseAssetDto.class,response.getId());
		}
	}

	public DataStoreService getDataStoreService() {
		return dataStoreService;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	public HouseAssetDao getHouseAssetDao() {
		return houseAssetDao;
	}

	public void setHouseAssetDao(HouseAssetDao houseAssetDao) {
		this.houseAssetDao = houseAssetDao;
		setBaseDao(houseAssetDao);
	}

	public AreaInfoDao getAreaInfoDao() {
		return areaInfoDao;
	}

	public void setAreaInfoDao(AreaInfoDao areaInfoDao) {
		this.areaInfoDao = areaInfoDao;
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		HouseAssetService service = (HouseAssetService) ac.getBean("houseAssetService");
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
	public Pagination<HouseAsset> findHouseAssetAreaInfo(
			Map<String, String> houseAssetFilter,
			Map<String, String> areaInfoFilter, Map<String, String> sortMap,
			int startIndex, int pageSize) {
		return houseAssetDao.findHouseAssetAreaInfo(houseAssetFilter, 
				areaInfoFilter, sortMap, startIndex, pageSize);
	}

	@Override
	public List<Object[]> getLineBuildAreaReport() {
		return houseAssetDao.getLineBuildAreaReport();
	}

	@Override
	public List<Object[]> getLineStationAreaReport() {
		return houseAssetDao.getLineStationAreaReport();
	}

	@Override
	public List<Object[]> getUseTypeReport() {
		return houseAssetDao.getUseTypeReport();
	}

	@Override
	public List<Object[]> getUseTypeReportByBigLine() {
		return houseAssetDao.getUseTypeReportByBigLine();
	}

	@Override
	public Integer getUseTypeTakeOverDepRows() {
		return houseAssetDao.getUseTypeTakeOverDepRows();
	}

	@Override
	public List<Object[]> getBuildAreaByLine() {
		// TODO Auto-generated method stub
		return houseAssetDao.getBuildAreaByLine();
	}

	
}
