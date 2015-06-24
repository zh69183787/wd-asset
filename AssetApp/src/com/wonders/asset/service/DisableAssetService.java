package com.wonders.asset.service;

import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.BorrowAsset;
import com.wonders.asset.model.DisableAsset;

public interface DisableAssetService extends BaseService<DisableAsset,String> {
    void importData()throws JAXBException, DocumentException;
    public Pagination<DisableAsset> findDisableAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize); 
}
