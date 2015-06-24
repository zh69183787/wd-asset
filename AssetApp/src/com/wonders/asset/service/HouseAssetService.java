package com.wonders.asset.service;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.HouseAsset;

public interface HouseAssetService extends BaseService<HouseAsset, String>{

	void importData() throws JAXBException, DocumentException;
	
	public Pagination<HouseAsset> findHouseAssetAreaInfo(Map<String, String> houseAssetFilter, 
			Map<String,String> areaInfoFilter, Map<String, String> sortMap, int startIndex, int pageSize);
	
	public List<Object[]> getLineBuildAreaReport();
	
	public List<Object[]> getLineStationAreaReport();
	
	public List<Object[]> getUseTypeReport();
	
	public List<Object[]> getUseTypeReportByBigLine();
	
	Integer getUseTypeTakeOverDepRows();

	List<Object[]> getBuildAreaByLine();
}
