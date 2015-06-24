package com.wonders.asset.service;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.BorrowAsset;

public interface BorrowAssetService extends BaseService<BorrowAsset, String>{

	void importData() throws JAXBException, DocumentException;
	public Pagination<BorrowAsset> findBorrowAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize); 
	
}
