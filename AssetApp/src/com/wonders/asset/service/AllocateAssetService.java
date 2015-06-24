package com.wonders.asset.service;



import java.util.Map;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.AllocateAsset;
import com.wonders.asset.model.BorrowAsset;

import org.dom4j.DocumentException;
import javax.xml.bind.JAXBException;

/**
 * Created by HH on 2014/11/9.
 */
public interface AllocateAssetService extends BaseService<AllocateAsset,String> {
    void importData() throws JAXBException, DocumentException;
    public Pagination<AllocateAsset> findAllocateAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}
