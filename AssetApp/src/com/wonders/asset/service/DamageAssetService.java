package com.wonders.asset.service;

import java.util.Map;

import com.wonders.asset.base.service.BaseService;
import com.wonders.asset.base.util.Pagination;
import com.wonders.asset.model.DamageAsset;
import org.dom4j.DocumentException;
import javax.xml.bind.JAXBException;

/**
 * Created by Administrator on 2014/11/5.
 */
public interface DamageAssetService extends BaseService<DamageAsset,String> {
    void importData() throws JAXBException, DocumentException;
    
    public Pagination<DamageAsset> findDamageAndAsset(Map<String, String> filterMap,
			Map<String, String> sortMap, int startIndex, int pageSize);
}
