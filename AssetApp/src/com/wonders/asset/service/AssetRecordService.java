package com.wonders.asset.service;

import java.util.Map;

import javax.xml.bind.JAXBException;
import org.dom4j.DocumentException;
import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.AssetRecord;
/**
 * Created by HH on 2015/1/16.
 */
public interface AssetRecordService  extends BaseService<AssetRecord,String> {
    void importData()throws JAXBException, DocumentException;
    public Pagination<AssetRecord> findRecordAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}
