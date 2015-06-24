package com.wonders.asset.service;

import javax.xml.bind.JAXBException;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.model.LandAsset;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

public interface LandAssetService extends BaseService<LandAsset,String> {

	void importData() throws JAXBException, DocumentException;

    public List<Map<String,String>> findReport();

	List<Object[]> getBuildAreaByLine();
}
